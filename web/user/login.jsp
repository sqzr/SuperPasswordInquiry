<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<jsp:include page="/comm/header.jsp"/>
<body>
<!-- Form area -->
<div class="admin-form">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <!-- Widget starts -->
                <div class="widget worange">
                    <!-- Widget head -->
                    <div class="widget-head">
                        <i class="icon-lock"></i> 登录
                    </div>

                    <div class="widget-content">
                        <div class="padd">
                            <!-- Login form -->
                            <form class="form-horizontal" action="index.html">
                                <!-- Email -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="username">账号:</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" id="username" placeholder="账号">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="password">密码:</label>
                                    <div class="col-lg-9">
                                        <input type="password"  class="form-control" id="password" placeholder="密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-3" ></label>
                                    <div class="col-lg-9">
                                            <input type="checkbox" id="keepMeLoggedInd" value="true"> 保持登陆
                                    </div>
                                </div>
                                <div class="col-lg-9 col-lg-offset-2">
                                    <button id="login" type="button" class="btn btn-danger">登录</button>
                                    <button type="reset" class="btn btn-default">重置</button>
                                </div>
                                <br />
                            </form>

                        </div>
                    </div>

                    <div class="widget-foot">
                        for fun
                    </div>
                    <script type="text/javascript">
                        $(document).ready(function(){//这个就是jQueryready,它就像C语言的main 所有操作包含在它里面
                            $("#login").mousedown(function(){
                                login();
                            });
                        });
                        function myAlert(alertMessage,type)
                        {
                            $._messengerDefaults = {
                                extraClasses: 'messenger-fixed messenger-on-top',
                                theme: 'air'
                            }
                            $.globalMessenger().post({message:alertMessage,type:type,showCloseButton:true,id:'loginInfo'});
                        }
                        function login(){ //函数 login();
                            var params = {
                                username : $("#username").val(),
                                password : $("#password").val(),
                                keepMeLoggedInd : $("#keepMeLoggedInd").attr("checked")
                            };
                            $.ajax({
                                type: "post",
                                url : "/ajax/login.html",
                                dataType:'json',
                                data: JSON.stringify(params),
                                beforeSend: function () {
                                    $("#login").attr('disabled',"true");
                                },
                                complete: function () {
                                    $("#login").removeAttr("disabled");
                                },
                                contentType: 'application/json',
                                success: function(data){//如果调用成功
                                    if(data.status == false){
                                        //登陆失败
                                        myAlert("用户名或密码错误","error");
                                    }else if(data.status == true){
                                        //登陆成功
                                        myAlert("登陆成功,跳转中..", "info");
                                        window.setTimeout(function(){location.href='/main.html'},1000);
                                    }
                                }
                            });
                        }
                    </script>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/comm/footer.jsp"/>