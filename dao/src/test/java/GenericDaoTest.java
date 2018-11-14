import com.ovchingus.dao.DaoFactory;
import com.ovchingus.dao.GenericDao;
import com.ovchingus.dao.hibernate.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericDaoTest {

    private GenericDao dao;

    @Before
    public void setUp() throws Exception {
        dao = DaoFactory.getDao("database", Store.class);
    }

    @Test
    public void testGetByPK() throws Exception {
        Store store = new Store("ashan", "karaganda");
        dao.persist(store);

        Assert.assertNotNull(store);
        // com.ovchingus.core.core.insert(new StoreJdbc("ashan", "hzGde"));
    }
}
