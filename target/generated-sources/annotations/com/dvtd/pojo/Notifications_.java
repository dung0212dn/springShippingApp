package com.dvtd.pojo;

import com.dvtd.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-09-16T07:47:43")
@StaticMetamodel(Notifications.class)
public class Notifications_ { 

    public static volatile SingularAttribute<Notifications, Date> createdDate;
    public static volatile SingularAttribute<Notifications, Boolean> isRead;
    public static volatile SingularAttribute<Notifications, Integer> notificationID;
    public static volatile SingularAttribute<Notifications, String> type;
    public static volatile SingularAttribute<Notifications, Users> userID;
    public static volatile SingularAttribute<Notifications, String> content;

}