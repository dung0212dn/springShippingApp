<%-- 
    Document   : sidebar
    Created on : Aug 16, 2023, 8:21:39 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="d-flex flex-column p-3 bg-light border bg-white" style="width: 280px; height: 100%;">
    <ul class="nav nav-pills flex-column justify-content-between  mb-auto ">
        <li class="nav-item">
            <a href="<c:url value="/promotions"/>" class="nav-link">
                Khuyến mãi
            </a>
        </li>
        <li class="nav-item">
            <a href="<c:url value="/users"/>" class="nav-link">
                Người dùng
            </a>
        </li>
         <li class="nav-item">
            <a href="<c:url value="/orders"/>" class="nav-link">
                Đơn hàng
            </a>
        </li>
      
    </ul>
    <hr>
  
</div>