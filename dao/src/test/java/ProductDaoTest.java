import com.ovchingus.dao.hibernate.Product;
import com.ovchingus.dao.hibernate.ProductDao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoTest {

    private ProductDao sd;

    @Before
    public void setUp() {
        sd = new ProductDao();
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();

        Product product;
        product = sd.findById(5);
        System.out.println(product);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        Product product;
        product = sd.findByName("Pivas");
        System.out.println(product);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate(){
        sd.openCurrentSessionWithTransaction();

        Product product = new Product(4, "KobachokOk");
        sd.update(product);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testDelete(){
        sd.openCurrentSessionWithTransaction();

        Product product = new Product();
        product.setId(4);
        sd.delete(product);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK(){
        sd.openCurrentSessionWithTransaction();

        Product product = new Product("Selyodka");
        sd.persist(product);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<Product> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
    }


}
