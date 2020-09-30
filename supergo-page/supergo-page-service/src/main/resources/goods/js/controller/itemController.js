/*****
 * 定义一个Content控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
window.onload = function () {
    Vue.prototype.axios = 'axios';
    var app = new Vue({
        el:'#app',
        data:{
            specificationItems:{},  //记录用户选中的规格信息
            num:1,                    //记录用户购买的数量信息
            sku:{},                   //用户选中的sku对象
            showAddress: {display:'none'},        //鼠标是否移入配送地址栏
            n: 1,                                            //监听配送地址选择
            address: '',
            titleArr:["广西","桂林市","秀峰区","丽君街道"],
            citysArr:[
                ["广西","上海","天津","重庆","北京"],
                ["崇左市","南宁市","柳州市","桂林市","百色市"],
                ["秀峰区","灌阳县","恭城瑶族自治县","荔浦县","叠彩区"],
                ["秀峰街道","甲山街道","丽君街道"]
            ],
            leftWidth: 0,                                   //配置色底线，左边边线宽度
            rightWidth: 0,                                  //配置色底线，右边边线宽度
        },
        methods:{
            //加入购物车实现
            addCart:function () {
                alert('恭喜您，已经将商品加入到购物车！ID：'+app.sku.id+',价格是：'+app.sku.price+',购买了'+app.num);

                //发送远程请求，加入购物车操作
                let url = 'http://localhost:18093/cart/add.shtml?itemId='+app.sku.id+'&num='+app.num;

                //加入购物车  {'withCredentials':true}:发送客户端的Cookie数据
                // axios.get(url,{'withCredentials':true}).then(function (response) {
                //     if(response.data.success){
                //         location.href='http://localhost:18093/cart.html';
                //     }else{
                //         alert(response.data.message);
                //     }
                // })
            },

            //判断2个Map结构是否匹配
            matchObject:function (map1,map2) {
                //循环一个map
                for(var key in map1){
                    //获取当前循环的map的key,再获取当前key的值
                    let m1v = map1[key];
                    //获取另一个map相同key的值
                     let m2v = map2[key];
                    //不同，则返回false，表明不相同
                    if(m1v!=m2v){
                        return false;
                    }
                }
                return true;
            },

            //每次点击，判断当前选中的规格属于哪个SKU的
            searchSku:function () {
                //循环所有的SKU
                for(var i=0;i<itemList.length;i++){
                    //判断当前被循环的SKU的spec值和选中的规格值是否相同
                    if(this.matchObject(this.specificationItems,JSON.parse(itemList[i].spec))){
                        //如果相同，则把当前被循环的SKU赋值给app.sku  深克隆
                        this.sku=JSON.parse(JSON.stringify(itemList[i]));
                        return;
                    }
                }

                //该商品下架了
                this.sku={'title':'该商品断货了','price':0,'id':0,spec:{}};
            },


            //定义方法获取SKU数据
            loadSku:function () {
                //从集合中获取第1个记录
                //深克隆
                this.sku =JSON.parse(JSON.stringify(itemList[0]));
                //将spce赋值给选中的specificationItems
                this.specificationItems=JSON.parse(this.sku.spec);
            },

            //创建方法，实现用户购买数量增加操作
            addNum:function (x) {
                this.num+=x;
                if(this.num<1){
                    this.num=1;
                }
            },

            //创建一个方法实现记录操作
            //key:规格   网络  network
            //value:规格选项   移动4G
            selectSpecification:function (key,value) {
                console.log("key: " + key + "  value:  " + value);
                this.$set(this.specificationItems,key,value);

                //查找当前SKU
                this.searchSku();
            },
            //动态对规格参数html进行拼接（th:onclick监听事件行不通）
            specificationLable1:function () {
                var specificationListStr = "";
                if(specificationList != null) {
                    for(var i = 0;i < specificationList.length;i++) {
                        specificationListStr += "<dl><dt> <div class=\"fl title\"> <i>"+specificationList[i].attributeName+"</i></div></dt>"
                        if(specificationList[i].attributeValue != null) {
                            for(var j = 0;j < specificationList[i].attributeValue.length;j++) {
                                specificationListStr +="<dd><a :class=\"specificationItems['"+specificationList[i].attributeName+"']=='"+specificationList[i].attributeValue[j]+"'? 'selected':''\" @click=\"selectSpecification('"+specificationList[i].attributeName+"','"+specificationList[i].attributeValue[j]+"')\">"+specificationList[i].attributeValue[j]+"<span title=\"点击取消选择\">&nbsp;</span></a></dd>"
                            }
                        }
                        specificationListStr += "</dl>";
                    }
                }
                $("#specification").append(specificationListStr);
            },
            //鼠标移入方法
            enter:function () {
                this.showAddress = { display: 'inline-block'};
            },
            //鼠标移出方法
            leave:function(){
                this.showAddress = { display: 'inline-block' };
            },
            //i:索引的键   v:索引的值
            changeN:function (i) {
                //给n赋值
                this.n = i;
                this.$nextTick(function(){
                    console.log(this.$refs.i);
                    //获取第一个div坐标原点
                    let firstDiv = this.$refs.i[0];
                    let firstDivLeft = firstDiv.offsetLeft;
                    //获取点击的div坐标
                    let left = this.$refs.i[i].offsetLeft;
                    //左边线长度
                    let leftWidth = left - firstDivLeft;
                    console.log("leftWidth:   " + leftWidth);
                    //计算选中div宽度 5:左边距 + 字体个数 * 字体大小 + 字体右边距 + 图片宽度 + 边线宽度
                    let width = 5 + this.titleArr[i].length * 12 + 6 + 23 + 2;
                    let positionDiv = this.$refs.position.offsetLeft;
                    //右边线长的宽度 div总宽度 - 右边点横坐标 - 内边距 - 左内边距 + 右外边距；
                    let rightWidth = positionDiv + 470 - (left + width) - 60 + 10;
                    console.log("rightWidth:  " + rightWidth);
                    this.leftWidth = leftWidth;
                    this.rightWidth = rightWidth;
                })
            },
            //获取地址
            getAddress: function (address) {
                this.address += address;
            },
            setDefaultAddress: function () {
                for(let i = 0;i < this.citysArr.length;i++) {
                    this.address += this.citysArr[i][0];
                }
                console.log("address:   " + this.address);
            }
            //设置选择边线的默认值
            // defaultChange: function () {
            //     //this.$nextTick()将回调延迟到下次 DOM 更新循环之后执行
            //     this.$nextTick(function(){
            //         //给n赋值
            //         this.n = 0;
            //         //获取第一个div坐标原点
            //         let firstDiv = this.$refs.i[0];
            //         let firstDivLeft = firstDiv.offsetLeft;
            //         //获取点击的div坐标
            //         let left = this.$refs.i[0].offsetLeft;
            //         //左边线长度
            //         let leftWidth = left - firstDivLeft;
            //         console.log("leftWidth:   " + leftWidth);
            //         //计算选中div宽度 5:左边距 + 字体个数 * 字体大小 + 字体右边距 + 图片宽度 + 边线宽度
            //         let width = 5 + this.titleArr[0].length * 12 + 6 + 23 + 2;
            //         console.log("width:  " + width);
            //         let positionDiv = this.$refs.position.offsetLeft;
            //         console.log("positionDiv:   " + positionDiv);
            //         //右边线长的宽度 div总宽度 - 右边点横坐标 - 内边距 - 左内边距 + 右外边距；
            //         //元素设置为none后，到左边的距离变为0，加载一个到左边距离
            //         let rightWidth = 63 + 470 - (left + width) - 60 + 10;
            //         console.log("rightWidth:  " + rightWidth);
            //         this.leftWidth = leftWidth;
            //         this.rightWidth = rightWidth;
            //     });
            // }
        },
        created:function () {
            //加载默认的SKU
            this.loadSku();
            //动态添加规格标签
            this.specificationLable1();
            //设置选择边线的默认值
            this.changeN(0);
            //设置配送地址默认值
            this.setDefaultAddress();
        }
    });
}




