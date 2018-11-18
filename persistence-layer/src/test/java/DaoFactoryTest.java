import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.mysqls.entities.StoreEntityMySQL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DaoFactoryTest {

    private GenericDao daoStore;

    @Before
    public void setUp() {
        daoStore = DaoFactory.getDao(StoreEntityMySQL.class);
    }

    @Test
    public void persistTest() {
        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL.setName("yamagata versa");
        storeEntityMySQL.setAddress("macadamia bladder, 11");
        DaoFactory.openTransaction(daoStore);
        daoStore.persist(storeEntityMySQL);
        DaoFactory.closeTransaction(daoStore);
        Assert.assertNotNull(daoStore);
    }

    @Test
    public void multiInsertTest() {
        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL("patyarachka", "movzoley lenina");
        StoreEntityMySQL storeEntityMySQL1 = new StoreEntityMySQL("chl", "en");
        StoreEntityMySQL storeEntityMySQL2 = new StoreEntityMySQL("piz", "zza");
        StoreEntityMySQL storeEntityMySQL3 = new StoreEntityMySQL("storeName", "address of storeName");

        DaoFactory.openTransaction(daoStore);
        daoStore.persist(storeEntityMySQL);
        daoStore.persist(storeEntityMySQL1);
        daoStore.persist(storeEntityMySQL2);
        daoStore.persist(storeEntityMySQL3);
        DaoFactory.closeTransaction(daoStore);
    }


}
