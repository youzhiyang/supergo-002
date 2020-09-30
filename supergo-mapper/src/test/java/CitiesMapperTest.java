import com.supergo.MapperApplication;
import com.supergo.mapper.CitiesMapper;
import com.supergo.pojo.Cities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class CitiesMapperTest {

    @Autowired
    private CitiesMapper citiesMapper;

    @Test
    public void testFindById() {
        Cities cities = citiesMapper.selectByPrimaryKey(1);
        System.out.println(cities.toString());
    }
}
