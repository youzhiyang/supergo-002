package com.supergo.manager.feign;

import com.supergo.http.HttpResult;
import com.supergo.pojo.Cities;
import com.supergo.pojo.Ordercart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@FeignClient(serviceId = "supergo-manager")
//@RequestMapping("/cities")   //不要在此注解上加载处理器映射器，会被处理器映射器扫描，进行误判
public interface ApiOrderCartFeign {

    /**
     * 未登录情况下添加购物车
     * @param itemId
     * @param clientId
     * @param num
     * @param sellerId
     * @return
     */
    @RequestMapping("/orderCart/unloginAddOrderCart/{item_id}/{client_id}/{seller_id}/{num}")
    public Map<Object, Object> unloginAddOrderCart(@PathVariable("item_id")int itemId, @PathVariable("client_id")String clientId,
                                                   @PathVariable("num")int num, @PathVariable("seller_id")int sellerId);

    /**
     * 登录情况下添加购物车
     * @param itemId
     * @param num
     * @param sellerId
     * @return
     */
    @RequestMapping("/orderCart/addOrderCart/{item_id}/{num}/{seller_id}")
    public Map<Object, Object> addOrderCart(@PathVariable("item_id")int itemId,
                             @PathVariable("num") int num,@PathVariable("seller_id")int sellerId);

    /**
     * 用户登录情况下，查询购物车
     * @return
     */
    @RequestMapping("/orderCart/getOrderCart")
    public List<Map<Object,Object>> getOrderCart();

    /**
     * 用户未登录情况下，查询购物车
     * @return
     */
    @RequestMapping("/orderCart/unloginOrderCart/{clientId}")
    public List<Map<Object,Object>> getUnloginOrderCart(@PathVariable("clientId")String clientId);

    /**
     * 更新购物车数据
     */
    @RequestMapping("/orderCart/updateOrderCart/{id}/{num}")
    public void updateOrderCart(@PathVariable("id")int id,@PathVariable("num")int num);

    /**
     * 根据主键查询购物车
     */
    @RequestMapping("/orderCart/selectByPrimaryKey/{id}")
    public Ordercart selectByPrimaryKey(@PathVariable("id") long id);

    /**
     * 更新redis购物车数据
     */
    @RequestMapping("/orderCart/updateRedisOrderCart/{itemId}/{num}/{clientId}")
    public void updateRedisOrderCart(@PathVariable("itemId")int itemId,@PathVariable("num")int num,@PathVariable("clientId")String clientId);

    /**
     * 根据id删除
     */
    @RequestMapping("/orderCart/delete/{id}")
    public void delete(@PathVariable("id")long id);

    /**
     * 删除redis购物车数据
     * @param itemId
     */
    @RequestMapping("/orderCart/deleteRedis/{itemId}/{clientId}")
    public void deleteRedis(@PathVariable("itemId")int itemId,@PathVariable("clientId")String clientId);

    /**
     * 批量删除
     */
    @RequestMapping("/orderCart/deletePatch/{ids}")
    public void deletePatch(@PathVariable("ids")String[] ids);

    /**
     * 批量删除redis购物车数据
     */
    @RequestMapping("/orderCart/deleteRedisPatch/{ids}/{clientId}")
    public void deleteRedisPatch(@PathVariable("ids")String[] ids,@PathVariable("clientId")String clientId);
}
