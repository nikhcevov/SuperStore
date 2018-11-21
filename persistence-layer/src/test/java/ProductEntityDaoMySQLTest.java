import com.ovchingus.persistence.sqlserver.dao.ProductDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.ProductEntitySQLServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductEntityDaoMySQLTest {

    private ProductDaoSQLServer sd;

    @Before
    public void setUp() {
        sd = new ProductDaoSQLServer();
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();

        ProductEntitySQLServer productEntitySQLServer;
        productEntitySQLServer = sd.findById(5);
        System.out.println(productEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        ProductEntitySQLServer productEntitySQLServer;
        productEntitySQLServer = sd.findByName("Pivas");
        System.out.println(productEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate(){
        sd.openCurrentSessionWithTransaction();

        ProductEntitySQLServer productEntitySQLServer = new ProductEntitySQLServer(4, "KobachokOk");
        sd.update(productEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testDelete(){
        sd.openCurrentSessionWithTransaction();

        ProductEntitySQLServer productEntitySQLServer = new ProductEntitySQLServer();
        productEntitySQLServer.setId(4);
        sd.delete(productEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK(){
        sd.openCurrentSessionWithTransaction();

        ProductEntitySQLServer productEntitySQLServer = new ProductEntitySQLServer("Селедка");
        sd.persist(productEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<ProductEntitySQLServer> list;
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
        Assert.assertNotNull(list);
    }


}
