import com.supergo.MapperApplication;
import com.supergo.mapper.ItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class ItemMapperTets {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testFindById() {
        Map<String, String> yijia = itemMapper.selectBySellerIdAndSkuId(1199181, "yijia");
        System.out.println(yijia.toString());
    }
}
