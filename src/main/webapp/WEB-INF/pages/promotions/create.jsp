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
        <h2>Thêm khuyến mãi</h2>
    </div>

    <div class="bg-white rounded-10 p-3 content">
        <c:url value="/promotions" var="action" />
        <form:form method="post" modelAttribute="promotion" action="${action}">
            <form:errors path="*" element="div" cssClass="alert alert-danger text-danger"/>
            <div class="row g-3 mb-4">
                <div class="col-12">
                    <label for="code" class="form-label">Mã khuyến mãi</label>
                    <div class="row">
                        <div class="col-8">
                            <form:input type="text" class="form-control col-6" path="code" id="code"
                                        placeholder="Nhấn tạo mã để tạo mã tự động"></form:input>
                            <form:errors path="code" element="div" cssClass="text-danger"/>
                            </div>
                            <div class="col-4">
                                <button class="w-100 btn btn-primary" type="button" onclick="setRandomValue(12)">
                                    Tạo mã khuyến mãi
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="col-12">
                        <label for="type" class="form-label">Loại khuyến mãi</label>
                    <form:select path="type" class="form-select" id="type">
                        <option value="percentage">Phần trăm khuyến mãi (1-100%)</option>
                        <option value="fixedValue">Giá giảm thẳng</option>
                    </form:select>
                </div>

                <div class="col-12">
                    <label for="value" class="form-label">Giá trị khuyến mãi</label>
                    <form:input type="text" class="form-control" name="value" path="value"
                                placeholder="Nhập giá trị khuyến mãi..."></form:input>
                        <form:errors path="value" element="div" cssClass="text-danger"/>
                    </div>

                    <div class="col-12">
                        <label for="quantity" class="form-label">Số lượt sử dụng </label>
                    <form:input type="number" class="form-control" path="quantity" id="quantity"
                                placeholder="Nhập số lượt sử dụng"></form:input>
                       <form:errors path="quantity" element="div" cssClass="text-danger"/>
                    </div>

                    <div class="col-12">
                        <label for="date" class="form-label">Ngày hết hạn</label>
                        <div class="col-5">
                            <div class="input-group date" id="datepicker">
                            <form:input type="text" class="form-control" path="expirationDate"
                                        id="expirationDate" placeholder="Chọn ngày hết hạn"></form:input>
                                <span class="input-group-append">
                                    <span class="input-group-text bg-light d-block">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                </span>
                            </div>
                             <form:errors path="expirationDate" element="div" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="col-12">
                        <button class="w-100 btn btn-primary btn-lg" type="submit">
                            Thêm sản phẩm
                        </button>
                    </div>
                </div>
        </form:form>
    </div>
</div>

<script src="<c:url value="/javascript/datepicker.js"/>"></script>
<script src="<c:url value="/javascript/utils/functions.js"/>"></script>
<script>
                                        function setRandomValue(length) {
                                            const randomString = generateRandomString(length);
                                            document.getElementById("code").value = randomString;
                                        }
</script>