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
            showAddress: {display:'none',},        //鼠标是否移入配送地址栏
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
            haveGoods: '有货',                             //判断商品是否有货
            disable: false
        },
        methods:{
            //加入购物车实现
            addCart:function () {
                //如果为无货或声誉库存大于当前库存禁止跳转
                if(this.sku.num <= 0 || this.sku.num <= this.num) {
                    return false;
                }

                if(bearerToken != null) {  //用户登录情况下添加购物车操作
                    var url = "http://page.supergo.com/page/goods/addOrderCart";
                    axios({
                        method: 'get',
                        url: url,
                        headers: {
                            'Authorization': bearerToken
                        },
                        params: {
                            itemId: this.sku.id,
                            num: this.num,
                            sellerId: this.sku.sellerId
                        }
                    }).then(function (res) {
                        console.log(res);
                        window.location.href = 'http://page.supergo.com/unloginAddOrderCart.html';
                    }).catch(function (error) {
                        alert("生成模板失败");
                    });
                } else {  //用户未登录情况下添加购物车操作
                    var url = "http://page.supergo.com/page/goods/unloginAddOrderCart";
                    axios({
                        method: 'get',
                        url: url,
                        params: {
                            itemId: this.sku.id,
                            num: this.num,
                            sellerId: this.sku.sellerId
                        }
                    }).then(function (res) {
                        console.log(res);
                        window.location.href = 'http://page.supergo.com/unloginAddOrderCart.html';
                    }).catch(function (error) {
                        alert("生成模板失败");
                    });
                }
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
                        //判断是否有货
                        if(this.sku.num <= 0) {
                            this.haveGoods = '无货';
                            this.disable = true;
                        } else {
                            this.haveGoods = '有货';
                            this.disable = false;
                        }
                        return;
                    }
                }

                //该商品下架了
                this.sku={'title':'该商品断货了','price':0,'id':0,spec:{}};
            },


            //定义方法获取SKU数据
            loadSku:function () {
                var sku = '';
                //从集合中获取第1个记录
                for (var i = 0;i < itemList.length;i++) {
                    sku =JSON.parse(JSON.stringify(itemList[0]));
                    //如果是默认规格
                    if(sku.isDefault == 1) {
                        this.sku = sku;
                        //将spce赋值给选中的specificationItems
                        this.specificationItems=JSON.parse(this.sku.spec);
                        //判断是否有货
                        if(this.sku.num <= 0) {
                            this.haveGoods = '无货';
                            this.disable = true;
                        } else {
                            this.haveGoods = '有货';
                            this.disable = false;
                        }
                    }
                }
            },

            //创建方法，实现用户购买数量增加操作
            addNum:function (x) {
                this.num = parseInt(this.num);
                this.num += parseInt(x);
                if(this.num<1){
                    this.num=1;
                } else if(this.num >= 200) {
                    this.num = 200;
                }
                if(this.sku.num <= this.num) {
                    this.num = this.sku.num;
                    this.haveGoods = '无货';
                    this.disable = true;
                } else {
                    this.haveGoods = '有货';
                    this.disable = false;
                }
            },

            change:function () {
              if(this.num > 200) {
                  this.num = 200;
              } else if(this.num <= 1) {
                  this.num = 1;
              }
              if(this.sku.num <= this.num) {
                  this.num = this.sku.num;
                  this.haveGoods = '无货';
                  this.disable = true;
              } else {
                  this.haveGoods = '有货';
                  this.disable = false;
              }
            },

            //创建一个方法实现记录操作
            //key:规格   网络  network
            //value:规格选项   移动4G
            selectSpecification:function (key,value) {
                this.$set(this.specificationItems,key,value);
                var _this = this;
                var url = "http://page.supergo.com/page/goods/getItemByGoodsId";
                axios({
                    method: 'get',
                    url: url,
                    params: {
                        goodsId: this.sku.goodsId,
                    }
                }).then(function (res) {
                    console.log(res);
                    //更新库存列表
                    itemList = res.data.data;
                    console.log(itemList);
                    _this.searchSku();
                }).catch(function (error) {
                    alert("生成模板失败");
                });

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
                this.showAddress = { display: 'none' };
            },
            //i:索引的键
            changeN:function (i) {
                //给n赋值
                this.n = i;
            },
            //设置默认地址信息
            setDefaultAddress: function () {
                this.titleArr = [];
                this.citysArr = [];
                var provincesArr = [];
                var citiesArr = [];
                var areasArr = [];
                var flag = false;
                //遍历省级列表
                for(var i = 0;i < provincesList.length;i++) {
                    //获取省级信息
                    var province = provincesList[i].province;
                    //获取第一个省的信息
                    if(i == 0) {
                        this.titleArr.push(province);
                        //获取第一个省下的市级列表
                        var citiesList = provincesList[i].citiesList;
                        //遍历市级列表
                        for(var j = 0;j < citiesList.length;j++) {
                            //获取市级信息
                            var city = citiesList[j].city;
                            //如果市市“市辖区”或则“县”
                            if(city.startsWith("市辖区") || city.startsWith("县") || city.startsWith("市")) {
                                //获取县级列表
                                var areasList = citiesList[j].areasList;
                                //遍历县级列表
                                for(var k = 0;k < areasList.length;k++) {
                                    //获取县级信息
                                    var area = areasList[k].area;
                                    if(k == 0) {
                                        //如果已经添加了头部信息（可能添加多次）,就不在添加
                                        if(!flag) {
                                            this.titleArr.push(area);
                                            flag = true;
                                        }
                                    }
                                    //将县级信息存入数组
                                    areasArr.push(area);
                                }
                            } else {
                                //获取第一个市的信息
                                if(j == 0) {
                                    this.titleArr.push(city);
                                    //获取县级列表
                                    var areasList = citiesList[j].areasList;
                                    //遍历县级列表
                                    for(var k = 0;k < areasList.length;k++) {
                                        //获取县级信息
                                        var area = areasList[k].area;
                                        if(k == 0) {
                                            this.titleArr.push(area);
                                        }
                                        //将县级信息存入数组
                                        areasArr.push(area);
                                    }
                                }
                                //将市级信息存入数组
                                citiesArr.push(city);
                            }
                        }
                    }
                    //将省级信息存入数组
                    provincesArr.push(province);
                }
                this.citysArr.splice(0,1,provincesArr);
                if(citiesArr.length == 0) {
                    this.citysArr.splice(1,1,areasArr);
                } else {
                    this.citysArr.splice(1,1,citiesArr)
                    this.citysArr.splice(2,1,areasArr);
                }
                for(var m = 0;m < this.citysArr.length;m++) {
                    this.address += this.citysArr[m][0];
                }
            },
            getAddress: function(address) {
                var provincesArr = [];
                var citiesArr = [];
                var areasArr = [];
                //遍历省级列表
                for(var i = 0;i < provincesList.length;i++) {
                    var province = provincesList[i].province;
                    //如果点击的是省级信息
                    if(province == address) {
                        if(province.startsWith("香港") || province.startsWith("澳门") || province.startsWith("台湾")) {
                            this.titleArr.splice(0,3,address);
                            this.showAddress = { display: 'none' };
                            this.address = '';
                            for (var m = 0; m < this.titleArr.length; m++) {
                                this.address += this.titleArr[m];
                            }
                            return;
                        }
                        //获取市级列表
                        var citiesList = provincesList[i].citiesList;
                        //遍历市级列表
                        for(var j = 0;j < citiesList.length;j++) {
                            var city = citiesList[j].city;
                            if(city.startsWith("市辖区") || city.startsWith("县") || city.startsWith("市")) {
                                //获取县级列表
                                var areasList = citiesList[j].areasList;
                                for(var k = 0;k< areasList.length;k++) {
                                    areasArr.push(areasList[k].area);
                                }
                            } else {
                                //将city添加进入数组
                                citiesArr.push(city);
                            }
                        }
                        this.titleArr.splice(1,2,"请选择");
                        if(city.startsWith("市辖区") || city.startsWith("县") || city.startsWith("市")) {
                            this.citysArr.splice(1,2,areasArr);
                        } else {
                            this.citysArr.splice(1,2,citiesArr);
                        }
                        this.titleArr.splice(0,1,address);
                        this.changeN(1);
                    } else {   //如果点击的是市级信息
                        var province1 = this.titleArr[0];
                        //获取省级信息
                        if (province == province1) {
                            //获取市级列表
                            var citiesList = provincesList[i].citiesList;
                            //遍历市级列表
                            for (var j = 0; j < citiesList.length; j++) {
                                var city = citiesList[j].city;
                                //如果点击的是市级列表
                                if (city == address) {
                                    //获取县级列表
                                    var areasList = citiesList[j].areasList;
                                    for (var k = 0; k < areasList.length; k++) {
                                        areasArr.push(areasList[k].area);
                                    }
                                    this.titleArr.splice(1, 1, address);
                                    this.citysArr.splice(2, 1, areasArr);
                                    this.titleArr.splice(2, 1, "请选择");
                                    this.changeN(2);
                                } else {
                                    var areasList = citiesList[j].areasList;
                                    for (var k = 0; k < areasList.length; k++) {
                                        var area = areasList[k].area;
                                        if (area == address) {
                                            if (this.titleArr.length == 2) {
                                                this.titleArr.splice(1, 1, address);
                                            } else {
                                                this.titleArr.splice(2, 1, address);
                                            }
                                            this.address = '';
                                            for (var m = 0; m < this.titleArr.length; m++) {
                                                this.address += this.titleArr[m];
                                            }
                                            this.showAddress = {display: 'none'};
                                        }
                                    }
                                }
                            }
                        }
                    }
                    provincesArr.push(province);
                }
            }
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




