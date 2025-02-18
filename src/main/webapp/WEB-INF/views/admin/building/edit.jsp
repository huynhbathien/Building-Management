<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="builidngAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm Toà Nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="/admin/building-list">Home</a>
                </li>
                <li class="active">Quản Lý Toà Nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Danh Sách Tòa Nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="buildingEdit" id="listForm" action="${buildingEditURL}" method="Get">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal " role="form" id="form-edit">
                            <div class="col-xs-12 ">
                                <div class="form-group row">
                                    <label class="col-xs-2 col-form-label"> Tên Tòa Nhà</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 ">
                                <div class="form-group row">
                                    <Label class="col-xs-2 col-form-label"> Quận</Label>
                                    <div class="col-xs-2">
                                        <form:select path="district" class="form-control">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districts}"></form:options>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2 col-form-label">Phường</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="ward"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Đường</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="street"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Kết Cấu</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="structure"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Số Tầng Hầm</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Diện Tích Sàn</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="floorArea"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Hướng</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Hạng</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="level"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Diện Tích Thuê</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="rentArea"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <dic class="form-group row">
                                    <label class="col-xs-2">Giá Thuê</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="rentPrice"/>
                                    </div>
                                </dic>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Mô Tả Giá</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="rentPriceDescription"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Phí Dịch Vụ</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="serviceFee"/>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Phí Ô Tô</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="carFee"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Phí Mô Tô</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="motoFee"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Phí Ngoài Giờ</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="overtimeFee"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Tiền Điện</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="electricityFee"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label f class="col-sm-2">Đặt Cọc</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="deposit"/>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Thanh Toán</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="payment"/>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Thời Hạn Thuê</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="rentTime"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Thời Gian Trang Trí</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="decorationTime"/>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Tên Quản Lý</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="managerName"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Số Điện Thoại Quản Lý</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="managerPhone"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Phí Môi Giới</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="brokerageFee"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-sm-2">Ghi Chú</label>
                                    <div class="col-xs-10">
                                        <form:input class="form-control" path="note"/>
                                    </div>
                                </div>
                            </div>
                                <%--                            <div class="col-xs-12">--%>
                                <%--                                <div class="form-group row">--%>
                                <%--                                    <label class="col-sm-2">Hình Đại Diện</label>--%>
                                <%--                                    <div class="col-xs-10">--%>
                                <%--                                        <input type="text" class="form-control">--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>
                                <%--                            </div>--%>
                            <div class="col-xs-12">
                                <div class="form-group row">
                                    <label class="col-xs-2">Loại Tòa Nhà</label>
                                    <form:checkboxes items="${buildingType}" path="typeCode" id="checkboxBuildingType"/>
                                </div>
                            </div>
                            <div class="col-xs-12 ">
                                <div class="form-group row">
                                    <label class="col-xs-2"></label>
                                    <div class="col-xs-10">
                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type="button" class="btn-primary" id="addOrUpdateBuilding">Cập Nhật
                                                Tòa Nhà
                                            </button>
                                            <button type="button" class="btn-primary" id="btnCancel">Hủy Thao Tác
                                            </button>
                                        </c:if>
                                        <c:if test="${ empty buildingEdit.id}">
                                            <button type="button" class="btn-primary" id="addOrUpdateBuilding">Thêm
                                                Mới
                                            </button>
                                            <button type="button" class="btn-primary" id="btnCancel"> Hủy Thao Tác
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>
                            <!-- PAGE CONTENT ENDS -->
                                <%--                    <
                                /div><!-- /.col -->--%>
                        </form>

                    </div>
                    <!-- /.row -->

                </form:form>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
    $('#addOrUpdateBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data["" + v.name + ""] = v.value;
            } else {
                typeCode.push(v.value);
            }
        });
        data['typeCode'] = typeCode;
        $.ajax({
            type: "POST",
            url: "${builidngAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                console.log(response);
                if (response.id) {
                    alert("Update Success");
                } else {
                    alert("Add Success");
                }
                window.location.href = '<c:url value="/admin/building-list"/>';
            },
            error: function (response) {
                console.log(response);
                alert("Add fail");
                window.location.href = '<c:url value="/admin/building-edit?message=error"/>';
            }
        });
    });
    $('#btnCancel').click(function (e) {
        window.location.href = "/admin/building-list";
    });
</script>
</body>
</html>
