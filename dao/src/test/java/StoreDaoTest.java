import com.ovchingus.dao.DaoFactory;
import com.ovchingus.dao.GenericDao;
import com.ovchingus.dao.hibernate.Store;
import com.ovchingus.dao.hibernate.StoreDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreDaoTest {

    private GenericDao dao;
    private GenericDao daw;

    @Before
    public void setUp() {
        dao = new DaoFactory().getDao("database", Store.class);
        StoreDao sd = new StoreDao();

    }

    @Test
    public void testGetByPK() {

        Store store = new Store();
        store = (Store) dao.findById(4);
        System.out.println(store);
        Assert.assertNotNull(store);
    }

    @Test
    public void testUpdate() {

        StoreDao sd = new StoreDao();
        Store store = new Store();
        store.setName("mega");
        store.setAddress("leninskiy, 2");
        store.setId(4);
        sd.update(store);
        //dao.update(store);
    }

    @Test
    public void testInsertPK() {
        StoreDao sd = new StoreDao();
        Store store = new Store();
        store.setName("ashan");
        store.setAddress("karaganda");
        sd.openCurrentSessionWithTransaction();
        sd.persist(store);
        sd.closeCurrentSessionWithTransaction();
        //dao.persist(store);
    }
}
