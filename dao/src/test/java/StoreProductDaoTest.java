import com.ovchingus.dao.hibernate.StoreProductDao;
import com.ovchingus.dao.hibernate.mappings.StoreProduct;
import org.junit.Before;
import org.junit.Test;

public class StoreProductDaoTest {

    private StoreProductDao spd;

    @Before
    public void setUp() {
        spd = new StoreProductDao();
    }

    @Test
    public void testPersist() {
        StoreProduct sp = new StoreProduct();
        StoreProduct.StoreProductPK spPK = new StoreProduct.StoreProductPK();
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
        StoreProduct sp = new StoreProduct();
        StoreProduct.StoreProductPK spPK = new StoreProduct.StoreProductPK();
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
        StoreProduct sp = new StoreProduct();
        StoreProduct.StoreProductPK spPK = new StoreProduct.StoreProductPK();
        sp.setQty(12);
        spPK.setStoreId(4);
        spPK.setProductId(4);

        spd.openCurrentSessionWithTransaction();
        spd.update(sp);
        spd.closeCurrentSessionWithTransaction();
    }




}
