<#assign ctx=request.contextPath/>

<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
                <div style="position: absolute;top:15px" id="btns">
                    <a href="#" class="btn btn-primary btn-sm" data-code="user:add">
                        <i class="icon-ok"></i>新增
                    </a>
                    <a href="#" class="btn btn-warning btn-sm" data-code="user:update">
                        <i class="icon-edit"></i>编辑
                    </a>
                    <a href="#" class="btn btn-danger btn-sm" data-code="user:delete">
                        <i class="icon-trash"></i>删除
                    </a>
                </div>
                <table id="userTb" lay-filter="userTb"></table>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form', 'layer', 'laypage', 'table'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var table = layui.table;
        var layPage = layui.laypage;


        form.render();

        //第一个实例
        table.render({
            elem: '#userTb',
            height: 315,
            url: '${ctx}/user/getUserList',
            page: true,
            cols: [[
                {field: 'userName', title: '用户名'},
                {field: 'password', title: '密码'},
                {field: 'email', title: '邮箱'},
                {field: 'phone', title: '手机'},
                {field: 'position', title: '位置'},
                {field: 'createBy', title: '创建人'},
                {field: 'createTime', title: '创建时间'},
                {field: 'lastModifyBy', title: '修改人'},
                {field: 'lastModifyTime', title: '修改时间'}
            ]],
            id: 'userTb',
            page: {
                layout: ['limit', 'count', 'prev', 'page', 'skip'],
                even: true,
                first: true,
                last: true,
                limit: 2
            },
            limits: [2, 5, 10],
            where: {
                'keyword': ''
            },
            request: {
                pageName: "pageNum",
                limitName: "pageSize"
            }, done: function (res, curr, count) {
                console.log("数据渲染成功！");
            }
        });
    });
</script>