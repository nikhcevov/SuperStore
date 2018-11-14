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
        Store store = new Store("ads", "daegshnm");
        store.setId(4);
        dao.update(store);
    }

    @Test
    public void testInsertPK() {
        Store store = new Store("ashan", "karaganda");
        dao.persist(store);
    }
}
