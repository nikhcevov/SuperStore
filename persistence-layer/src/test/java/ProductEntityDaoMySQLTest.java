import com.ovchingus.persistence.MySQL.dao.ProductDaoMySQL;
import com.ovchingus.persistence.MySQL.entities.ProductEntityMySQL;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductEntityDaoMySQLTest {

    private ProductDaoMySQL sd;

    @Before
    public void setUp() {
        sd = new ProductDaoMySQL();
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();

        ProductEntityMySQL productEntityMySQL;
        productEntityMySQL = sd.findById(5);
        System.out.println(productEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        ProductEntityMySQL productEntityMySQL;
        productEntityMySQL = sd.findByName("Pivas");
        System.out.println(productEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate(){
        sd.openCurrentSessionWithTransaction();

        ProductEntityMySQL productEntityMySQL = new ProductEntityMySQL(4, "KobachokOk");
        sd.update(productEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testDelete(){
        sd.openCurrentSessionWithTransaction();

        ProductEntityMySQL productEntityMySQL = new ProductEntityMySQL();
        productEntityMySQL.setId(4);
        sd.delete(productEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK(){
        sd.openCurrentSessionWithTransaction();

        ProductEntityMySQL productEntityMySQL = new ProductEntityMySQL("Selyodka");
        sd.persist(productEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<ProductEntityMySQL> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
    }


}
