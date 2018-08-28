<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>登录页</title>

    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css"/>


    <script src="${ctx}/assets/js/jquery-2.0.3.min.js"></script>
    <script src="${ctx}/assets/js/custom/md5.js"></script>
    <script src="${ctx}/assets/js/custom/common.js"></script>
    <script src="${ctx}/assets/layui/layui.js"></script>
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="green">🐳</i>
                            <span class="blue">Blue</span>
                            <span class="white">Whale</span>
                        </h1>
                        <h4 class="blue">&copy; Company Name</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        请填写登录信息
                                    </h4>

                                    <div class="space-6"></div>

                                    <form id="loginForm">
                                        <fieldset>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="userName" type="text" class="form-control"
                                                               placeholder="用户名"/>
                                                        <i class="icon-user"></i>
                                                    </span>
                                            </label>

                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="password" type="password" class="form-control"
                                                               placeholder="密码"/>
                                                        <i class="icon-lock"></i>
                                                    </span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>

                                                <button id="btnLogin" type="button"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                    <div class="social-or-login center">
                                        <span class="bigger-110">第三方登录</span>
                                    </div>

                                    <div class="social-login center">
                                        <a class="btn btn-primary">
                                            <i class="icon-facebook"></i>
                                        </a>

                                        <a class="btn btn-info">
                                            <i class="icon-twitter"></i>
                                        </a>

                                        <a class="btn btn-danger">
                                            <i class="icon-google-plus"></i>
                                        </a>
                                    </div>
                                </div>
                                <!-- /widget-main -->

                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="#" onclick="show_box('forgot-box'); return false;"
                                           class="forgot-password-link">
                                            <i class="icon-arrow-left"></i>
                                            忘记密码
                                        </a>
                                    </div>

                                    <div>
                                        <a href="#" onclick="show_box('signup-box'); return false;"
                                           class="user-signup-link">
                                            去注册
                                            <i class="icon-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="icon-key"></i>
                                        Retrieve Password
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        Enter your email and to receive instructions
                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input type="email" class="form-control" placeholder="Email"/>
                                                        <i class="icon-envelope"></i>
                                                    </span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="icon-lightbulb"></i>
                                                    Send Me!
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /widget-main -->

                                <div class="toolbar center">
                                    <a href="#" onclick="show_box('login-box'); return false;"
                                       class="back-to-login-link">
                                        Back to login
                                        <i class="icon-arrow-right"></i>
                                    </a>
                                </div>
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /forgot-box -->

                        <!-- 注册 -->
                        <div id="signup-box" class="signup-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header green lighter bigger">
                                        <i class="icon-group blue"></i>
                                        新用户注册
                                    </h4>

                                    <div class="space-6"></div>
                                    <p> 请填写您的详细信息: </p>

                                    <form id="registerForm">
                                        <fieldset>
                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="email" type="email" class="form-control"
                                                               placeholder="邮箱"/>
                                                        <i class="icon-envelope"></i>
                                                    </span>
                                            </label>

                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="userName" type="text" class="form-control"
                                                               placeholder="用户名"/>
                                                        <i class="icon-user"></i>
                                                    </span>
                                            </label>

                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="password" type="password" class="form-control"
                                                               placeholder="密码"/>
                                                        <i class="icon-lock"></i>
                                                    </span>
                                            </label>

                                            <label class="block clearfix">
                                                    <span class="block input-icon input-icon-right">
                                                        <input name="rePassword" type="password" class="form-control"
                                                               placeholder="再次确认密码"/>
                                                        <i class="icon-retweet"></i>
                                                    </span>
                                            </label>

                                            <label class="block">
                                                <input name="isAgree" type="checkbox" class="ace"/>
                                                <span class="lbl">
                                                        我接受
                                                        <a href="#">用户协议</a>
                                                    </span>
                                            </label>

                                            <div class="space-24"></div>

                                            <div class="clearfix">
                                                <button id="btnResert" type="reset"
                                                        class="width-30 pull-left btn btn-sm">
                                                    <i class="icon-refresh"></i>
                                                    重置
                                                </button>

                                                <button id="btnRegister" type="button"
                                                        class="width-65 pull-right btn btn-sm btn-success">
                                                    注册
                                                    <i class="icon-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>

                                <div class="toolbar center">
                                    <a href="#" onclick="show_box('login-box'); return false;"
                                       class="back-to-login-link">
                                        <i class="icon-arrow-left"></i>
                                        已经注册，去登陆
                                    </a>
                                </div>
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <!-- /signup-box -->
                    </div>
                    <!-- /position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /.main-container -->

<script type="text/javascript">
    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }

    layui.use(['layer'], function () {
        var layer = layui.layer;
        var $ = layui.$;

        //Enter键登录
        $("#loginForm input[name='userName'], #loginForm input[name='password']").on('keydown', function () {
            if (event.keyCode === 13) {
                event.returnValue = false;
                event.cancel = true;
                $("#btnLogin").click();
            }
        });

        $("#btnLogin").on('click', function (e) {
            e.preventDefault();
            var userName = $("input[name='userName']").val();
            var password = $("input[name='password']").val();
            if (!userName || !password) {
                layer.msg("用户名或密码不能为空！", {icon: 2, time: 2000});
                return false;
            }

            var param = {'userName': userName, 'password': password};
            $.post("${ctx}/ajaxLogin", param, function (data) {
                if (data && data.status === "SUCCESS") {
                    if (data.resultMap && data.resultMap['url']) {
                        layer.msg("登录成功！", {icon: 1, time: 2000}, function () {
                            window.open(data.resultMap['url'], '_self');
                        });
                    }
                } else {
                    console.log(data.msg);
                    layer.msg(data.msg || "登录失败！", {icon: 2, time: 2000});
                }
            });
        });

        $("#btnRegister").on('click', function (e) {
            e.preventDefault();
            var formParam = $("#registerForm").serialiseJson();
            console.log(formParam);
            var password = formParam['password'];
            var rePassword = formParam['rePassword'];
            if (password !== rePassword) {
                layer.msg("两次密码输入不一致，请确认后再输入！", {icon: 2, time: 2000});
                return false;
            }

            formParam['password'] = hex_md5(password);
            formParam['rePassword'] = hex_md5(rePassword);
            $.post('${ctx}/register', formParam, function (data) {
                if (data && data.status === "SUCCESS") {
                    if (data.resultMap && data.resultMap['url']) {
                        layer.msg("注册成功！", {icon: 1, time: 2000}, function () {
                            window.open(data.resultMap['url'], '_self');
                        });
                    }
                } else {
                    layer.msg(data.msg || '注册失败！', {icon: 2, time: 2000});
                }
            });
        });
    });
</script>
</body>

</html>