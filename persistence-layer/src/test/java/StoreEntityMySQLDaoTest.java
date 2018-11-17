import com.ovchingus.persistence.DaoFactory;
import com.ovchingus.persistence.GenericDao;
import com.ovchingus.persistence.MySQL.dao.StoreDaoMySQL;
import com.ovchingus.persistence.MySQL.entities.StoreEntityMySQL;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreEntityMySQLDaoTest {

    private StoreDaoMySQL sd;
    private GenericDao sdg;

    @Before
    public void setUp() {
        sd = new StoreDaoMySQL();
        GenericDao sdg = DaoFactory.getDao(StoreEntityMySQL.class);
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();


        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL = sd.findById(4);
        System.out.println(storeEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL = sd.findByName("sege");
        System.out.println(storeEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate() {
        sd.openCurrentSessionWithTransaction();

        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL.setName("mega");
        storeEntityMySQL.setAddress("leninskiy, 2");
        storeEntityMySQL.setId(4);
        sd.update(storeEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
        //persistence.update(storeEntityMySQL);
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<StoreEntityMySQL> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK() {
        sd.openCurrentSessionWithTransaction();

        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL.setName("eshkere");
        storeEntityMySQL.setAddress("mom");
        sd.persist(storeEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
        //persistence.persist(storeEntityMySQL);
    }

    @Test
    public void testDelete() {
        sd.openCurrentSessionWithTransaction();

        StoreEntityMySQL storeEntityMySQL = new StoreEntityMySQL();
        storeEntityMySQL.setId(4);
        sd.delete(storeEntityMySQL);

        sd.closeCurrentSessionWithTransaction();
    }
}
