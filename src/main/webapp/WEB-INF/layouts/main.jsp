<%-- 
    Document   : main
    Created on : Aug 1, 2023, 1:50:56 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script src="https://kit.fontawesome.com/cf8e4768f9.js" crossorigin="anonymous"></script>
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
    </head>
    <body class="" style="background-color: #f9f9f9 !important;">
        <tiles:insertAttribute name="header"/>
        <div class="bg-base min-vh-100 d-grid">
            <div class="d-flex h-100  ">
                <div class="">
                    <tiles:insertAttribute name="sidebar"/>
                </div>
                 <div class="pt-4 ps-3 w-100 h-100">
                <tiles:insertAttribute name="content"/>
                 </div>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </body>
</html>
