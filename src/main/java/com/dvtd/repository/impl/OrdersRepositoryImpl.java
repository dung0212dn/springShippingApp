/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.OrderInfo;
import com.dvtd.pojo.Orderdetail;
import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Promotions;
import com.dvtd.pojo.Users;
import com.dvtd.repository.BidsRepository;
import com.dvtd.repository.OrdersRepository;
import com.dvtd.repository.PromotionsRepository;
import com.dvtd.repository.UsersRepository;
import com.dvtd.service.EmailSenderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *
 * @author ACER
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
@PropertySource("classpath:configs.properties")
public class OrdersRepositoryImpl implements OrdersRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private PromotionsRepository promotionRepo;
    @Autowired
    private BidsRepository bidsRepo;
    @Autowired
    private Environment env;

    @Override
    public List<Orders> getAllOrders(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
        Root<Orders> root = q.from(Orders.class);

        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {

                predicates.add(b.like(root.get("userID").get("username"), String.format("%%%s%%", kw)));
            }
            
            String type = params.get("type");
            if (type != null && !type.isEmpty()) {
                predicates.add(b.equal(root.get("status"), type));
            }

            String sortOption = params.get("sort");
            if (sortOption != null && !sortOption.isEmpty()) {
                switch (sortOption) {
                    case "createdDateAsc":
                        q.orderBy(b.asc(root.get("createdDate")));
                        break;
                    case "createdDateDesc":
                        q.orderBy(b.desc(root.get("createdDate")));
                        break;
                    case "IS_AUTIONING":
                        predicates.add(b.like(root.get("status"), "IS_AUTIONING"));
                        break;
                    case "IS_DELIVERED":
                        predicates.add(b.like(root.get("status"), "IS_DELIVERED"));
                        break;
                    case "IS_SHIPPING":
                        predicates.add(b.like(root.get("status"), "IS_SHIPPING"));
                        break;
                    case "IS_CANCEL":
                        predicates.add(b.like(root.get("status"), "IS_CANCEL"));
                        break;
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);
        query.setMaxResults(Integer.parseInt(this.env.getProperty("PAGE_SIZE")));
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int currentPage = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setFirstResult((currentPage - 1) * pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public Orders createOrder(Orders orderInfo, List<Orderdetail> orderDetail, String promotion) {
        try {
            Session s = this.factory.getObject().getCurrentSession();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currenPrincipal = auth.getName();
            Users currentUsers = this.userRepo.getUserByUsername(currenPrincipal);

            Orders order = new Orders();
            order.setTitle(orderInfo.getTitle());
            order.setContent(orderInfo.getContent());
            order.setBillingAddress(orderInfo.getBillingAddress());
            order.setShippingAddress(orderInfo.getShippingAddress());
            order.setStatus("IS_AUTIONING");
            order.setOrderTotal(orderInfo.getOrderTotal());
            order.setUserID(currentUsers);
            order.setReceiverName(orderInfo.getReceiverName());
            order.setReceiverPhone(orderInfo.getReceiverPhone());
            order.setReceiverEmail(orderInfo.getReceiverEmail());

            Promotions prm = this.promotionRepo.getPromotionDetailByCode(promotion);
            if (prm != null) {
                if (prm.getQuantity() > 0) {
                    prm.setQuantity(prm.getQuantity() - 1);
                    this.promotionRepo.updatePromotion(prm);
                }
            }
            Date now = new Date();
            order.setCreatedDate(now);

            s.save(order);

            for (Orderdetail o : orderDetail) {
                Orderdetail ord = new Orderdetail();
                ord.setProductName(o.getProductName());
                ord.setQuantity(o.getQuantity());
                ord.setProductImg(o.getProductImg());
                ord.setOrderID(order);
                s.save(ord);
            }
            return order;
        } catch (HibernateException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Hoàn tác transaction
            e.printStackTrace();
            return null;
        }

    }

    public Orders getOrderById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Orders.class, id);
    }

    public List<Orderdetail> getOrderDetailsByOrderId(int orderId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Orderdetail> query = criteriaBuilder.createQuery(Orderdetail.class);
        Root<Orderdetail> root = query.from(Orderdetail.class);

        query.select(root);
        query.where(criteriaBuilder.equal(root.get("orderID"), orderId));

        return session.createQuery(query).getResultList();
    }

    @Override
    public Orders update(Orders o) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            s.update(o);
            return o;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Hoàn tác transaction
            return null;
        }
    }

    @Override
    public OrderInfo getOrderInfoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        // Lấy thông tin đơn hàng
        Orders order = this.getOrderById(id);

        // Lấy thông tin chi tiết đơn hàng
        CriteriaQuery<Orderdetail> detailQuery = b.createQuery(Orderdetail.class);
        Root<Orderdetail> rootDetail = detailQuery.from(Orderdetail.class);
        detailQuery.select(rootDetail);
        detailQuery.where(b.equal(rootDetail.get("orderID"), id));
        List<Orderdetail> orderDetails = s.createQuery(detailQuery).getResultList();

        // Gán danh sách Orderdetail vào OrderInfo
        // Tạo đối tượng OrderInfo
        OrderInfo orderInfo = new OrderInfo(order, orderDetails);

        return orderInfo;
    }

//    @Override
//    public boolean chooseShipperForOrder(int orderID, int shipperID) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
//        Root rootO = q.from(Orders.class);
//
//        Orders o = this.getOrderById(orderID);
//        o.setChosenShipperID(this.userRepo.getUserDetail(shipperID));
//        o.setStatus("IS_SHIPPING");
//        try {
//            s.update(o);
//            return true;
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Hoàn tác transaction
//            return false;
//        }
//
//    }
    @Override
    public int countOrders() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(Orders.class);
        q.select(b.count(root));

        Query query = s.createQuery(q);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public List<Orders> getAllOrdersByUserID(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currenPrincipal = auth.getName();
        Users currentUsers = this.userRepo.getUserByUsername(currenPrincipal);

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
        Root<Orders> root = q.from(Orders.class);
        q.select(root);

        q.where(b.equal(root.get("userID"), currentUsers.getUserID()));

        Query query = s.createQuery(q);
        query.setMaxResults(Integer.parseInt(this.env.getProperty("PAGE_SIZE")));
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int currentPage = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setFirstResult((currentPage - 1) * pageSize);
            }
        }
        return query.getResultList();
    }

    @Override
    public List<Orders> getAllOrdersByBids(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Orders> q = b.createQuery(Orders.class);
        Root<Orders> root = q.from(Orders.class);
        q.select(root);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currenPrincipal = auth.getName();
        Users currentUsers = this.userRepo.getUserByUsername(currenPrincipal);

        List<Bid> bids = this.bidsRepo.getAllBidByShipperId(params, currentUsers.getUserID());

        List<Integer> orderIds = new ArrayList<>();
        for (Bid bid : bids) {
            orderIds.add(bid.getOrderID().getOrderID());
        }

        q.where(root.get("orderID").in(orderIds));

        try {
            Query query = s.createQuery(q);
            query.setMaxResults(Integer.parseInt(this.env.getProperty("PAGE_SIZE")));
            if (params != null) {
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int currentPage = Integer.parseInt(page);
                    int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                    query.setFirstResult((currentPage - 1) * pageSize);
                }
            }
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

   
}
