<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/comm/header.jsp"/>
<body>
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

    <div class="conjtainer">
        <!-- Menu button for smallar screens -->
        <div class="navbar-header">
            <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse"
                    data-target=".bs-navbar-collapse" style="padding: 5px 10px;">
                <span>菜单</span>
            </button>
            <a href="/main.html" class="navbar-brand"></a>
        </div>
        <!-- Navigation starts -->
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">

            <ul class="nav navbar-nav">

                <!-- Upload to server link. Class "dropdown-big" creates big dropdown -->
                <li class="dropdown dropdown-big">
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-success"><i class="icon-cloud-upload"></i></span> 上传到云服务器</a>--%>
                    <!-- Dropdown -->

                </li>

                <!-- Sync to server link -->
                <li class="dropdown dropdown-big">

                </li>

            </ul>


            <!-- Links -->
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown pull-right">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-user"></i> <s:property value="#session.user.username"/> <b class="caret"></b>
                    </a>

                    <!-- Dropdown menu -->
                    <ul class="dropdown-menu">
                        <li><a href="/user/logout.html"><i class="icon-off"></i> 退出</a></li>
                    </ul>
                </li>

            </ul>
        </nav>
    </div>
</div>

<div class="container">
    <div class="row">

        <div class="search">
            <div id="searchdiv">
                <div class="col-xs-8 col-md-11">
                    <input id="keyword" class="form-control" type="text">
                    <s:token></s:token>
                </div>
                <div class="col-xs-3 col-md-1">
                    <button id="search" type="button" class="btn btn-danger">搜索</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-md-12" id="resbox" style="display: block;font-size: 15px;"></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#search").mousedown(function () {
            search();
        });
    });
    function myAlert(alertMessage, type) {
        $._messengerDefaults = {
            extraClasses: 'messenger-fixed messenger-on-bottom',
            theme: 'air'
        }
        $.globalMessenger().post({message: alertMessage, type: type, showCloseButton: true});
    }
    function search() { //函数 login();
        var params = {
            keyword: $("#keyword").val(),
        };
        $.ajax({
            type: "post",
            url: "/ajax/search.html",
            dataType: 'json',
            data: JSON.stringify(params),
            beforeSend: function () {
                $("#search").text("搜索中...").attr('disabled',"true");
            },
            complete: function () {
                $("#search").text("搜索").removeAttr("disabled");
            },
            contentType: 'application/json',
            success: function (data) {//如果调用成功
                if (data.status == "error") {
                    myAlert("没有检索到!", "error");
                } else if (data.status == "notlogon") {
                    myAlert("登陆超时!", "error");
                    window.setTimeout(function () {
                        location.href = '/user/login.html'
                    }, 1000);
                } else if (data.status == "success") {
                    /**
                     *  show the result
                     */
                    var rows = data.rows;
                    var tableHeader = ' <table id="resultTable" class="table"> <thead> <tr><th> 用户名 </th> <th> 密码 </th> <th> 邮箱</th> <th> 其它 </th><th> salt </th> <th> 来源 </th></tr> </thead> <tbody>';
                    var count = 0;
                    $.each(rows, function (index, item) {
                        if (count % 2 == 0) {
                            tableHeader += '<tr>';
                        } else {
                            tableHeader += '<tr class="success">';
                        }
                        count++;
                        tableHeader += '<td>' + item['username'] + '</td>';
                        tableHeader += '<td>' + item['password'] + '</td>';
                        tableHeader += '<td>' + item['email'] + '</td>';
                        tableHeader += '<td>' + item['other'] + '</td>';
                        tableHeader += '<td>' + item['salt'] + '</td>';
                        tableHeader += '<td>' + item['origin'] + '</td>';
                        tableHeader += '</tr>';
                    })
                    tableHeader += ' </tbody> </table>';
                    $('#resbox').hide();
                    $('#resbox').html(tableHeader);
                    $('#resbox').fadeIn("slow");
                    $('#info').hide();
                    $('#info').fadeIn("normal");
//                    $('#jsDb').data('resultCount', data.data.resultCount);
//                    $('#jsDb').data('nowLimit', data.data.currentLimit);
                }
            }
        });
    }
</script>
</body>
<jsp:include page="/comm/footer.jsp"/>