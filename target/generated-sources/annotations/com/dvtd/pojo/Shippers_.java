package com.dvtd.pojo;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Shippers.class)
public class Shippers_ { 

    public static volatile SingularAttribute<Shippers, Integer> shipperID;
    public static volatile SingularAttribute<Shippers, Date> createdDate;
    public static volatile SetAttribute<Shippers, Orders> ordersSet1;
    public static volatile SetAttribute<Shippers, Bid> bidSet;
    public static volatile SingularAttribute<Shippers, Users> userID;
    public static volatile SingularAttribute<Shippers, String> cmnd;

}