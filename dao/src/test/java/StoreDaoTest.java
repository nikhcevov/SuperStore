import com.ovchingus.dao.hibernate.Store;
import com.ovchingus.dao.hibernate.StoreDao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StoreDaoTest {

    private StoreDao sd;

    @Before
    public void setUp() {
        sd = new StoreDao();
    }

    @Test
    public void testGetByPK() {
        sd.openCurrentSessionWithTransaction();

        Store store = new Store();
        store = sd.findById(4);
        System.out.println(store);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testGetByName() {
        sd.openCurrentSessionWithTransaction();

        Store store = new Store();
        store = sd.findByName("sege");
        System.out.println(store);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testUpdate() {
        sd.openCurrentSessionWithTransaction();

        Store store = new Store();
        store.setName("mega");
        store.setAddress("leninskiy, 2");
        store.setId(4);
        sd.update(store);

        sd.closeCurrentSessionWithTransaction();
        //dao.update(store);
    }

    @Test
    public void testGetAll() {
        sd.openCurrentSessionWithTransaction();

        List<Store> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);

        sd.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testInsertPK() {
        sd.openCurrentSessionWithTransaction();

        Store store = new Store();
        store.setName("eshkere");
        store.setAddress("mom");
        sd.persist(store);

        sd.closeCurrentSessionWithTransaction();
        //dao.persist(store);
    }

    @Test
    public void testDelete() {
        sd.openCurrentSessionWithTransaction();

        Store store = new Store();
        store.setId(4);
        sd.delete(store);

        sd.closeCurrentSessionWithTransaction();
    }
}
