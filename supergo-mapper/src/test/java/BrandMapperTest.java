import com.supergo.MapperApplication;
import com.supergo.mapper.BrandMapper;
import com.supergo.pojo.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void testFindById() {
        Brand brand = brandMapper.selectByPrimaryKey(1);
        System.out.println(brand);
    }
}
