package com.dvtd.pojo;

import com.dvtd.pojo.Bid;
import com.dvtd.pojo.Notifications;
import com.dvtd.pojo.Orders;
import com.dvtd.pojo.Reviews;
import com.dvtd.pojo.Shippers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SetAttribute<Users, Notifications> notificationsSet;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SetAttribute<Users, Shippers> shippersSet;
    public static volatile SingularAttribute<Users, Short> active;
    public static volatile SingularAttribute<Users, String> avatar;
    public static volatile SetAttribute<Users, Orders> ordersSet;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SetAttribute<Users, Reviews> reviewsSet;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Date> createdDate;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile CollectionAttribute<Users, Bid> bidCollection;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;

}