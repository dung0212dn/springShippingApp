/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.Promotions;
import com.dvtd.repository.PromotionsRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class PromotionsRepositoryImpl implements PromotionsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
//    @Autowired
//    private SimpleDateFormat f;
    @Autowired
    private Environment env;

    @Override
    public List<Promotions> getPromotions(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Promotions> q = b.createQuery(Promotions.class);
        Root root = q.from(Promotions.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("code"), String.format("%%%s%%", kw)));
            }

            String sortOption = params.get("sort");
            if (sortOption != null && !sortOption.isEmpty()) {
                switch (sortOption) {
                    case "codeAsc":
                        q.orderBy(b.asc(root.get("code")));
                        break;
                    case "codeDesc":
                        q.orderBy(b.desc(root.get("code")));
                        break;
                    case "valueAsc":
                        q.orderBy(b.asc(root.get("value")));
                        break;
                    case "valueDesc":
                        q.orderBy(b.desc(root.get("value")));
                        break;
                    case "expirationDateAsc":
                        q.orderBy(b.asc(root.get("expirationDate")));
                        break;
                    case "expirationDateDesc":
                        q.orderBy(b.desc(root.get("expirationDate")));
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
    public Promotions getPromotionDetail(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Promotions.class, id);
    }

    @Override
    public boolean addPromotions(Promotions p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countPromotions() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(Promotions.class);
        q.select(b.count(root));

        Query query = s.createQuery(q);
        return Integer.parseInt(query.getSingleResult().toString());

    }
    
     @Override
    public boolean updatePromotion(Promotions p) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePromotion(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Promotions p = this.getPromotionDetail(id);
        
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Promotions getPromotionDetailByCode(String code) {
         Session s = this.factory.getObject().getCurrentSession();
         CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Promotions> q = b.createQuery(Promotions.class);
        Root root = q.from(Promotions.class);
        
        q.where(b.equal(root.get("code"), code));
        try {
            Query query = s.createQuery(q);
             return (Promotions) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
       
    }
}
