package com.dvtd.pojo;

import com.dvtd.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Reviews.class)
public class Reviews_ { 

    public static volatile SingularAttribute<Reviews, Date> createdDate;
    public static volatile SingularAttribute<Reviews, Users> shipperID;
    public static volatile SingularAttribute<Reviews, Integer> rating;
    public static volatile SingularAttribute<Reviews, String> comment;
    public static volatile SingularAttribute<Reviews, Integer> reviewID;
    public static volatile SingularAttribute<Reviews, Users> userID;

}