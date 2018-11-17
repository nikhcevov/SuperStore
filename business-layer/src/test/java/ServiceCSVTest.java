import com.ovchingus.persistence.CSV.entities.StoreEntityCSV;
import com.ovchingus.service.func.Settings;
import com.ovchingus.service.func.model.Service;
import org.junit.Before;
import org.junit.Test;

public class ServiceCSVTest {

    private Service dao;

    @Before
    public void setUp() {
        Settings.setSourceCSV();
        dao = new Service();
    }

    @Test
    public void serviceCreateProductTest() {
        StoreEntityCSV store = new StoreEntityCSV(123, "dawd", "d1d");
        dao.createProduct(31, "mogoogom");
    }

    @Test
    public void updateProductTest() {
        //dao.updateProduct();
    }
}
