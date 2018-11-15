import com.ovchingus.dao.DaoFactory;
import com.ovchingus.dao.GenericDao;
import com.ovchingus.dao.hibernate.mappings.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DaoFactoryTest {

    private GenericDao daoStore;

    @Before
    public void setUp() {
        daoStore = DaoFactory.getDao(Store.class);
    }

    @Test
    public void persistTest() {
        Store store = new Store();
        store.setName("yamagata versa");
        store.setAddress("macadamia bladder, 11");
        DaoFactory.openTransaction(daoStore);
        daoStore.persist(store);
        DaoFactory.closeTransaction(daoStore);
        Assert.assertNotNull(daoStore);
    }

    @Test
    public void multiInsertTest() {
        Store store = new Store("patyarachka", "movzoley lenina");
        Store store1 = new Store("chl", "en");
        Store store2 = new Store("piz", "zza");
        Store store3 = new Store("storeName", "address of storeName");

        DaoFactory.openTransaction(daoStore);
        daoStore.persist(store);
        daoStore.persist(store1);
        daoStore.persist(store2);
        daoStore.persist(store3);
        DaoFactory.closeTransaction(daoStore);
    }


}
