/*****
 * 定义一个Content控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
window.onload = function () {
    Vue.prototype.axios = 'axios';
    var app = new Vue({
        el: '#app',
        data: {
            bgColor: false
        },
        methods: {
            //鼠标移入方法
            enter:function () {
                this.bgColor = true;
            },
            //鼠标移出方法
            leave:function(){
                this.bgColor = false;
            },
        }
    });
}