<%-- 
    Document   : editCustomer
    Created on : Mar 12, 2022, 11:21:53 AM
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
        <link rel="stylesheet" href="../../css/editCustomer.css">
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
                    <a href="list">
                        <i class='bx bx-pie-chart-alt-2'></i>
                        <span class="link_name">Khách trọ</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="list">Khách trọ</a></li>
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
                <span class="text">Cập nhật thông tin khách hàng</span>
            </div>
        </section>
        <section class="content-section">
            <div class="detail">
                <div class="detail-container">
                    <c:if test="${requestScope.customer.gender}">
                        <div class="detail-avata">
                            <img src="../../image/male.png" alt="">
                        </div>
                    </c:if>
                    <c:if test="${!requestScope.customer.gender}">
                        <div class="detail-avata">
                            <img src="../../image/female.png" alt="">
                        </div>
                    </c:if>
                </div>
                <div class="detail-container">
                    <form class="detail-information" action="edit" method="POST">
                        <div class="detail-name">
                            <input type="text" name="name" id="name" value="${requestScope.customer.name}">
                            <td><i class='bx bx-edit-alt'></i></td>
                        </div>
                        <table class="table">
                            <tr>
                                <td>Giới tính</td>
                                <td class="input-radio">
                                    <input type="radio" 
                                           <c:if test="${requestScope.customer.gender}">
                                               checked="checked"
                                           </c:if>
                                               name="gender" id="" value="1">Male
                                    <input type="radio" 
                                           <c:if test="${!requestScope.customer.gender}">
                                               checked="checked"
                                           </c:if>
                                               name="gender" id="" value="0">Female
                                </td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>Ngày sinh</td>
                                <td><input type="date" name="dob" id="" value="${requestScope.customer.dob}"></td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>Số điện thoại</td>
                                <td><input type="text" name="phone" id="" value="${requestScope.customer.phone}"></td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>CMND/CCCD</td>
                                <td><input type="text" name="identity" id="" value="${requestScope.customer.identity}"></td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email" id="" value="${requestScope.customer.email}"></td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>Nơi ở</td>
                                <td><input type="text" name="address" id="" value="${requestScope.customer.address}"></td>
                                <td><i class='bx bx-edit-alt'></i></td>
                            </tr>
                            <tr>
                                <td>Ngày thuê</td>
                                <td>${requestScope.customer.hireDate}</td>
                            </tr>
                            <tr>
                                <td>Tên phòng</td>
                                <td>${requestScope.customer.room.name}</td>
                            </tr>
                            <tr>
                                <td>Tên tài khoản</td>
                                <td>${requestScope.customer.account.username}</td>
                            </tr>
                            <tr>
                                <td>Mật khẩu</td>
                                <td>${requestScope.customer.account.password}</td>
                            </tr>
                            <tr>
                                <td>Trạng thái</td>
                                <td>
                                    <c:if test="${requestScope.customer.status}">
                                        <span style="color: green">Đang thuê</span>
                                    </c:if>
                                    <c:if test="${!requestScope.customer.status}">
                                        <span style="color: red">Dừng thuê</span>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                        <div class="detail-submit">
                            <input type="hidden" name="idCustomer" value="${requestScope.customer.id}">
                            <input class="btn btn-primary" type="submit" value="Cập nhật">
                            <a class="btn btn-danger" href="list" role="button">Hủy</a>
                        </div>
                    </form>
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


            function changeWidth() {
                let inputName = document.getElementById("name");
                inputName.style.width = inputName.value.length;
            }
        </script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>

</html>
