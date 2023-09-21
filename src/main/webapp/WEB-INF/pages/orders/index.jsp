<%-- 
    Document   : index
    Created on : Aug 2, 2023, 10:35:40 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<c:url value="/orders" var="action"/>
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

    <div class="section-title pb-3 fs-3 fw-bold">Đơn hàng</div>
    <c:if test="${not empty messageSuccess}">
        <div class="alert alert-success my-2">
            ${messageSuccess}
        </div>
    </c:if>
    <div class="bg-white rounded-10 p-3 content">
        <div class="content-header row p-2">
            <div class="col-md-4">
                <form:form method="get" action="${action}" >
                    <div class="p-0 d-flex">
                        <input type="text" class="form-control shadow-none w-100" name="kw" id="" placeholder="Nhập tên người tạo...">
                        <button class="btn btn-light" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>
                </form:form>
            </div>
            <div class="col-md-2">

            </div>
            <div class="col-md-3">
                <form:form method="get" id="sortForm" modelAttribute="sortForm" action="${action}" >
                    <select class="form-select" name="sort" onchange="submitForm('sortForm')" id="sortOption">
                        <option value="createdDateAsc" ${sortOption eq 'codeAsc' ? 'selected' : ''}>Tăng dần thời gian tạo</option>
                        <option value="createdDateAscDesc" ${sortOption eq 'codeDesc' ? 'selected' : ''}>Giảm dần thời gian tạo đơn</option>
                        <option value="IS_DELIVERED" ${sortOption eq 'IS_DELIVERED' ? 'selected' : ''}>Lọc theo đơn giao thành công</option>
                        <option value="IS_AUTIONING" ${sortOption eq 'IS_AUTION' ? 'selected' : ''}>Lọc theo đơn đang đấu giá</option>
                        <option value="IS_SHIPPING" ${sortOption eq 'IS_SHIPPING' ? 'selected' : ''}>Lọc theo đơn đang giao</option>
                        <option value="IS_CANCEL" ${sortOption eq 'IS_CANCEL' ? 'selected' : ''}>Lọc theo đơn bị hủy</option>
                    </select>
                </form:form>
            </div>

            <div class="col-md-3 d-flex justify-content-end">
                <a class="btn text-white w-100" style="background-color: #6610F2" href="<c:url value="/orders/create"/>"><span class="pe-3"><i class="fa-solid fa-plus"></i></span>Thêm khuyến mãi</a>
            </div>
        </div>
        <table class="table rounded table-borderless ">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Người tạo đơn</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Địa chỉ nhận hàng</th>
                    <th scope="col">Địa chỉ giao hàng</th>
                    <th scope="col">Ngày tạo đơn</th>
                    <th scope="col" class="text-center">Tùy chỉnh</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="o">
                    <tr>
                        <th scope="row">${o.orderID}</th>
                        <td>${o.userID.username}</td>
                        <c:choose>
                            <c:when test="${o.status == 'IS_CANCEL'}">
                                <td class=" text-danger fw-bold">ĐÃ HỦY</td>   
                            </c:when>
                            <c:when test="${o.status == 'IS_AUTIONING'}">
                                <td class=" text-primary fw-bold">ĐANG ĐẤU GIÁ</td>   
                            </c:when>
                            <c:when test="${o.status == 'IS_SHIPPING'}">
                                <td class=" text-warning fw-bold">ĐANG GIAO HÀNG</td>   
                            </c:when>
                            <c:otherwise><td class=" text-success fw-bold">THÀNH CÔNG</td>  </c:otherwise>
                        </c:choose>
                            <td style="max-width: 150px ;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;">${o.shippingAddress}</td>
                            <td style="max-width: 150px ;overflow: hidden; text-overflow: ellipsis;white-space: nowrap;">${o.billingAddress}</td>
                            <td>${o.createdDate}</td>
                            <td class="d-flex">
                                <a type="button" href="<c:url value="/orders/${o.orderID}/"></c:url>" class="w-50 mx-2 btn btn-success"><i class="fa-solid fa-pen-to-square"></i></a>
                                <button type="button" class="w-50 mx-2 btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-name="${p.code}" data-bs-id="${p.orderID}" data-bs-type="mã khuyến mãi"><i class="fa-solid fa-trash-can"></i></button>
                            </td>
                        </tr>
                </c:forEach>

            </tbody>
        </table>
        <nav aria-label="..." class="d-flex w-100 justify-content-end">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <li class="page-item disabled">
                            <a class="page-link" href="" tabindex="-1" aria-disabled="true">Trước</a>
                        </li>
                    </c:when>
                    <c:otherwise>  <li class="page-item">
                            <c:url value="/orders" var="previousLink">
                                <c:param name="page" value="${currentPage - 1}"/>
                                <c:if test="${not empty sortOption}">
                                    <c:param name="sort" value="${sortOption}"/>
                                </c:if>
                            </c:url>
                            <a class="page-link" href="${previousLink}">Trước</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="1" end="${countOrders}" var="i">
                    <c:url value="/orders" var="pageLink">
                        <c:param name="page" value="${i}"/>
                        <c:if test="${not empty sortOption}">
                            <c:param name="sort" value="${sortOption}"/>
                        </c:if>
                    </c:url>
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <li class="page-item active"><a class="page-link" href="${pageLink}">${i}</a></li> 
                            </c:when>
                            <c:when test="${Math.abs(currentPage - i) <= 2}">
                            <li class="page-item"><a class="page-link" href="${pageLink}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                            <li class="page-item disabled"><span class="page-link" href="${pageLink}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                </c:forEach>
                <c:choose>
                    <c:when test="${currentPage == countOrders}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Tiếp</a>
                        </li>
                    </c:when>
                    <c:otherwise>  <li class="page-item">
                            <c:url value="/orders" var="nextLink">
                                <c:param name="page" value="${currentPage + 1}"/>
                                <c:if test="${not empty sortOption}">
                                    <c:param name="sort" value="${sortOption}"/>
                                </c:if>
                            </c:url>
                            <a class="page-link" href="${nextLink}">Tiếp</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</div>
<!--  Modal form   -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <span></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <c:url value="/orders/delete" var="actionDelete">

                </c:url>
                <form:form method="post" action="${actionDelete}" >
                    <input value="" type="hidden" name="orderId">
                    <button type="submit" class="btn btn-danger">Xóa khuyến mãi</button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/javascript/utils/modal.js"/>"></script>
<script>
                        function submitForm(formId) {
                            document.getElementById(formId).submit();
                        }
</script>