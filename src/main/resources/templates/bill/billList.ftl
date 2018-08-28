<#assign ctx=request.contextPath/>

<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
                <div>
                    <a href="#" class="btn btn-primary btn-sm" id="btnAdd">
                        <i class="icon-ok"></i>小记一笔
                    </a>
                    <a href="#" class="btn btn-warning btn-sm" id="btnEdit">
                        <i class="icon-edit"></i>编辑
                    </a>
                    <a href="#" class="btn btn-danger btn-sm" id="btnDelete">
                        <i class="icon-trash"></i>删除
                    </a>
                </div>
                <table id="billTb" lay-filter="billTb"></table>
            </div>
        </div>
    </div>
</div>


<script>
    layui.use(['form', 'layer', 'laypage', 'table'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var table = layui.table;
        var $ = layui.jquery;

        form.render();

        var table_data = [];
        //第一个实例
        table.render({
            elem: '#billTb',
            height: 315,
            url: '${ctx}/bill/getBillList',
            page: true,
            cols: [[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'createBy', title: '创建人'},
                {field: 'createTime', title: '创建时间'},
                {field: 'lastModifyBy', title: '修改人'},
                {field: 'lastModifyTime', title: '修改时间'}
            ]],
            id: 'billTb',
            limits: [5, 10],
            where: {
                'keyword': ''
            },
            request: {
                pageName: "pageNum",
                limitName: "pageSize"
            }, done: function (res, curr, count) {
                table_data = res.data;
                console.log("数据渲染成功！");
            }
        });


        //存储选中行的userId
        var selectUserIds = [];
        table.on('checkbox(billTb)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectUserIds.push(obj.data.userId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectUserIds.push(table_data[i].userId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectUserIds.length; i++) {
                        if (selectUserIds[i] === obj.data.userId) {
                            selectUserIds.remove(i);
                        }
                    }
                } else {
                    selectUserIds = [];
                }
            }

            console.log("-->>");
            console.log(selectUserIds);
        });

        //新增人员
        $("#btnAdd").on('click', function () {
            layer.open({
                title: '小记一笔',
                type: 2,
                anim:1,
                area: ['500px', '600px'],
                content: '${ctx}/bill/addBill',
                resize: false,
                cancel: function () {
                    console.log("-->取消了");
                }
            });
        });


        //编辑人员
        $("#btnEdit").on('click', function () {
            alert('edit');
        });

        //删除人员
        $("#btnDelete").on('click', function () {
            alert('delete');
        });


        //数组添加remove方法
        Array.prototype.remove = function (dx) {
            if (isNaN(dx) || dx < this.length) {
                return false;
            }

            for (var i = 0, n = 0; i < this.length; i++) {
                if (this[i] != this[dx]) {
                    this[n++] = this[i];
                }
            }

            this.length -= 1;
        }
    });
</script>