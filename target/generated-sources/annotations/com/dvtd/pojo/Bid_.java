package com.dvtd.pojo;

import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Bid.class)
public class Bid_ { 

    public static volatile SingularAttribute<Bid, Date> bidTime;
    public static volatile SingularAttribute<Bid, Date> createdDate;
    public static volatile SingularAttribute<Bid, Users> shipperID;
    public static volatile SingularAttribute<Bid, Orders> orderID;
    public static volatile SingularAttribute<Bid, Integer> price;
    public static volatile SingularAttribute<Bid, Boolean> isChosen;
    public static volatile SingularAttribute<Bid, Integer> bidID;

}