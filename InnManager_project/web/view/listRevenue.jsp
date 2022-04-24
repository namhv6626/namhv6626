<%-- 
    Document   : listRevenue
    Created on : Mar 9, 2022, 12:32:28 PM
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
        <link rel="stylesheet" href="../../css/listRevenue.css">
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
                        <a href="../room/list">
                            <i class='bx bx-home'></i>
                            <span class="link_name">Phòng trọ</span>
                        </a>
                        <i class='bx bxs-chevron-down arrow'></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="../room/list">Phòng trọ</a></li>
                        <li><a href="../room/add">Thêm phòng</a></li>
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
                        <li><a class="link_name" href="../cusotmer/list">Khách trọ</a></li>
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
                    <a href="list">
                        <i class='bx bx-history'></i>
                        <span class="link_name">Doanh thu</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="list">Doanh thu</a></li>
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
                <span class="text">Doanh Thu</span>
            </div>
        </section>
        <section class="content-section">
            <div class="detail">
                <div class="detail-container-search">
                    <form action="list" method="GET" class="detail-date" id="form-date">
                        <div class="search-type">
                            <label for="formDate">Từ ngày:</label>
                            <input type="date" id="formDate" name="fromDate" class="search-date"
                                   <c:if test="${requestScope.fromDate != null}">
                                       value="${requestScope.fromDate}" 
                                   </c:if>
                                   onchange="submitForm()">
                        </div>
                        <div class="search-type">
                            <label for="toDate">Đến ngày:</label>
                            <input type="date" id="toDate" name="toDate" class="search-date"
                                   <c:if test="${requestScope.toDate != null}">
                                       value="${requestScope.toDate}" 
                                   </c:if>
                                   onchange="submitForm()">
                        </div>
                    </form>
                    <form action="list" method="GET" class="detail-search">
                        <input type="text" name="search" class="search-input" placeholder="Nhập tên phòng...">
                        <button type="submit" class="search-button">
                            <i class='bx bx-search'></i>
                        </button>
                    </form>
                </div>
                <div class="table-responsive detail-container-information">
                    <table class="table detail-information">
                        <tr class="table-light">
                            <th>&ensp;</th>
                            <th class="text-center">Tên phòng</th>
                            <th class="text-center">Giá tiền</th>
                            <th class="text-center">Từ ngày</th>
                            <th class="text-center">Đến ngày</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                        <c:forEach items="${requestScope.listPaymentHistoryPaging}" var="ph">
                            <tr>
                                <td class="text-center align-middle column-information"><span class="button-link btn_open"
                                                                                              onclick="openFunctionModal(${ph.id})"><i class='bx bx-info-circle'></i></span></td>
                                <td class="text-center align-middle">${ph.payment.contract.room.name}</td>
                                <td class="text-center align-middle">${ph.bill.priceLong + ph.payment.contract.contractDetail.priceLong}</td>
                                <td class="text-center align-middle">${ph.fromDate}</td>
                                <td class="text-center align-middle">${ph.toDate}</td>
                                <td class="text-center"><a class="btn btn-primary" href="../room/information?id=${ph.payment.contract.room.id}&status=${ph.payment.contract.status?"1":"0"}" role="button">Xem phòng</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:set var="revenue" scope="page" value="0"/>
                    <c:forEach items="${requestScope.listPaymentHistory}" var="ph">
                        <c:set var="revenue" scope="page" value="${pageScope.revenue + ph.bill.priceLong + ph.payment.contract.contractDetail.priceLong}"/>
                    </c:forEach>
                    <div class="result-price" style="width: 30%;">
                        <table class="table">
                            <tr>
                                <td>Tổng:</td>
                                <td>${pageScope.revenue}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="paging">
                <div class="paging-content">
                    <c:if test="${requestScope.indexPage != 1}">
                        <div class="paging-container left">
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${1}"><span>&lt;&lt;</span></i></a>
                            </button>
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.indexPage - 1}"><span>&lt;</span></a>
                            </button>
                        </div>
                    </c:if>
                    <div class="paging-container center">
                        <c:if test="${requestScope.indexPage - 1 > 0}">
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.indexPage - 1}">${requestScope.indexPage - 1}</a>
                            </button>
                        </c:if>
                        <button class="paging-button" type="button">
                            <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.indexPage}"><span>${requestScope.indexPage}</span></a>
                        </button>
                        <c:if test="${requestScope.indexPage + 1 <= requestScope.numberPage}">
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.indexPage + 1}"><span>${requestScope.indexPage + 1}</span></a>
                            </button>
                        </c:if>
                    </div>
                    <c:if test="${requestScope.indexPage != requestScope.numberPage}">
                        <div class="paging-container right">
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.indexPage + 1}">&gt;</a>
                            </button>
                            <button class="paging-button" type="button">
                                <a href="list?fromDate=${requestScope.fromDate}&toDate=${requestScope.toDate}&search=${requestScope.search}&page=${requestScope.numberPage}">&gt;&gt;</a>
                            </button>
                        </div>
                    </c:if>
                </div>
            </div>
        </section>
        <div class="model-delete">
            <div class="model-form hidden">
                <button class="btn_close" type="button" onclick="closeFunctionModal()">&times;</button>
            </div>
            <div class="overlay hidden"></div>
        </div>


        <script>
            let arrow = document.querySelectorAll(".arrow");
            for (var i = 0; i < arrow.length; i++) {
                arrow[i].addEventListener("click", (e) => {
                    let arrowParent = e.target.parentElement.parentElement;
                    arrowParent.classList.toggle("showMenu");
                });
            }

            function submitForm() {
                const form_date = document.getElementById('form-date');
                form_date.submit();
            }

            const modal = document.querySelector('.model-form');
            const overlay = document.querySelector('.overlay');
            //const btnOpen = document.querySelectorAll('.btn_open');


            //*Modal window
            function openFunctionModal(idPayment) {
                modal.classList.remove('hidden');
                overlay.classList.remove('hidden');
                $.ajax({
                    url: "/InnManager_project/loadPaymentHistory",
                    type: "get",
                    data: {
                        idPayment: idPayment
                    },
                    success: function (response) {
                        modal.innerHTML += response;
                    }
                });
            }

            function closeFunctionModal() {
                modal.classList.add('hidden');
                overlay.classList.add('hidden');
                var child = document.getElementById("model-change");
                modal.removeChild(child);
            }


            const closeModal = function () {
                modal.classList.add('hidden');
                overlay.classList.add('hidden');
                var child = document.getElementById("model-change");
                modal.removeChild(child);
            };

            //            for (var i = 0; i < btnOpen.length; i++) {
            //                btnOpen[i].addEventListener('click', openModal);
            //            }
            //            const btnClose = document.querySelector('.btn_close');
            //            btnClose.addEventListener('click', closeModal);
            overlay.addEventListener('click', closeModal);

            document.addEventListener('keydown', function (e) {
                if (e.key === 'Escape' && !modal.classList.contains('hidden')) {
                    closeModal();
                }
            });
        </script>
        <!--JQuery-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>

</html>
