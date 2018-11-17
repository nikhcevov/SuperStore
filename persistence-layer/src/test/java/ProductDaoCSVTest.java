import com.ovchingus.persistence.CSV.dao.ProductDaoCSV;
import com.ovchingus.persistence.CSV.entities.ProductEntityCSV;
import com.ovchingus.persistence.CSV.entities.ProductInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoCSVTest {

    private ProductDaoCSV dao;

    @Before
    public void setUp() {
        dao = new ProductDaoCSV();
    }

    @Test
    public void persistTest() {
        ProductEntityCSV entity = new ProductEntityCSV();
        entity.setId(3);
        entity.setName("chocolate Alenka");
        List<ProductInfo> list = new ArrayList<>();
        list.add(new ProductInfo(1, 10, 85.00));
        list.add(new ProductInfo(2, 15, 90.00));
        entity.setProducts(list);
        dao.persist(entity);
        dao.persist(entity);
        dao.persist(entity);
    }

    @Test
    public void deleteTest() {
        ProductEntityCSV entity = new ProductEntityCSV();
        entity.setId(3);
        entity.setName("chocolate Alenka");
        List<ProductInfo> list = new ArrayList<>();
        list.add(new ProductInfo(1, 10, 85.00));
        list.add(new ProductInfo(2, 15, 90.00));
        entity.setProducts(list);
        dao.delete(entity);
    }

    @Test
    public void findAllTest() {
        List<ProductEntityCSV> list = dao.findAll();
        System.out.println(list);
    }

    @Test
    public void findByNameTest() {
        ProductEntityCSV pe;
        pe = dao.findByName("tvSamsumg");
        System.out.println(pe.getId());
    }

    @Test
    public void findByIdTest() {
        ProductEntityCSV pe;
        pe = dao.findById(1);
        System.out.println(pe.getProducts());
    }


}