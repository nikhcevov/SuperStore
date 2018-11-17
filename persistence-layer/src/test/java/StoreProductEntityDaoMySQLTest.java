import com.ovchingus.persistence.mysql.dao.StoreProductDaoMySQL;
import com.ovchingus.persistence.mysql.entities.StoreProductEntityMySQL;
import org.junit.Before;
import org.junit.Test;

public class StoreProductEntityDaoMySQLTest {

    private StoreProductDaoMySQL spd;

    @Before
    public void setUp() {
        spd = new StoreProductDaoMySQL();
    }

    @Test
    public void testPersist() {
        StoreProductEntityMySQL sp = new StoreProductEntityMySQL();
        StoreProductEntityMySQL.StoreProductPK spPK = new StoreProductEntityMySQL.StoreProductPK();
        spPK.setProductId(4);
        spPK.setStoreId(4);
        sp.setId(spPK);
        sp.setPrice(250.1);
        sp.setQty(13);
        spd.openCurrentSessionWithTransaction();
        spd.persist(sp);
        spd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByPK() {
        StoreProductEntityMySQL sp = new StoreProductEntityMySQL();
        StoreProductEntityMySQL.StoreProductPK spPK = new StoreProductEntityMySQL.StoreProductPK();
        spPK.setStoreId(4);
        spPK.setProductId(4);
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
        StoreProductEntityMySQL sp = new StoreProductEntityMySQL();
        StoreProductEntityMySQL.StoreProductPK spPK = new StoreProductEntityMySQL.StoreProductPK();
        sp.setQty(12);
        spPK.setStoreId(4);
        spPK.setProductId(4);

        spd.openCurrentSessionWithTransaction();
        spd.update(sp);
        spd.closeCurrentSessionWithTransaction();
    }




}
