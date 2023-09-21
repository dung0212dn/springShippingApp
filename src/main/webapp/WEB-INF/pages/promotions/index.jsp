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
<c:url value="/promotions" var="action"/>
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

    <div class="section-title pb-3 fs-3 fw-bold">Khuyến mãi</div>
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
                        <input type="text" class="form-control shadow-none w-100" name="kw" id="" placeholder="Nhập mã khuyến mãi...">
                        <button class="btn btn-light" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>
                </form:form>
            </div>
            <div class="col-md-2">

            </div>
            <div class="col-md-3">
                <form:form method="get" id="sortForm" modelAttribute="sortForm" action="${action}" >
                    <select class="form-select" name="sort" onchange="submitForm('sortForm')" id="sortOption">
                        <option value="codeAsc" ${sortOption eq 'codeAsc' ? 'selected' : ''}>Tăng dần bảng chữ cái</option>
                        <option value="codeDesc" ${sortOption eq 'codeDesc' ? 'selected' : ''}>Giảm dần bảng chữ cái</option>
                        <option value="valueAsc" ${sortOption eq 'valueAsc' ? 'selected' : ''}>Tăng dần giá trị khuyến mãi</option>
                        <option value="valueDesc" ${sortOption eq 'valueDesc' ? 'selected' : ''}>Giảm dần giá trị khuyến mãi</option>
                        <option value="expirationDateAsc" ${sortOption eq 'expirationDateAsc' ? 'selected' : ''}>Tăng dần ngày hết hạn</option>
                        <option value="expirationDateDesc" ${sortOption eq 'expirationDateDesc' ? 'selected' : ''}>Giảm dần ngày hết hạn</option>
                    </select>
                </form:form>
            </div>

           <div class="col-md-3 d-flex justify-content-end">
                <a class="btn text-white w-100" style="background-color: #6610F2" href="<c:url value="/promotions/create"/>"><span class="pe-3"><i class="fa-solid fa-plus"></i></span>Thêm khuyến mãi</a>
            </div>
        </div>
        <table class="table rounded table-borderless ">
            <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Mã khuyến mãi</th>
                    <th scope="col">Loại khuyến mãi</th>
                    <th scope="col">Giá trị khuyến mãi</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Ngày hết hạn</th>
                    <th scope="col">Ngày tạo mã</th>
                    <th scope="col" class="text-center">Tùy chỉnh</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${promotions}" var="p">
                    <tr>
                        <th scope="row">${p.promotionID}</th>
                        <td>${p.code}</td>
                        <td>${p.type}</td>
                        <td>${p.value}</td>
                        <td>${p.quantity}</td>
                        <td>${p.expirationDate}</td>
                        <td>${p.createdDate}</td>
                        <td class="d-flex">
                            <a type="button" href="<c:url value="/promotions/${p.promotionID}"></c:url>" class="w-50 mx-2 btn btn-success"><i class="fa-solid fa-pen-to-square"></i></a>
                            <button type="button" class="w-50 mx-2 btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-name="${p.code}" data-bs-id="${p.promotionID}" data-bs-type="mã khuyến mãi"><i class="fa-solid fa-trash-can"></i></button>
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
                            <c:url value="/promotions" var="previousLink">
                                <c:param name="page" value="${currentPage - 1}"/>
                                <c:if test="${not empty sortOption}">
                                    <c:param name="sort" value="${sortOption}"/>
                                </c:if>
                            </c:url>
                            <a class="page-link" href="${previousLink}">Trước</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="1" end="${countPromotions}" var="i">
                    <c:url value="/promotions" var="pageLink">
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
                    <c:when test="${currentPage == countPromotions}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Tiếp</a>
                        </li>
                    </c:when>
                    <c:otherwise>  <li class="page-item">
                            <c:url value="/promotions" var="nextLink">
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
                <c:url value="/promotions/delete" var="actionDelete">

                </c:url>
                <form:form method="post" action="${actionDelete}" >
                    <input value="" type="hidden" name="promotionId">
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