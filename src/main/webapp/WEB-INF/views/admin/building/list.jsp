<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>

<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
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
                    <a href="#">Home</a>
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

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12 ">
                            <div class="widget-box ui-sortable-handle" id="widget-box-1">
                                <div class="widget-header">
                                    <h5 class="widget-title">Tìm Kiếm</h5>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>

                                <div class="widget-body"
                                     style="display: block;font-family: 'Times New Roman', Times, serif;">
                                    <div class="widget-main">
                                        <form:form id="listForm" modelAttribute="buildingSearch"
                                                   action="${buildingListURL}" method="Get">
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <div class="col-xs-6">
                                                            <label class="name">Tên Tòa Nhà</label>
                                                                <%--                                                                    <input type="text" class="form-control" name="name" value="">--%>
                                                            <form:input path="name" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label class="name">Diện Tích Sàn</label>
                                                            <form:input class="form-control" path="floorArea"/>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <div class="col-xs-2">
                                                            <label class="name">Quận</label>
                                                            <form:select class="form-control" path="district">
                                                                <form:option value="">---Chọn Quận---</form:option>
                                                                <form:options items="${districts}"></form:options>
                                                            </form:select>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class="name">Phường</label>
                                                            <form:input path="ward" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class="name">Đường</label>
                                                            <form:input path="street" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <div class="col-xs-4">
                                                            <label class="name">Số Tầng Hầm</label>
                                                            <form:input path="numberOfBasement" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class="name">Hướng</label>
                                                            <form:input path="direction"
                                                                        class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <label class="name">Hạng</label>
                                                            <form:input type="number" path="level"
                                                                        class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <div class="col-xs-3">
                                                            <label class="name">Diện Tích Từ</label>
                                                            <form:input path="areaFrom" class="form-control"/>

                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class="name">Diện Tích Đến</label>
                                                            <form:input path="areaTo" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class="name">Giá Thuê Từ</label>
                                                            <form:input path="rentPriceFrom" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <label class="name">Giá Thuê Đến</label>
                                                            <form:input path="rentPriceTo" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <div class="col-xs-5">
                                                            <label class="name">Tên Quản Lý</label>
                                                            <form:input path="managerName" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <label class="name">Số Điện Thoại Quản lý</label>
                                                            <form:input path="managerPhone" class="form-control"/>
                                                        </div>
                                                        <div class="col-xs-2">
                                                            <security:authorize access="hasRole('MANAGER')">
                                                                <label class="name">Nhân Viên Phụ Trách</label>
                                                                <form:select class="form-control" path="staffId">
                                                                    <form:option
                                                                            value="">---Chọn Nhân Viên Phụ Trách---</form:option>
                                                                    <form:options items="${listStaffs}"/>
                                                                </form:select>
                                                            </security:authorize>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 ">
                                                    <div class="col-xs-6 checkbox-inline">
                                                        <form:checkboxes items="${buildingType}" path="typeCode"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <button type="button" class="btn btn-success btn-xs"
                                                                id="btnSearchBuilding">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                 height="16" fill="currentColor" class="bi bi-search"
                                                                 viewBox="0 0 16 16">
                                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                                            </svg>
                                                            Tìm Kiếm
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                        </form:form>
                                    </div>
                                </div>
                                <div class="pull-right">
                                    <a href="/admin/building-edit">
                                        <button class="btn btn-info" title="Thêm Tòa Nhà">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                                <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                                <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                            </svg>
                                        </button>
                                    </a>
                                    <button class="btn btn-danger" title="Xóa Tòa Nhà" id="btnDeleteBuilding">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                    <div class="col-xs-12">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <table id="tableList"
                                       style="margin: 3em 0 1.5em;margin-left: 0;margin-right: 0;font-family: 'Times New Roman', Times, serif;"
                                       class="table  table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace">
                                                <span class="lbl"></span>
                                            </label>
                                        </th>
                                        <th>Tên Tòa Nhà</th>
                                        <th>Địa Chỉ</th>
                                        <th>Số Tầng Hầm</th>
                                        <th>Tên Quản Lý</th>
                                        <th>Số Điện Thoại Quản Lý</th>
                                        <th>Diện Tích Sàn</th>
                                        <th>Diện Tích Trống</th>
                                        <th>Diện Tích Thuê</th>
                                        <th>Phí Dịch Vụ</th>
                                        <th>Phí Môi Giới</th>
                                        <th>Thao Tác</th>

                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="item" items="${buildingDTO}">
                                        <tr>
                                            <td class="center">
                                                <label class="pos-rel">
                                                    <input type="checkbox" class="ace" name="checkList"
                                                           value="${item.id}">
                                                    <span class="lbl"></span>
                                                </label>
                                            </td>
                                            <td>${item.name}</td>
                                            <td> ${item.street}, ${item.ward}, ${item.district} </td>
                                            <td>${item.numberOfBasement}</td>
                                            <td>${item.managerName}</td>
                                            <td>${item.managerPhone}</td>
                                            <td>${item.floorArea}</td>
                                            <td></td>
                                            <td>${item.rentArea}</td>
                                            <td>${item.serviceFee}</td>
                                            <td>${item.brokerageFee}</td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group">

                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <button class="btn btn-xs btn-success" title="Giao Tòa Nhà "
                                                                onclick="assignmentBuilding(${item.id})">
                                                            <i class="glyphicon glyphicon-list bigger-120"></i>
                                                        </button>
                                                    </security:authorize>
                                                    <a class="btn btn-xs btn-info" title="Sửa Tòa Nhà"
                                                       href="/admin/building-edit-${item.id}">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <button class="btn btn-xs btn-danger"
                                                                onclick="deleteBuilding(${item.id})"
                                                                title="Xóa Tòa Nhà">
                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                        </button>
                                                    </security:authorize>

                                                </div>
                                                <div class="hidden-md hidden-lg">
                                                    <div class="inline pos-rel">
                                                        <button class="btn btn-minier btn-primary dropdown-toggle"
                                                                data-toggle="dropdown" data-position="auto">
                                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                        </button>

                                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                            <li>
                                                                <a href="#" class="tooltip-info" data-rel="tooltip"
                                                                   title=""
                                                                   data-original-title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
                                                                </a>
                                                            </li>

                                                            <li>
                                                                <a href="#" class="tooltip-success"
                                                                   data-rel="tooltip"
                                                                   title="" data-original-title="Edit">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
                                                                </a>
                                                            </li>

                                                            <li>
                                                                <a href="#" class="tooltip-error" data-rel="tooltip"
                                                                   title="" data-original-title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div><!-- /.row -->
                </div><!-- /.page-content -->
            </div>
        </div><!-- /.main-content -->


        <%--                //scroll cuộn lên đầu trang--%>
        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->

</div>
<%--        class:model sẽ tu động ẩn đi và fade là ẩn 1 cách chậm  --%>
<div class="modal fade" id="assignmentBuildingModal" role="dialog"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh Sách Nhân Viên</h4>
            </div>
            <div class="modal-body " var="item" items="${buildingDTO}">
                <table class="table  table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th> Tên Nhân Viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="building" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnassingmentBuilding">Giao Tòa Nhà
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.2.1.1.min.js"></script>

<script>
    function assignmentBuilding(buildingId) {
        $('#buildingId').val(buildingId);
        $('#assignmentBuildingModal').modal();
        loadStaff(buildingId);
    }

    function loadStaff(buildingid) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingid + '/staffs', // sử dụng biến URL ở đây
            // data: JSON.stringify(data),
            // contentType: "application/json",
            dataType: "json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + '" class="check-box-element"' + (item.checked ? 'checked' : '') + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                // Thêm vào phần tử table body
                $('#staffList tbody').html(row);
                console.info("success");
            },
            error: function (respond) {
                console.log("failed");
                window.location.href = '<c:url value="/admin/building-list?message=error"/>';
                console.log(respond);
            }
        });
    }

    $('#btnassingmentBuilding').click(function (e) {
        e.preventDefault();
        console.log("Button clicked");
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if (data['staffs'] != '') {
            assignments(data);
        } else {
            console.log("fault");
        }
        console.log("oke");
    });

    function assignments(data) {
        $.ajax({
            type: "Post",
            url: "${buildingAPI}/" + 'assignments', // sử dụng biến URL ở đây
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                console.log("success");
                alert("Assignment Successful ");
                window.location.href = '<c:url value="/admin/building-list?message=success"/>';
            },
            error: function (respond) {
                alert("Assignment failed ");
                window.location.href = '<c:url value="/admin/building-list?message=error"/>';
                console.log(respond);
            }
        });
    }

    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        $('#listForm').submit()
    });


    function deleteBuilding(id) {
        if (confirm("Do you want delete this building ? ")) {
            var buildingId = [id];
            deleteBuildings(buildingId)
        }
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        if (confirm("Do you want delete this building ?")) {
            var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuildings(buildingIds);
        }
    });

    function deleteBuildings(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data, // sử dụng biến URL ở đây
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                console.log(response);
                alert("Delete Success")
                window.location.href = '<c:url value="/admin/building-list?message=success "/>';
            },
            error: function (response) {
                console.log(response.responseText);
                alert("Delete fail")
                window.location.href = '<c:url value="/admin/building-list?message=error"/>';
            }
        });
    }

</script>
</body>
</html>
