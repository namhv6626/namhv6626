<%-- 
    Document   : home
    Created on : Mar 10, 2022, 7:59:17 PM
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
        <link rel="stylesheet" href="../css/home.css">
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- boostrap link -->
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .statistical .left .statistical-model{
                width: 200px;
                height: 200px;
                background-image: conic-gradient(red 0deg,red ${requestScope.deg}deg, blue ${requestScope.deg}deg);
                border-radius: 50%;
            }

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
                    <a href="home">
                        <i class='bx bx-grid-alt'></i>
                        <span class="link_name">Thống kê chung</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="#">Thống kê chung</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="room/list">
                            <i class='bx bx-home'></i>
                            <span class="link_name">Phòng trọ</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="room/list">Phòng trọ</a></li>
                        <li><a href="room/add">Thêm phòng</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="roomtype/list">
                            <i class='bx bx-book-alt'></i>
                            <span class="link_name">Loại phòng</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="roomtype/list">Loại phòng</a></li>
                        <li><a href="roomtype/add">Thêm phòng</a></li>
                    </ul>
                </li>
                <li>
                    <a href="customer/list">
                        <i class='bx bx-pie-chart-alt-2'></i>
                        <span class="link_name">Khách trọ</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="cusotmer/list">Khách trọ</a></li>
                    </ul>
                </li>
                <li 
                    <c:if test="${sessionScope.listPaymentToCreate.size() > 0}">
                        class="notification"
                    </c:if>
                    >
                    <a href="notification">
                        <i class='bx bx-line-chart'></i>
                        <span class="link_name">Thông báo</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="notification">Thông báo</a></li>
                    </ul>
                </li>
                <li
                    <c:if test="${sessionScope.listPaymentToPay.size() > 0}">
                        class="bill"
                    </c:if>
                    >
                    <a href="bill/add">
                        <i class='bx bx-compass'></i>
                        <span class="link_name">Hóa đơn</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="bill/add">Hóa đơn</a></li>
                    </ul>
                </li>
                <li>
                    <a href="revenue/list">
                        <i class='bx bx-history'></i>
                        <span class="link_name">Doanh thu</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="revenue/list">Doanh thu</a></li>
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
                        <li><a href="setting/service">Tiền dịch vụ</a></li>
                        <li><a href="setting/conduct">Tiền đồ dùng</a></li>
                    </ul>
                </li>
                <li>
                    <a href="../logout">
                        <i class='bx bx-exit'></i>
                        <span class="link_name">Đăng xuất</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="../logout">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <section class="home-section">
            <div class="home-content">
                <span class="text">Thống kê chung</span>
            </div>
        </section>
        <section class="content-section">
            <div class="row detail">
                <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                    <div class="content-container">
                        <a href="room/list">
                            <p class="detail-name">Tổng phòng trọ</p>
                            <div class="detail-infomation">
                                <h3 class="information-number">${requestScope.totalRoom}</h3>
                                <i class='bx bx-home'></i>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                    <div class="content-container">
                        <a href="room/list?status=1">
                            <p class="detail-name">Phòng đang thuê</p>
                            <div class="detail-infomation">
                                <h3 class="information-number">${requestScope.totalHireRoom}</h3>
                                <i class='bx bx-home-smile'></i>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                    <div class="content-container">
                        <a href="room/list?status=0">
                            <p class="detail-name">Phòng trống</p>
                            <div class="detail-infomation">
                                <h3 class="information-number">${requestScope.totalEmptyRoom}</h3>
                                <i class='bx bx-home-circle'></i>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                    <div class="content-container">
                        <a href="customer/list?status=1">
                            <p class="detail-name">Khách hàng thuê</p>
                            <div class="detail-infomation">
                                <h3 class="information-number">${requestScope.totalHireCustomer}</h3>
                                <i class='bx bx-body'></i>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 detail-content">
                    <div class="content-container">
                        <a href="revenue/list">
                            <p class="detail-name">Tổng doanh thu</p>
                            <div class="detail-infomation">
                                <h3 class="information-number">${requestScope.revenue}</h3>
                                <i class='bx bx-line-chart-down'></i>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row statistical">
                <div class="statistical-tittle">
                    <h2>Biểu đồ phòng trọ</h2>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 left">
                    <div class="statistical-model"></div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 right">
                    <div class="statistical-description">
                        <table>
                            <tr class="part">
                                <td class="color-part">
                                    <div style="background-color: red;" class="colorr"></div>
                                </td>
                                <td class="content-part">Phòng đang thuê: ${requestScope.percent}%</td>
                            </tr>
                            <tr class="part">
                                <td class="color-part">
                                    <div style="background-color: blue;" class="colorr"></div>
                                </td>
                                <td class="content-part">Phòng trống: ${100 - requestScope.percent}%</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row statistical">
                <div class="statistical-tittle">
                    <h2>Biểu đồ doanh thu</h2>
                </div>
                <form action="home" method="GET" id="search-input-form" style="padding-left: 40px;">
                    Năm: <select name="year" onchange="submitSearchForm()">
                        <c:forEach items="${requestScope.listYear}" var="year">
                        <option
                            <c:if test="${requestScope.year == year}">
                                selected="selected"
                            </c:if>
                            value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </form>
                <div style="height: 200px; width: 700px; padding-left: 30px;">
                    <canvas id="myChart" width="100%" height="100%" style="padding-bottom: 20px;"></canvas>
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
            
            const ctx = document.getElementById('myChart').getContext('2d');
            const myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    datasets: [{
                            label: 'Revenue of month',
                            data: [{x: 'Tháng 1', y: ${requestScope.listRevenue.get(0)}},
                                {x: 'Tháng 2', y: ${requestScope.listRevenue.get(1)}},
                                {x: 'Tháng 3', y: ${requestScope.listRevenue.get(2)}},
                                {x: 'Tháng 4', y: ${requestScope.listRevenue.get(3)}},
                                {x: 'Tháng 5', y: ${requestScope.listRevenue.get(4)}},
                                {x: 'Tháng 6', y: ${requestScope.listRevenue.get(5)}},
                                {x: 'Tháng 7', y: ${requestScope.listRevenue.get(6)}},
                                {x: 'Tháng 8', y: ${requestScope.listRevenue.get(7)}},
                                {x: 'Tháng 9', y: ${requestScope.listRevenue.get(8)}},
                                {x: 'Tháng 10', y: ${requestScope.listRevenue.get(9)}},
                                {x: 'Tháng 11', y: ${requestScope.listRevenue.get(10)}},
                                {x: 'Tháng 12', y: ${requestScope.listRevenue.get(11)}}
                            ]
                        }]
                },
                options: {
                    scales: {
                        y: {
                            type: 'linear',
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>

</html>



