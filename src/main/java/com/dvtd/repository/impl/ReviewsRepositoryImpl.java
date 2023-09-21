/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.Reviews;
import com.dvtd.pojo.Users;
import com.dvtd.repository.ReviewsRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class ReviewsRepositoryImpl implements ReviewsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Reviews reviewCreate(Reviews review) {
        Session s = this.factory.getObject().getCurrentSession();
       
        try {
            s.save(review);
            return review;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reviews> getReviewsByShipperID(int shipperID) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Reviews> q = b.createQuery(Reviews.class);
        Root rootR = q.from(Reviews.class);
        
        q.where(b.equal(rootR.get("shipperID"), shipperID));
        q.orderBy(b.desc(rootR.get("createdDate")));
        
        Query query = s.createQuery(q);
        
        return query.getResultList();
    }

}
