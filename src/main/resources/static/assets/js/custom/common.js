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