<%-- 
    Document   : notification
    Created on : Mar 11, 2022, 11:16:39 AM
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
        <link rel="stylesheet" href="../cssUser/notification.css">
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- boostrap link -->
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
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
                <li
                    <c:if test="${sessionScope.listPaymentToPay.size() > 0}">
                        class="bill"
                    </c:if>
                    >
                    <a href="notification">
                        <i class='bx bx-compass'></i>
                        <span class="link_name">Hóa đơn</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="notification">Hóa đơn</a></li>
                    </ul>
                </li>
                <li>
                    <a href="revenue">
                        <i class='bx bx-history'></i>
                        <span class="link_name">Doanh thu</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="revenue">Doanh thu</a></li>
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
                <span class="text">Thông báo</span>
            </div>
        </section>
        <section class="content-section">
            <div class="detail">
                <div class="table-responsive detail-container-information">
                    <table class="table detail-information">
                        <tr class="table-light">
                            <th>&ensp;</th>
                            <th class="text-center">Tên phòng</th>
                            <th class="text-center">Giá tiền</th>
                            <th class="text-center">Từ ngày</th>
                            <th class="text-center">Đến ngày</th>
                        </tr>
                        <c:forEach items="${sessionScope.listPaymentToPay}" var="pm">
                            <tr>
                                <td class="text-center align-middle column-information"><span class="button-link btn_open" onclick="openFunctionModal(${pm.id})"><i
                                            class='bx bx-info-circle'></i></span></td>
                                <td class="text-center align-middle">${pm.contract.room.name}</td>
                                <td class="text-center align-middle">${pm.bill.priceLong + pm.contract.room.roomType.priceLong}</td>
                                <td class="text-center align-middle">${pm.fromDate}</td>
                                <td class="text-center align-middle">${pm.toDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
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
            const modal = document.querySelector('.model-form');
            const overlay = document.querySelector('.overlay');
//            const btnOpen = document.querySelectorAll('.btn_open');


            //*Modal window
//            const openModal = function (e) {
//                e.preventDefault();
//                modal.classList.remove('hidden');
//                overlay.classList.remove('hidden');
//            };
            function openFunctionModal(idPayment) {
                modal.classList.remove('hidden');
                overlay.classList.remove('hidden');
                $.ajax({
                    url: "/InnManager_project/addBill",
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
