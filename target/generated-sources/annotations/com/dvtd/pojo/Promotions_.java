package com.dvtd.pojo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Promotions.class)
public class Promotions_ { 

    public static volatile SingularAttribute<Promotions, String> code;
    public static volatile SingularAttribute<Promotions, Integer> quantity;
    public static volatile SingularAttribute<Promotions, Date> createdDate;
    public static volatile SingularAttribute<Promotions, String> type;
    public static volatile SingularAttribute<Promotions, Integer> value;
    public static volatile SingularAttribute<Promotions, Integer> promotionID;
    public static volatile SingularAttribute<Promotions, Date> expirationDate;

}