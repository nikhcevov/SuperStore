import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DaoFactoryTest {

    private GenericDao daoStore;

    @Before
    public void setUp() {
        daoStore = DaoFactory.getDao(StoreEntitySQLServer.class);
    }

    @Test
    public void persistTest() {
        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer.setName("yamagata versa");
        storeEntitySQLServer.setAddress("macadamia bladder, 11");
        DaoFactory.openTransaction(daoStore);
        daoStore.persist(storeEntitySQLServer);
        DaoFactory.closeTransaction(daoStore);
        Assert.assertNotNull(daoStore);
    }

    @Test
    public void multiInsertTest() {
        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer("patyarachka", "movzoley lenina");
        StoreEntitySQLServer storeEntitySQLServer1 = new StoreEntitySQLServer("chl", "en");
        StoreEntitySQLServer storeEntitySQLServer2 = new StoreEntitySQLServer("piz", "zza");
        StoreEntitySQLServer storeEntitySQLServer3 = new StoreEntitySQLServer("storeName", "address of storeName");

        DaoFactory.openTransaction(daoStore);
        daoStore.persist(storeEntitySQLServer);
        daoStore.persist(storeEntitySQLServer1);
        daoStore.persist(storeEntitySQLServer2);
        daoStore.persist(storeEntitySQLServer3);
        DaoFactory.closeTransaction(daoStore);
    }


}
