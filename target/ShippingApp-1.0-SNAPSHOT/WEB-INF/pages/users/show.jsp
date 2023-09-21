<%-- 
    Document   : create
    Created on : Aug 2, 2023, 10:41:11 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="col-md-7 col-lg-8 mx-auto">
    <!-- Breadcrumbs -->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <c:forEach items="${breadcrumbs}" var="breadcrumb">
                <c:choose>
                    <c:when test="${not empty breadcrumb.url}">
                        <li class="breadcrumb-item"><a class="text-decoration-none " href="<c:url value="${breadcrumb.url}"/>">${breadcrumb.label}</a></li>
                        </c:when>
                        <c:otherwise>
                        <li class="breadcrumb-item active" aria-current="page">${breadcrumb.label}</li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
        </ol>
    </nav>
    <!-- Breadcrumbs -->

    <div class="py-2 text-center">
        <h2>Thêm người dùng</h2>
    </div>
  
    <div class="bg-white rounded-10 p-3 content d-flex">
        <div class="avartar w-25">
            <img src="${user.avatar}" id="selected-image" height="" alt="alt"  width=100%"/>

        </div>
        <div class="form-update w-75 ps-5">
             <c:url value="/users/${user.userID}" var="action" />
            <form:form method="post" modelAttribute="user" action="${action}" enctype="multipart/form-data">
                <form:errors path="*" element="div" cssClass="alert alert-danger text-danger"/>
                <form:hidden path="userID" />
                <form:hidden path="avatar" />
                <form:hidden path="password" />
                <form:hidden path="createdDate" />
                <div class="row g-3 mb-4">
                    <div class="col-12">
                        <label for="code" class="form-label">Tên đăng nhập</label>
                        <form:input type="text" class="form-control col-6" path="username" id=""
                                    placeholder="Nhập tên đăng nhập" readonly="true" ></form:input>
                        <form:errors path="username" element="div" cssClass="text-danger"/>
                    </div>

                    <div class="col-12">
                        <label for="email" class="form-label">Email</label>
                        <form:input type="email" class="form-control" name="value" path="email"
                                    placeholder="Nhập email..."></form:input>
                        <form:errors path="email" element="div" cssClass="text-danger"/>
                    </div>
                    
                    <div class="col-12">
                        <label for="name" class="form-label">Họ và tên</label>
                        <form:input type="text" class="form-control" name="value" path="name"
                                    ></form:input>
                        <form:errors path="name" element="div" cssClass="text-danger"/>
                    </div>

                    <div class="col-12">
                        <label for="phone" class="form-label">Số điện thoại</label>
                        <form:input type="phone" class="form-control" path="phone" id="phone"
                                    placeholder="Nhập số điện thoại..."></form:input>
                        <form:errors path="phone" element="div" cssClass="text-danger"/>
                    </div>

                    <div class="col-12">
                        <label for="type" class="form-label">Vai trò</label>
                        <form:select path="role" class="form-select" id="">
                            <option value="ROLE_ADMIN">ADMIN</option>
                            <option value="ROLE_SHIPPER">SHIPPER</option>
                            <option value="ROLE_USER">USER</option>
                        </form:select>
                    </div>

                    <div class="col-12">
                        <label for="type" class="form-label">Trạng thái</label>
                        <form:select path="active" class="form-select" id="">
                            <option value="1">Đã xác nhận</option>
                            <option value="0">Chưa xác nhận</option>
                        </form:select>
                    </div>


                    <div class="col-12">
                        <button class="w-100 btn btn-primary btn-lg" type="submit">
                            Thêm người dùng
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="<c:url value="/javascript/utils/functions.js"/>"></script>
