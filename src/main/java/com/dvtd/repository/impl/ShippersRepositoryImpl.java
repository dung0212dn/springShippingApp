/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.ShipperInfo;
import com.dvtd.pojo.Shippers;
import com.dvtd.pojo.Users;
import com.dvtd.repository.ShippersRepository;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class ShippersRepositoryImpl implements ShippersRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Shippers getShipperByCMND(String CMND) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Shippers.class);
        q.select(root);
        q.where(b.equal(root.get("cmnd"), CMND));

        Query query = s.createQuery(q);

        try {
            return (Shippers) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ShipperInfo getShipperByUserId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ShipperInfo> q = b.createQuery(ShipperInfo.class);
        Root rootS = q.from(Shippers.class);

        Join<Users, Shippers> userJoin = rootS.join("userID");
        q.where(b.equal(rootS.get("userID"), id));
        
        q.select(
                b.construct(
                        ShipperInfo.class,
                        userJoin.get("userID"),
                        userJoin.get("username"),
                        userJoin.get("role"),
                        userJoin.get("avatar"),
                        userJoin.get("email"),
                        userJoin.get("phone"),
                        rootS.get("shipperID"),
                        rootS.get("cmnd")
           
                )
        );

        Query query = s.createQuery(q);
        return (ShipperInfo) query.getSingleResult();
    }

}
