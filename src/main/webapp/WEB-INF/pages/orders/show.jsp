<%-- 
    Document   : create
    Created on : Aug 2, 2023, 10:41:11 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="container ">
    <!-- Breadcrumbs -->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <c:forEach items="${breadcrumbs}" var="breadcrumb">
                <c:choose>
                    <c:when test="${not empty breadcrumb.url}">
                        <li class="breadcrumb-item"><a class="text-decoration-none" href="<c:url value="${breadcrumb.url}"/>">${breadcrumb.label}</a></li>
                        </c:when>
                        <c:otherwise>
                        <li class="breadcrumb-item active" aria-current="page">${breadcrumb.label}</li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
        </ol>
    </nav>
    <!-- Breadcrumbs -->

    <div class="section-title pb-3 fs-3 fw-bold">Chi tiết đơn hàng</div>
    <div class="bg-white rounded-10 p-3 content">

        <div class="content-header row p-2">
            <h4 class="heading-order fw-bold">Thông tin đơn hàng</h4>
            <hr py-2>
            <div class="col-md-6">
                <table class="w-100">
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Mã đơn hàng:</th>
                        <td class="col-md-8">${orders.orderInfo.orderID}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Người tạo đơn:</th>
                        <td class="col-md-8">${orders.orderInfo.userID.username}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Email:</th>
                        <td class="col-md-8">${orders.orderInfo.userID.email}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Số điện thoại:</th>
                        <td class="col-md-8">${orders.orderInfo.userID.phone}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Địa chỉ nhận đơn:</th>
                        <td class="col-md-8">${orders.orderInfo.shippingAddress}</td>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-4">Địa chỉ giao hàng:</th>
                        <td class="col-md-8">${orders.orderInfo.billingAddress}</td>            
                    </tr>
                </table>
            </div>
            <div class="col-md-6">
                <table class="w-100">
                    <tr class="row pb-1 w-100">
                        <th class="col-md-6">Người giao hàng:</th>
                        <td class="col-md-6">${orders.orderInfo.chosenShipperID.username}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-6">Email người giao hàng:</th>
                        <td class="col-md-6">${orders.orderInfo.chosenShipperID.email}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-6">Số điện thoại người giao hàng:</th>
                        <td class="col-md-6">${orders.orderInfo.chosenShipperID.phone}</th>            
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-6">Trạng thái đơn hàng:</th>
                            <c:choose>
                                <c:when test="${orders.orderInfo.status == 'IS_CANCEL'}">
                                <td class="col-md-6 text-danger fw-bold">ĐÃ HỦY</td>   
                            </c:when>
                            <c:when test="${orders.orderInfo.status == 'IS_AUTIONING'}">
                                <td class="col-md-6 text-primary fw-bold">ĐANG ĐẤU GIÁ</td>   
                            </c:when>
                            <c:when test="${orders.orderInfo.status == 'IS_SHIPPING'}">
                                <td class="col-md-6 text-warning fw-bold">ĐANG GIAO HÀNG</td>   
                            </c:when>
                            <c:otherwise><td class="col-md-6 text-success fw-bold">THÀNH CÔNG</td>  </c:otherwise>
                        </c:choose>         
                    </tr>
                    <tr class="row pb-1 w-100">
                        <th class="col-md-6">Thời gian tạo đơn:</th>
                        <td class="col-md-6">${orders.orderInfo.createdDate}</td>            
                    </tr>
                </table>
            </div>
        </div>
        <hr class="py-1">
        <div class="content-header row p-2">
            <h4 class="heading-order fw-bold">Chi tiết đơn hàng</h4>
            <hr py-2>
            <div class="col-md-6">
                <table class="w-100">
                    <tr class="row pb-1 w-100">
                        <th class="col-md-8">Tên sản phẩm</th>
                        <th class="col-md-4">Số lượng sản phẩm</th>            
                    </tr>
                    <c:forEach var="details" items="${orders.orderDetails}">
                        <c:set var="detail" value="${details.value}" />
                        <tr class="row pb-1 w-100">
                            <td class="col-md-8">${detail.productName}</th>
                            <td class="col-md-4">x ${detail.quantity}</th>            
                        </tr>
                    </c:forEach>


                </table>
                <div class="row pt-4 w-100">
                    <h4 class="col-md-8">Tổng tiền:</h4>
                    <h4 class="col-md-4 fw-bold text-danger">${total} </h4>            
                </div>
            </div>

        </div>
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