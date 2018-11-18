import com.ovchingus.service.Service;
import com.ovchingus.service.Settings;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class ServiceCSVTest {

    private Service dao;
    private int randInt;
    private String randStr;

    @Before
    public void setUp() {
        Settings.setSourceCSV();
        dao = new Service();
    }

    @Test
    public void serviceCreateProductTest() {
        for (int i = 0; i < 100; i++) {
            //randInt = ThreadLocalRandom.current().nextInt(100, 1000);
            randInt = i;
            randStr = RandomStringUtils.random(10, true, true);
            dao.createProduct(randInt, randStr);
        }
    }

    @Test
    public void createStoreTest() {
        for (int i = 0; i < 100; i++) {
            randStr = RandomStringUtils.random(10, true, true);
            randInt = i;
            dao.createStore(randInt, randStr, randStr);
        }
    }

    @Test
    public void serviceInsertProductTest() {
        System.out.println(dao.insertProductToStore("GeH3zFEvv9", "CSd7uzHTVm", 188, 54.00));
    }

    @Test
    public void updateProductTest() {
        System.out.println(dao.updateProduct("GeH3zsEvv9", "CSd7uzHTVm", 1632, 48.00));
    }
}
