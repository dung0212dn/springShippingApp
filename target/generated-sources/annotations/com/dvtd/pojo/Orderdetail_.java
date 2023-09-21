package com.dvtd.pojo;

import com.dvtd.pojo.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Orderdetail.class)
public class Orderdetail_ { 

    public static volatile SingularAttribute<Orderdetail, Integer> quantity;
    public static volatile SingularAttribute<Orderdetail, Orders> orderID;
    public static volatile SingularAttribute<Orderdetail, String> productImg;
    public static volatile SingularAttribute<Orderdetail, Integer> orderDetailID;
    public static volatile SingularAttribute<Orderdetail, String> productName;

}