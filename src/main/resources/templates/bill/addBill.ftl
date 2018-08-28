<#assign ctx=request.contextPath/>
<link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css"/>

<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
                <form style="margin-top: 20px;margin-left: 20px;" class="layui-form" id="billForm">
                    <div class="layui-form-item">
                        <label class="layui-form-label">账单金额</label>
                        <div class="">
                            <input style="width: 300px" type="text" name="money" required lay-verify="required"
                                   placeholder="请输入金额" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">记录方式</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <input type="radio" name="recordStyle" value="dateTime" title="时间点" checked="checked"
                                   lay-filter="recordStyle">
                            <input type="radio" name="recordStyle" value="dateDiff" title="时间段"
                                   lay-filter="recordStyle">
                        </div>
                    </div>

                    <div class="layui-form-item dateTime">
                        <label class="layui-form-label">账单日期</label>
                        <div class="layui-input-inline">
                            <input style="width: 300px" type="text" id="recordDate"
                                   name="recordDate" required lay-verify="required"
                                   placeholder="选择账单日期" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item dateDiff" style="display: none;">
                        <label class="layui-form-label">开始日期</label>
                        <div class="layui-input-inline">
                            <input style="width: 300px" type="text" id="recordStartDate"
                                   name="recordStartDate" required lay-verify="required"
                                   placeholder="选择开始日期" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item dateDiff" style="display: none;">
                        <label class="layui-form-label">结束日期</label>
                        <div class="layui-input-inline">
                            <input style="width: 300px" type="text" id="recordEndDate"
                                   name="recordEndDate" required lay-verify="required"
                                   placeholder="选择结束日期" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">账单类型</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <select name="billType" lay-verify="required">
                                <option value=""></option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">消费类别</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <select name="categoryId" lay-verify="required">
                                <option value=""></option>
                            <#list categoryList as billCategory>
                                <option value="${billCategory.categoryId}">${billCategory.categoryName}</option>
                            </#list>
                            </select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">是否报销</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <input type="radio" name="isReimburse" value="0" title="不可报销" checked>
                            <input type="radio" name="isReimburse" value="1" title="可报销">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">账单状态</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <input type="radio" name="status" value="UNFINISH" title="未完结" checked>
                            <input type="radio" name="status" value="FINISHED" title="已完结">
                        </div>
                    </div>

                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">备注</label>
                        <div style="width: 300px" class="layui-input-inline">
                            <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                        </div>
                    </div>

                    <div class="layui-form-item" style="margin-top: 20px;">
                        <label class="layui-form-label"></label>
                        <div class="layui-input-block">
                            <input type="reset" id="btnCancel" value="重置" class="layui-btn layui-btn-primary"/>
                            <input type="button" id="btnSubmit" value="确定" class="layui-btn layui-btn-normal"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/assets/layui/layui.js"></script>

<script>
    layui.use(['form', 'laydate', 'layer'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var layer = layui.layer;

        var $ = layui.jquery;
        var parent$ = window.parent.layui.jquery;

        form.render();
        laydate.render({
            elem: '#recordDate',
            value: new Date()
        });

        laydate.render({
            elem: '#recordStartDate',
            value: new Date()
        });

        laydate.render({
            elem: '#recordEndDate',
        });


        //监听记录方式切换效果
        form.on('radio(recordStyle)', function (data) {
            debugger
//            console.log(data.elem); //得到checkbox原始DOM对象
//            console.log(data.elem.checked); //是否被选中，true或者false
//            console.log(data.value); //复选框value值，也可以通过data.elem.value得到
//            console.log(data.othis); //得到美化后的DOM对象
            var $rdRecordStyle = $(data.elem);
            var value = $rdRecordStyle.val();
            if (value === "dateDiff") {
                $("div.dateDiff").show();
                $("div.dateTime").hide();
            } else {
                $("div.dateDiff").hide();
                $("div.dateTime").show();
            }
        });

        $("#btnCancel").on('click', function () {
            $("div.dateDiff").hide();
            $("div.dateTime").show();
        });

        $("#btnSubmit").on('click', function () {
            var serialiseJson = $("#billForm").serialiseJson();
            console.log(serialiseJson);

            layer.msg("恭喜你，又花出一笔钱啦。。", {icon: 1, time: 2000}, function () {
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            });
        });


        //为jquery添加原型方法，用于表单序列化
        $.fn.serialiseJson = function () {
            var serializeObj = {};
            var array = this.serializeArray();
            $(array).each(function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.name);
                    } else {
                        serializeObj[this.name] = [serializeObj[this.name], this.value]
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });

            return serializeObj;
        };
    });
</script>