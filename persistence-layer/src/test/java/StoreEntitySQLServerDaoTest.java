import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.sqlserver.dao.StoreDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreEntitySQLServerDaoTest {

    private StoreDaoSQLServer sd;
    private GenericDao sdg;

    @Before
    public void setUp() {
        sd = new StoreDaoSQLServer();
        GenericDao sdg = DaoFactory.getDao(StoreEntitySQLServer.class);
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();


        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer = sd.findById(4);
        System.out.println(storeEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer = sd.findByName("sege");
        System.out.println(storeEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate() {
        sd.openCurrentSessionWithTransaction();

        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer.setName("mega");
        storeEntitySQLServer.setAddress("leninskiy, 2");
        storeEntitySQLServer.setId(4);
        sd.update(storeEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
        //persistence.update(storeEntitySQLServer);
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<StoreEntitySQLServer> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK() {
        sd.openCurrentSessionWithTransaction();

        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer.setName("eshkere");
        storeEntitySQLServer.setAddress("mom");
        sd.persist(storeEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
        //persistence.persist(storeEntitySQLServer);
    }

    @Test
    public void testDelete() {
        sd.openCurrentSessionWithTransaction();

        StoreEntitySQLServer storeEntitySQLServer = new StoreEntitySQLServer();
        storeEntitySQLServer.setId(4);
        sd.delete(storeEntitySQLServer);

        sd.closeCurrentSessionWithTransaction();
    }
}
