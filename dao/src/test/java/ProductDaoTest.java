import com.ovchingus.dao.DaoFactory;
import com.ovchingus.dao.GenericDao;
import com.ovchingus.dao.hibernate.Product;
import com.ovchingus.dao.hibernate.ProductDao;
import org.junit.Assert;
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
        Product product;
        product = sd.findById(5);
        System.out.println(product);
    }

    @Test
    public void testGetByName() {
        Product product;
        product = sd.findByName("Pivas");
        System.out.println(product);
    }

    @Test
    public void testUpdate(){
        Product product = new Product(4, "Kobachok");
        sd.update(product);
    }

    @Test
    public void testDelete(){
        Product product = new Product();
        product.setId(4);
        sd.delete(product);
    }

    @Test
    public void testInsertPK(){
        Product product = new Product("Selyodka");
        sd.persist(product);
    }

    @Test
    public void testGetAll() {
        List<Product> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);
    }


}
