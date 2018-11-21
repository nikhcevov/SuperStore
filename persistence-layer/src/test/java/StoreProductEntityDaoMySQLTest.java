import com.ovchingus.persistence.sqlserver.dao.StoreProductDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreProductEntitySQLServer;
import org.junit.Before;
import org.junit.Test;

public class StoreProductEntityDaoMySQLTest {

    private StoreProductDaoSQLServer spd;

    @Before
    public void setUp() {
        spd = new StoreProductDaoSQLServer();
    }

    @Test
    public void testPersist() {
        StoreProductEntitySQLServer sp = new StoreProductEntitySQLServer();
        StoreProductEntitySQLServer.StoreProductPK spPK = new StoreProductEntitySQLServer.StoreProductPK();
        boolean test;
        spPK.setProductId(6);
        spPK.setStoreId(26);
        sp.setId(spPK);
        sp.setPrice(250.1);
        sp.setQty(13);
        spd.openCurrentSessionWithTransaction();
        test = spd.persist(sp);
        spd.closeCurrentSessionWithTransaction();
        System.out.println(test);
    }

    @Test
    public void testGetByPK() {
        StoreProductEntitySQLServer sp = new StoreProductEntitySQLServer();
        StoreProductEntitySQLServer.StoreProductPK spPK = new StoreProductEntitySQLServer.StoreProductPK();
        spPK.setStoreId(25);
        spPK.setProductId(5);
        sp.setId(spPK);
        spd.openCurrentSessionWithTransaction();
        System.out.println(spd.findById(spPK));
        spd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetAll() {
        spd.openCurrentSessionWithTransaction();
        System.out.println(spd.findAll());
        spd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate() {
        StoreProductEntitySQLServer sp = new StoreProductEntitySQLServer();
        StoreProductEntitySQLServer.StoreProductPK spPK = new StoreProductEntitySQLServer.StoreProductPK();
        sp.setQty(12);
        spPK.setStoreId(4);
        spPK.setProductId(4);

        spd.openCurrentSessionWithTransaction();
        spd.update(sp);
        spd.closeCurrentSessionWithTransaction();
    }




}
