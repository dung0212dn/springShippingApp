/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.Users;
import com.dvtd.repository.BidsRepository;
import com.dvtd.repository.OrdersRepository;
import com.dvtd.repository.UsersRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class BidsRepositoryImpl implements BidsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private OrdersRepository ordersRepo;
    @Autowired
    private Environment env;

    @Override
    public Bid bidCreate(int orderId, Map<String, String> bidInfo) {
        try {
            Session s = this.factory.getObject().getCurrentSession();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currenPrincipal = auth.getName();
            Users currentUsers = this.userRepo.getUserByUsername(currenPrincipal);

            Bid bid = new Bid();
            bid.setOrderID(this.ordersRepo.getOrderById(orderId));
            bid.setPrice(Integer.parseInt(bidInfo.get("price")));
            bid.setIsChosen(false);
            bid.setShipperID(currentUsers);
            bid.setCreatedDate(new Date());
            bid.setBidTime(new Date());

            s.save(bid);
            return bid;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Hoàn tác transaction
            return null;

        }
    }

    @Override
    public List<Bid> getAllBidByOrderId(int orderId, Map<String, String> params) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Bid> q = b.createQuery(Bid.class);
            Root rootB = q.from(Bid.class);

            q.where(b.equal(rootB.get("orderID"), orderId));

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
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Bid getBidOrderByShipperId(int orderId, int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Bid> q = b.createQuery(Bid.class);
            Root rootB = q.from(Bid.class);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(b.equal(rootB.get("shipperID"), userId));
            predicates.add(b.equal(rootB.get("orderID"), orderId));

            q.where(predicates.toArray(Predicate[]::new));

            Query query = s.createQuery(q);
            return (Bid) query.getSingleResult();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Bid updateBid(Bid bid) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(bid);
            return bid;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Bid> getAllBidByShipperId(Map<String, String> params, int userID) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Bid> q = b.createQuery(Bid.class);
        Root rootB = q.from(Bid.class);

        q.where(b.equal(rootB.get("shipperID"), userID));

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
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
