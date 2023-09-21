<%-- 
    Document   : login
    Created on : Aug 23, 2023, 11:17:40 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/login" var="action"/>
<div class="container d-flex" >
    <div class=" bg-white mx-auto w-30 p-3" >
        <h2 class="w-100 text-center text-uppercase">Đăng nhập</h2>
        <c:if test="${param.error ne null}">
            <div  class="alert alert-danger">    
                Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu.
            </div>
        </c:if>
        
        <form action="${action}" class="" method="post">
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Tên đăng nhập:</label>
                <input type="text" class="form-control" id="email" placeholder="Nhập tên đăng nhập..." name="username">
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">Mật khẩu:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Nhập mật khẩu..." name="password">
            </div>

            <button type="submit" class="btn btn-primary w-100" style="background-color: #6610F2">Đăng nhập</button>
        </form>
    </div>
</div>