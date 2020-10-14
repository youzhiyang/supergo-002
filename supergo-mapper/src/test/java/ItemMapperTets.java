import com.supergo.MapperApplication;
import com.supergo.mapper.ItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class ItemMapperTets {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void testFindById() {
        Map<Object, Object> objectObjectMap = itemMapper.selectBySellerIdAndSkuId(1282248, 1);

    }
}
