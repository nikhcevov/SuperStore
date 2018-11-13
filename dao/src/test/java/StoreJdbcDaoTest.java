import com.ovchingus.dao.core.DaoFactory;
import com.ovchingus.dao.core.GenericDao;
import com.ovchingus.dao.core.jdbc.DaoFactoryJdbc;
import com.ovchingus.dao.core.jdbc.PersistException;
import com.ovchingus.dao.model.Store;
import com.ovchingus.dao.util.KeyDb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class StoreJdbcDaoTest {

    private GenericDao dao;

    @Before
    public void setUp() throws Exception {

        DaoFactory<Connection> factory = new DaoFactoryJdbc();
        Connection connection = factory.getContext();
        dao = factory.getDao(connection, Store.class);
    }

    @Test
    public void testGetByPK() throws Exception {
        Store store = (Store) dao.getByPK(new KeyDb(1));
        Assert.assertNotNull(store);
       // com.ovchingus.core.core.insert(new Store("ashan", "hzGde"));
    }

    @Test
    public void testInsert() throws Exception {
        Store store= new Store("ashan", "astafieve 7");
        dao.insert(store);
    }

    @Test
    public void testDelete() throws Exception {
        Store store = new Store(18,"ashan","astafieve 7");
        dao.delete(store);
    }

}
