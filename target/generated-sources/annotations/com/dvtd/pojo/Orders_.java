package com.dvtd.pojo;

import com.dvtd.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Users> chosenShipperID;
    public static volatile SingularAttribute<Orders, Integer> orderID;
    public static volatile SingularAttribute<Orders, String> receiverName;
    public static volatile SingularAttribute<Orders, String> title;
    public static volatile SingularAttribute<Orders, Users> userID;
    public static volatile SingularAttribute<Orders, String> content;
    public static volatile SingularAttribute<Orders, Integer> orderTotal;
    public static volatile SingularAttribute<Orders, String> receiverPhone;
    public static volatile SingularAttribute<Orders, Date> createdDate;
    public static volatile SingularAttribute<Orders, String> shippingAddress;
    public static volatile SingularAttribute<Orders, String> billingAddress;
    public static volatile SingularAttribute<Orders, String> receiverEmail;
    public static volatile SingularAttribute<Orders, String> status;

}