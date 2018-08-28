<#assign ctx=request.contextPath/>
<link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css"/>
<div class="row">
    <div class="col-xs-12">
        <form class="layui-form layui-form-pane" id="userForm">
            <div class="layui-form-item" style="margin-top: 20px;">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="男" title="男" checked="">
                        <input type="radio" name="sex" value="女" title="女">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" id="email" lay-verify="required" placeholder="请输入邮箱"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">手机</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <label class="layui-form-label">角色</label>
                    <div class="layui-input-block">
                        <select name="interest" lay-filter="aihao">
                            <option value=""></option>
                            <option value="0">超级管理员</option>
                            <option value="1">管理员</option>
                            <option value="2">游民</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">所在省</label>
                <div class="layui-input-inline">
                    <select name="province">
                        <option value="">请选择省</option>
                        <option value="安徽">安徽省</option>
                    </select>
                </div>

                <label class="layui-form-label">所在城市</label>
                <div class="layui-input-inline">
                    <select name="city">
                        <option value="">请选择市</option>
                        <option value="合肥">合肥</option>
                    </select>
                </div>

                <label class="layui-form-label">所在地区</label>
                <div class="layui-input-inline">
                    <select name="area">
                        <option value="">请选择县/区</option>
                        <option value="蜀山区">蜀山区</option>
                        <option value="庐阳区">庐阳区</option>
                        <option value="包河区">包河区</option>
                        <option value="高新区">高新区</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="${ctx}/assets/layui/layui.js"></script>

<script>
    layui.use(['form', 'layer', 'laypage', 'table'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var table = layui.table;
        var layPage = layui.laypage;
        var $ = layui.jquery;

        form.render();

    });
</script>