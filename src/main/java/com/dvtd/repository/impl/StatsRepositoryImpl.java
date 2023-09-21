/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvtd.repository.impl;

import com.dvtd.pojo.Orders;
import com.dvtd.repository.StatsRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private SimpleDateFormat f;

    @Override
    public List<Object[]> statsRevenue(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rOrder = q.from(Orders.class);

     
        q.multiselect(b.function("MONTH", Integer.class, rOrder.get("createdDate")).alias("month"), b.sum(rOrder.get("price")).alias("revenue"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rOrder.get("status"), "IS_DELIVERED"));
        String fd = params.get("fromDate");
        if (fd != null && !fd.isEmpty()) {
            try {
                predicates.add(b.greaterThanOrEqualTo(rOrder.get("createdDate"), f.parse(fd)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String td = params.get("toDate");
        if (td != null && !td.isEmpty()) {
            try {
                predicates.add(b.lessThanOrEqualTo(rOrder.get("createdDate"), f.parse(td)));
            } catch (ParseException ex) {
                Logger.getLogger(StatsRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String quarter = params.get("quarter");
        if (quarter != null && !quarter.isEmpty()) {
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                predicates.addAll(Arrays.asList(
                        b.equal(b.function("YEAR", Integer.class, rOrder.get("createdDate")), Integer.parseInt(year)),
                        b.equal(b.function("QUARTER", Integer.class, rOrder.get("createdDate")), Integer.parseInt(quarter))
                ));
            }
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function("MONTH", Integer.class, rOrder.get("createdDate")));
        q.orderBy(b.asc(b.function("MONTH", Integer.class, rOrder.get("createdDate"))));

        try {
            Query query = session.createQuery(q);
            return query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
