/*****
 * 定义一个Content控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
window.onload = function () {
    Vue.prototype.axios = 'axios';
    var app = new Vue({
        el:'#app',
        data:{
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
            orderCartList:orderCartList,
            userInfo: userInfo,
            isCheckedAll: false,      //是否全选
            checkModel: [],            //双向数据绑定
            total: 0.00,               //商品总价格
            disable: false             //去结算按钮是否禁用
        },
        methods:{
            //去结算
            goAccount: function () {
                //如果已经登录
                if(bearerToken != null) {
                    //如果未选中商品、禁止跳转
                    if(this.checkModel.length == 0) {
                        return false;
                    }
                    console.log("aaaaa");
                    var url = "http://page.supergo.com/page/orderCart/goAccount";
                    axios({
                        method: 'get',
                        url: url,
                        headers: {
                            'Authorization': bearerToken
                        }
                    }).then(function (res) {
                        console.log(res);
                        window.location.href = 'http://page.supergo.com/orderInfo.html';
                    }).catch(function (error) {
                        alert("生成模板失败");
                    });
                } else {
                    //获取当前页面url
                    var url = window.location.href;
                    console.log(url);
                    //跳转到登入页
                    window.location.href = "http://sso.supergo.com/user/loginPage?url=" + url
                }
            },
            //批量删除
            deletePatch:function() {
                var bearerToken1 = '';
                if(bearerToken != null) {
                    bearerToken1 = bearerToken;
                    var url = "http://page.supergo.com/page/orderCart/deletePatch";
                    axios({
                        method: 'post',
                        url: url,
                        params: {
                            ids: this.checkModel
                        },
                        headers: {
                            'Authorization': bearerToken1
                        }
                    }).then(function (res) {
                        if(res.data.code == 200) {
                            var url = "http://page.supergo.com/page/goods/showOrderCart";
                            axios({
                                method: 'get',
                                url: url,
                                headers: {
                                    'Authorization': bearerToken1
                                }
                            }).then(function (res) {
                                console.log(res);
                                window.location.href = 'http://page.supergo.com/'+res.data.msg+'.html';
                            }).catch(function (error) {
                                alert("生成模板失败");
                            });
                        }
                    }).catch(function (error) {
                        alert("生成模板失败");
                    });
                } else {
                    var url = "http://page.supergo.com/page/orderCart/deleteRedisPatch";
                    axios({
                        method: 'get',
                        url: url,
                        params: {
                            ids: this.checkModel
                        },
                        headers: {
                            'Authorization': bearerToken1
                        }
                    }).then(function (res) {
                        var url = "http://page.supergo.com/page/goods/unloginShowOrderCart";
                        axios({
                            method: 'get',
                            url: url,
                            headers: {
                                'Authorization': bearerToken1
                            }
                        }).then(function (res) {
                            window.location.href = "http://page.supergo.com/"+res.data.msg+".html";
                        }).catch(function (error) {
                            alert("生成模板失败");
                        });
                    }).catch(function (error) {
                        alert("生成模板失败");
                    });
                }
            },
            //单选
            checkedOne:function (itemId) {
                this.total = 0.00;
                let idIndex = this.checkModel.indexOf(itemId);
                if (idIndex >= 0) {
                    // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
                    this.checkModel.splice(idIndex, 1);
                } else {
                    // 选中该checkbox
                    this.checkModel.push(itemId);
                }
                console.log(this.checkModel);
                var _this = this;
                this.orderCartList.forEach(function (v) {
                    if(_this.checkModel.indexOf(v.item_id) >= 0) {
                        _this.total += v.price * v.num;
                        _this.total = Math.floor(_this.total * 100) / 100;
                    }
                });
                //如果选择框数据为0，禁止点击
                if(this.checkModel.length == 0) {
                    this.disable = true;
                } else {
                    this.disable = false;
                }
                console.log(this.total);
            },
            //点击全选按钮时，判断全选是选中还是未选中状态
            checkAll:function(){
                this.total = 0.00;
                this.isCheckedAll = !this.isCheckedAll
                if (this.isCheckedAll) {
                    // 全选时
                    this.checkModel = []
                    this.orderCartList.forEach(function (v) {
                        this.checkModel.push(v.item_id)
                    }, this)
                } else {
                    this.checkModel = []
                }
                console.log(this.checkModel);
                var _this = this;
                this.orderCartList.forEach(function (v) {
                    if(_this.checkModel.indexOf(v.item_id) >= 0) {
                        _this.total += v.price * v.num;
                        _this.total = Math.floor(_this.total * 100) / 100;
                    }
                });
                console.log(this.total);
                //如果选择框数据为0，禁止点击
                if(this.checkModel.length == 0) {
                    this.disable = true;
                } else {
                    this.disable = false;
                }
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
            //获取地址信息
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
            },
            //创建方法，实现用户购买数量增加操作
            addNum:function (x,id,index,itemId) {
                var num = parseInt(this.$refs.inputMiddle[index].value) + parseInt(x);
                var _this = this;
                if(num > 200) {
                    num = 200;
                } else if(num < 1) {
                    num = 1;
                } else {
                    var bearerToken1 = '';
                    if(bearerToken != null) {
                        bearerToken1 = bearerToken;
                        var url = "http://page.supergo.com/page/orderCart/getItemStock";
                        axios({
                            method: 'get',
                            url: url,
                            params: {
                                itemId:itemId
                            },
                            headers: {
                                'Authorization': bearerToken1
                            }
                        }).then(function (res) {
                            console.log(res);
                            var stock = res.data.data;
                            //如果当前数量大于剩余库
                            if(stock < num) {
                                alert("库存不足,无法继续添加");
                            } else {
                                var url = "http://page.supergo.com/page/orderCart/updateOrderCart";
                                //更新购物车
                                axios({
                                    method: 'get',
                                    url: url,
                                    params: {
                                        id: id,
                                        num: num
                                    },
                                    headers: {
                                        'Authorization': bearerToken1
                                    },
                                }).then(function (res) {
                                    if(res.data.code == 200) {
                                        //更新输入框数据
                                        _this.$refs.inputMiddle[index].value = num;
                                        //库存信息改变，从新生成购物车模板(刷新页面)
                                        var url = "http://page.supergo.com/page/goods/showOrderCart";
                                        axios({
                                            method: 'get',
                                            url: url,
                                            headers: {
                                                'Authorization': bearerToken1
                                            }
                                        }).then(function (res) {
                                            console.log(res);
                                            window.location.href = 'http://page.supergo.com/showOrderCart.html';
                                        }).catch(function (error) {
                                            alert("生成模板失败");
                                        });
                                    }
                                }).catch(function (error) {
                                    console.log(error);
                                    alert("更新购物车失败");
                                });
                            }
                            //更新价格
                        }).catch(function (error) {
                            console.log(error);
                            alert("获取库存信息失败");
                        });
                    } else {
                        var url = "http://page.supergo.com/page/orderCart/getItemStock";
                        axios({
                            method: 'get',
                            url: url,
                            params: {
                                itemId:itemId
                            },
                            headers: {
                                'Authorization': bearerToken1
                            }
                        }).then(function (res) {
                            var stock = res.data.data;
                            //如果当前数量大于剩余库
                            if(stock < num) {
                                alert("库存不足,无法继续添加");
                            } else {
                                var url = "http://page.supergo.com/page/orderCart/updateRedisOrderCart";
                                //更新购物车
                                axios({
                                    method: 'get',
                                    url: url,
                                    params: {
                                        itemId: itemId,
                                        num: num
                                    },
                                    headers: {
                                        'Authorization': bearerToken1
                                    },
                                }).then(function (res) {
                                    //更新输入框数据
                                    _this.$refs.inputMiddle[index].value = num;
                                    //库存信息改变，从新生成购物车模板(刷新页面)
                                    var url = "http://page.supergo.com/page/goods/unloginShowOrderCart";
                                    axios({
                                        method: 'get',
                                        url: url,
                                        headers: {
                                            'Authorization': bearerToken1
                                        }
                                    }).then(function (res) {
                                        console.log(res);
                                        window.location.href = 'http://page.supergo.com/showOrderCart.html';
                                    }).catch(function (error) {
                                        alert("生成模板失败");
                                    });
                                }).catch(function (error) {
                                    console.log(error);
                                    alert("更新购物车失败");
                                });
                            }
                        }).catch(function (error) {
                            console.log(error);
                            alert("获取库存信息失败");
                        });
                    }
                }
            },
            //监听商品数量修改时数据变化
            change:function (id,index,itemId) {
                var num = parseInt(this.$refs.inputMiddle[index].value);
                var _this = this;
                if(num > 200) {
                    num = 200;
                    _this.$refs.inputMiddle[index].value = num;
                } else if(num < 1 || isNaN(num)) {
                    num = 1;
                    _this.$refs.inputMiddle[index].value = num;
                } else {
                    var bearerToken1 = '';
                    if(bearerToken != null) {
                        bearerToken1 = bearerToken;
                        var url = "http://page.supergo.com/page/orderCart/getItemStock";
                        axios({
                            method: 'get',
                            url: url,
                            params: {
                                itemId:itemId
                            },
                            headers: {
                                'Authorization': bearerToken1
                            },
                        }).then(function (res) {
                            console.log(res);
                            var stock = res.data.data;
                            //如果当前数量大于剩余库
                            if(stock < num) {
                                alert("库存不足,无法修改");
                            } else {
                                var url = "http://page.supergo.com/page/orderCart/updateOrderCart";
                                //更新购物车
                                axios({
                                    method: 'get',
                                    url: url,
                                    params: {
                                        id: id,
                                        num: num
                                    },
                                    headers: {
                                        'Authorization': bearerToken1
                                    },
                                }).then(function (res) {
                                    if(res.data.code == 200) {
                                        //更新输入框数据
                                        _this.$refs.inputMiddle[index].value = num;
                                        //库存信息改变，从新生成购物车模板(刷新页面)
                                        var url = "http://page.supergo.com/page/goods/showOrderCart";
                                        axios({
                                            method: 'get',
                                            url: url,
                                            headers: {
                                                'Authorization': bearerToken1
                                            }
                                        }).then(function (res) {
                                            console.log(res);
                                            window.location.href = 'http://page.supergo.com/showOrderCart.html';
                                        }).catch(function (error) {
                                            alert("生成模板失败");
                                        });
                                    }
                                }).catch(function (error) {
                                    console.log(error);
                                    alert("更新购物车失败");
                                });
                            }
                        }).catch(function (error) {
                            console.log(error);
                            alert("获取库存信息失败");
                        });
                    } else {
                        var url = "http://page.supergo.com/page/orderCart/getItemStock";
                        axios({
                            method: 'get',
                            url: url,
                            params: {
                                itemId:itemId
                            },
                            headers: {
                                'Authorization': bearerToken1
                            },
                        }).then(function (res) {
                            console.log(res);
                            var stock = res.data.data;
                            //如果当前数量大于剩余库
                            if(stock < num) {
                                alert("库存不足,无法修改");
                            } else {
                                var url = "http://page.supergo.com/page/orderCart/updateRedisOrderCart";
                                //更新购物车
                                axios({
                                    method: 'get',
                                    url: url,
                                    params: {
                                        itemId: itemId,
                                        num: num
                                    },
                                    headers: {
                                        'Authorization': bearerToken1
                                    },
                                }).then(function (res) {
                                    //更新输入框数据
                                    _this.$refs.inputMiddle[index].value = num;
                                    //库存信息改变，从新生成购物车模板(刷新页面)
                                    var url = "http://page.supergo.com/page/goods/unloginShowOrderCart";
                                    axios({
                                        method: 'get',
                                        url: url,
                                        headers: {
                                            'Authorization': bearerToken1
                                        }
                                    }).then(function (res) {
                                        console.log(res);
                                        window.location.href = 'http://page.supergo.com/showOrderCart.html';
                                    }).catch(function (error) {
                                        alert("生成模板失败");
                                    });
                                }).catch(function (error) {
                                    console.log(error);
                                    alert("更新购物车失败");
                                });
                            }
                        }).catch(function (error) {
                            console.log(error);
                            alert("获取库存信息失败");
                        });
                    }
                }
            },
            //删除商品
            deleteGoods: function (id,itemId) {
                alert(itemId);
                var bearerToken1 = '';
                if(bearerToken != null) {
                    bearerToken1 = bearerToken;
                    var url = "http://page.supergo.com/page/orderCart/delete";
                    axios({
                        method: 'get',
                        url: url,
                        params: {
                            id:id
                        },
                        headers: {
                            'Authorization': bearerToken1
                        },
                    }).then(function (res) {
                        if(res.data.code == 200) {
                            var url = "http://page.supergo.com/page/goods/showOrderCart";
                            axios({
                                method: 'get',
                                url: url,
                                headers: {
                                    'Authorization': bearerToken1
                                }
                            }).then(function (res) {
                                console.log(res);
                                window.location.href = 'http://page.supergo.com/'+res.data.msg+'.html';
                            }).catch(function (error) {
                                alert("生成模板失败");
                            });
                        }
                    }).catch(function (error) {
                        console.log(error);
                        alert("删除商品信息失败");
                    });
                } else {
                    var url = "http://page.supergo.com/page/orderCart/deleteRedis";
                    axios({
                        method: 'get',
                        url: url,
                        params: {
                            itemId:itemId
                        },
                        headers: {
                            'Authorization': bearerToken1
                        },
                    }).then(function (res) {
                        if(res.data.code == 200) {
                            var url = "http://page.supergo.com/page/goods/unloginShowOrderCart";
                            axios({
                                method: 'get',
                                url: url,
                                headers: {
                                    'Authorization': bearerToken1
                                }
                            }).then(function (res) {
                                console.log(res);
                                window.location.href = 'http://page.supergo.com/'+res.data.msg+'.html';
                            }).catch(function (error) {
                                alert("生成模板失败");
                            });
                        }
                    }).catch(function (error) {
                        console.log(error);
                        alert("删除商品信息失败");
                    });
                }
            } 
        },
        created:function () {
            //设置选择边线的默认值
            this.changeN(0);
            //设置配送地址默认值
            this.setDefaultAddress();
            //默认全选
            this.checkAll();
        }
    });
}




