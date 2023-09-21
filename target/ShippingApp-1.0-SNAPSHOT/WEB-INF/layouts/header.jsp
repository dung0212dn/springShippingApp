<%-- 
    Document   : header
    Created on : Aug 1, 2023, 1:51:11 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="p-3 border-bottom bg-white">
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <ul class="nav col-12 me-lg-auto col-lg-auto mb-2 justify-content-center mb-md-0">
                <c:url value="/" var="home"/>
                <li><a href="${home}" class="nav-link px-2 link-secondary">Trang chủ</a></li>
            </ul>

            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <div class="h-100 d-flex align-items-center">  <p class="m-0">Xin chào, ${pageContext.request.userPrincipal.name}</p></div>
                    <div class="dropdown text-end ms-3">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${currentUser.avatar}" alt="mdo" width="32" height="32" class="rounded-circle">
                        </a>
                       
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                            <li><a class="dropdown-item" href="<c:url value="/logout"/>">Sign out</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                <a class="btn btn-primary" href="<c:url value="/login"/>">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
