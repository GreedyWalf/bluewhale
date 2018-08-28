<#assign ctx=request.contextPath/>

<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
                <div>
                    <a href="#" class="btn btn-primary btn-sm" id="btnAdd">
                        <i class="icon-ok"></i>新增
                    </a>
                    <a href="#" class="btn btn-warning btn-sm" id="btnEdit">
                        <i class="icon-edit"></i>编辑
                    </a>
                    <a href="#" class="btn btn-danger btn-sm" id="btnDelete">
                        <i class="icon-trash"></i>删除
                    </a>
                </div>
                <table id="billCategoryTb" lay-filter="billCategoryTb"></table>
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
        var $ = layui.jquery;

        form.render();


        var table_data = [];
        //第一个实例
        table.render({
            elem: '#billCategoryTb',
            height: 315,
            url: '${ctx}/bill/getBillCategoryList',
            page: true,
            cols: [[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'categoryName', title: '账单类别'},
                {field: 'createBy', title: '创建人'},
                {field: 'createTime', title: '创建时间'},
                {field: 'lastModifyBy', title: '修改人'},
                {field: 'lastModifyTime', title: '修改时间'}
            ]],
            id: 'billCategoryTb',
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
        var selectBillCategories = [];
        table.on('checkbox(billCategoryTb)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectBillCategories.push(obj.data.categoryId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectBillCategories.push(table_data[i].categoryId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectBillCategories.length; i++) {
                        if (selectBillCategories[i] === obj.data.categoryId) {
                            selectBillCategories.remove(i);
                        }
                    }
                } else {
                    selectBillCategories = [];
                }
            }

            console.log("-->>");
            console.log(selectBillCategories);
        });

        //新增
        $("#btnAdd").on('click',function(){
            layer.open({
                type: 2,
                area: ['400px', '500px'],
                content: '${ctx}/user/addUser',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    alert($("#name").val());
                    alert(iframeIndex);
                },
                btn2: function (index, layero) {
                    console.log(layero);
                    alert(index);
                }, cancel: function () {
                    alert("取消了");
                }
            });
        });


        //编辑
        $("#btnEdit").on('click',function(){
            alert('edit');
        });

        //删除
        $("#btnDelete").on('click',function () {
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