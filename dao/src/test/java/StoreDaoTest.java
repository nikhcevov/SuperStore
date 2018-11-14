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
        Store store = new Store();
        store = sd.findById(4);
        System.out.println(store);
    }

    @Test
    public void testGetByName() {
        Store store = new Store();
        store = sd.findByName("sege");
        System.out.println(store);
    }

    @Test
    public void testUpdate() {
        Store store = new Store();
        store.setName("mega");
        store.setAddress("leninskiy, 2");
        store.setId(4);
        sd.update(store);
        //dao.update(store);
    }

    @Test
    public void testGetAll() {
        List<Store> list = new ArrayList<>();
        list = sd.findAll();
        System.out.println(list);
    }

    @Test
    public void testInsertPK() {
        Store store = new Store();
        store.setName("eshkere");
        store.setAddress("mom");
        sd.persist(store);
        //dao.persist(store);
    }

    @Test
    public void testDelete() {
        Store store = new Store();
        store.setId(4);
        sd.delete(store);
    }
}
