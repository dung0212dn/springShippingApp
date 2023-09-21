/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dvtd.pojo.Shippers;
import com.dvtd.pojo.Users;
import com.dvtd.repository.UsersRepository;
import com.dvtd.service.impl.UsersServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Users> getUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("username"), String.format("%%%s%%", kw)));
            }

            String sortOption = params.get("sort");
            if (sortOption != null && !sortOption.isEmpty()) {
                switch (sortOption) {
                    case "usernameAsc":
                        q.orderBy(b.asc(root.get("username")));
                        break;
                    case "usernameDesc":
                        q.orderBy(b.desc(root.get("username")));
                        break;
                    case "roleAdmin":
                        predicates.add(b.like(root.get("role"), "ROLE_ADMIN"));
                        break;
                    case "roleUser":
                        predicates.add(b.like(root.get("role"), "ROLE_USER"));
                        break;
                    case "roleShipper":
                        predicates.add(b.like(root.get("role"), "ROLE_SHIPPER"));
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
    public Users getUserDetail(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addUsers(Map<String, String> params, String type, MultipartFile avatar) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            Users u = new Users();
            u.setName(params.get("name"));
            u.setPhone(params.get("phone"));
            u.setEmail(params.get("email"));
            u.setUsername(params.get("username"));
            u.setPassword(this.passwordEncoder.encode(params.get("password")));
         

            if (type == "SHIPPER_REGISTER") {
                u.setRole("ROLE_SHIPPER");
                u.setActive(Short.parseShort("0")); 
            } else {
                u.setRole("ROLE_USER");
                u.setActive(Short.parseShort(params.get("active")));
            }

            Date now = new Date();
            u.setCreatedDate(now);

            if (!avatar.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    u.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            s.save(u);
            if (type == "SHIPPER_REGISTER") {
                Shippers shipper = new Shippers();
                shipper.setCmnd(params.get("CMND"));
                shipper.setUserID(u);
                shipper.setCreatedDate(now);
                s.save(shipper);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // Hoàn tác transaction
            return false;
        }
    }

    @Override
    public boolean updateUsers(Users u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(u);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(Users.class);
        q.select(b.count(root));

        Query query = s.createQuery(q);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public boolean deleteUsers(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);
        q.where(b.equal(root.get("username"), username));

        Query query = s.createQuery(q);

        try {
            return (Users) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        Users u = this.getUserByUsername(username);
        if (u.getActive() == 1) {
            return this.passwordEncoder.matches(password, u.getPassword());
        }
        return false;
    }

}
