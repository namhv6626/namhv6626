<%-- 
    Document   : listRoom
    Created on : Mar 1, 2022, 10:19:30 AM
    Author     : firem
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- Created by CodingLab |www.youtube.com/CodingLabYT-->
<html lang="en" dir="ltr">

    <head>
        <meta charset="UTF-8">
        <!--<title> Drop Down Sidebar Menu | CodingLab </title>-->
        <link rel="stylesheet" href="../../css/listRoom.css">
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- boostrap link -->
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .sidebar .nav-links .notification::before{
                content: "${sessionScope.listPaymentToCreate.size()}";
                width: 20px;
                height: 20px;
                border-radius: 50%;
                position: absolute;
                background-color: red;
                color: white;
                top: 50%;
                right: 20px;
                transform: translateY(-50%);
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.5s ease;
            }
            
            .sidebar .nav-links .bill::before{
                content: "${sessionScope.listPaymentToPay.size()}";
                width: 20px;
                height: 20px;
                border-radius: 50%;
                position: absolute;
                background-color: red;
                color: white;
                top: 50%;
                right: 20px;
                transform: translateY(-50%);
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.5s ease;
            }

            @media (max-width: 768px){
                .sidebar .nav-links .notification::before{
                    width: 20px;
                    height: 20px;
                    border-radius: 50%;
                    position: absolute;
                    background-color: red;
                    color: white;
                    top: 50%;
                    right: 5px;
                    transform: translateY(-50%);
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
                
                .sidebar .nav-links .bill::before{
                    width: 20px;
                    height: 20px;
                    border-radius: 50%;
                    position: absolute;
                    background-color: red;
                    color: white;
                    top: 50%;
                    right: 5px;
                    transform: translateY(-50%);
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
            }
        </style>
    </head>

    <body>
        <div class="sidebar">
            <div class="logo-details">
                <i class='bx bxl-c-plus-plus'></i>
                <span class="logo_name">InnManager</span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="../home">
                        <i class='bx bx-grid-alt'></i>
                        <span class="link_name">Thống kê chung</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../home">Thống kê chung</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="list">
                            <i class='bx bx-home'></i>
                            <span class="link_name">Phòng trọ</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="list">Phòng trọ</a></li>
                        <li><a href="add">Thêm phòng</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="../roomtype/list">
                            <i class='bx bx-book-alt'></i>
                            <span class="link_name">Loại phòng</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="../roomtype/list">Loại phòng</a></li>
                        <li><a href="../roomtype/add">Thêm phòng</a></li>
                    </ul>
                </li>
                <li>
                    <a href="../customer/list">
                        <i class='bx bx-pie-chart-alt-2'></i>
                        <span class="link_name">Khách trọ</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../customer/list">Khách trọ</a></li>
                    </ul>
                </li>
                <li
                    <c:if test="${sessionScope.listPaymentToCreate.size() > 0}">
                        class="notification"
                    </c:if>
                    >
                    <a href="../notification">
                        <i class='bx bx-line-chart'></i>
                        <span class="link_name">Thông báo</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../notification">Thông báo</a></li>
                    </ul>
                </li>
                <li
                    <c:if test="${sessionScope.listPaymentToPay.size() > 0}">
                        class="bill"
                    </c:if>
                    >
                    <a href="../bill/add">
                        <i class='bx bx-compass'></i>
                        <span class="link_name">Hóa đơn</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../bill/add">Hóa đơn</a></li>
                    </ul>
                </li>
                <li>
                    <a href="../revenue/list">
                        <i class='bx bx-history'></i>
                        <span class="link_name">Doanh thu</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../revenue/list">Doanh thu</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="">
                            <i class='bx bx-book-alt'></i>
                            <span class="link_name">Thiết lập</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="">Thiết lập</a></li>
                        <li><a href="../setting/service">Tiền dịch vụ</a></li>
                        <li><a href="../setting/conduct">Tiền đồ dùng</a></li>
                    </ul>
                </li>
                <li>
                    <a href="../../logout">
                        <i class='bx bx-exit'></i>
                        <span class="link_name">Đăng xuất</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../../logout">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <section class="home-section">
            <div class="home-content">
                <span class="text">Danh sách phòng trọ</span>
            </div>
        </section>
        <section class="content-section">
            <div class="detail-container-search">
                <form action="list" method="GET" class="detail-search">
                    <input type="text" name="search" class="search-input" placeholder="Nhập tên phòng...">
                    <button type="submit" class="search-button">
                        <i class='bx bx-search'></i>
                    </button>
                </form>
            </div>
            <div class="search">
                <div class="search-content">
                    <form class="search-container" id="search-input-form" action="list" method="GET">
                        <div class="search-type">
                            <label for="type">Kiểu phòng:</label>
                            <select name="type" id="type" onchange="submitSearchForm()">
                                <option value="-1">--Chọn kiểu phòng--</option>
                                <c:forEach items="${requestScope.listRoomType}" var="rt">
                                    <option 
                                        <c:if test="${requestScope.type == rt.id}">
                                            selected="selected"
                                        </c:if>
                                        value="${rt.id}">${rt.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="search-type">
                            <label for="floor">Tầng:</label>
                            <select name="floor" id="floor" onchange="submitSearchForm()">
                                <option value="-1">--Chọn tầng--</option>
                                <c:forEach items="${requestScope.listFloor}" var="fl">
                                    <option 
                                        <c:if test="${requestScope.floor == fl}">
                                            selected="selected"
                                        </c:if>
                                        value="${fl}">Tầng ${fl}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="search-type">
                            <label for="status">Tình trạng:</label>
                            <select name="status" id="status" onchange="submitSearchForm()">
                                <option value="-1">--Chọn loại tình trạng--</option>
                                <option 
                                    <c:if test="${requestScope.status == 1}">
                                        selected="selected"
                                    </c:if>
                                    value="1">Đang thuê</option>
                                <option 
                                    <c:if test="${requestScope.status == 0}">
                                        selected="selected"
                                    </c:if>
                                    value="0">Đang trống</option>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row detail">
                <c:if test="${requestScope.listRoom != null && !requestScope.listRoom.isEmpty()}">
                    <c:forEach items="${requestScope.listRoom}" var="rm">
                        <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                            <div class="content-container">
                                <span class="detail-name-inn">${rm.name}</span>
                                <c:if test="${rm.status}">
                                    <span class="detail-status">Đang thuê</span>
                                </c:if>
                                <c:if test="${!rm.status}">
                                    <span class="detail-status" style="color: red;">Đang trống</span>
                                </c:if>
                                <div class="detail-image">
                                    <img src="../../image/phong_tro.png" alt="" class="img-inn">
                                </div>
                                <c:if test="${rm.status}">
                                    <c:forEach items="${requestScope.listContract}" var="ct">
                                        <c:if test="${rm.id == ct.room.id && ct.status}">
                                            <span class="detail-name-customer">${ct.customer.name}</span>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${!rm.status}">
                                    <span class="detail-name-customer" style="visibility: hidden">Đang trống</span>
                                </c:if>
                                <c:if test="${rm.status}">
                                    <button type="button" class="detail-infomation">
                                        <a href="information?id=${rm.id}&status=1">
                                            <i class='bx bx-info-circle'></i>
                                            <span class="information-click">Chi tiết</span>
                                        </a>
                                    </button>
                                </c:if>
                                <c:if test="${!rm.status}">
                                    <div class="detail-button">
                                        <button type="button" class="detail-infomation">
                                            <a href="../contract/add?id=${rm.id}">
                                                <i class='bx bx-plus'></i>
                                                <span class="information-click">Thuê</span>
                                            </a>
                                        </button>
<!--                                        <button type="button" class="detail-infomation">
                                            <a href="#">
                                                <i class='bx bx-trash'></i>
                                                <span class="information-click">Xóa</span>
                                            </a>
                                        </button>-->
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="paging">
                <div class="paging-content">
                    <c:if test="${requestScope.indexPage != 1}">
                        <div class="paging-container left">
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${1}"><span>&lt;&lt;</span></i></a>
                            </button>
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.indexPage - 1}"><span>&lt;</span></a>
                            </button>
                        </div>
                    </c:if>
                    <div class="paging-container center">
                        <c:if test="${requestScope.indexPage - 1 > 0}">
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.indexPage - 1}">${requestScope.indexPage - 1}</a>
                            </button>
                        </c:if>
                        <button class="paging-button" type="button">
                            <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.indexPage}"><span>${requestScope.indexPage}</span></a>
                        </button>
                        <c:if test="${requestScope.indexPage + 1 <= requestScope.numberPage}">
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.indexPage + 1}"><span>${requestScope.indexPage + 1}</span></a>
                            </button>
                        </c:if>
                    </div>
                    <c:if test="${requestScope.indexPage != requestScope.numberPage}">
                        <div class="paging-container right">
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.indexPage + 1}">&gt;</a>
                            </button>
                            <button class="paging-button" type="button">
                                <a href="list?type=${requestScope.type}&floor=${requestScope.floor}&status=${requestScope.status}&search=${requestScope.search}&page=${requestScope.numberPage}">&gt;&gt;</a>
                            </button>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>



        <script>
            let arrow = document.querySelectorAll(".arrow");
            for (var i = 0; i < arrow.length; i++) {
                arrow[i].addEventListener("click", (e) => {
                    let arrowParent = e.target.parentElement.parentElement;
                    arrowParent.classList.toggle("showMenu");
                });
            }

            function submitSearchForm() {
                document.getElementById("search-input-form").submit();
            }
        </script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>

</html>
