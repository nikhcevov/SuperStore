import com.ovchingus.dao.core.DaoFactory;
import com.ovchingus.dao.core.GenericDao;
import com.ovchingus.dao.core.jdbc.DaoFactoryJdbc;
import com.ovchingus.dao.core.jdbc.PersistException;
import com.ovchingus.dao.model.StoreProduct;
import com.ovchingus.dao.util.KeyDb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class StoreProductJdbcDaoTest {

    private GenericDao dao;

    @Before
    public void setUp() throws PersistException {

        DaoFactory<Connection> factory = new DaoFactoryJdbc();
        Connection connection = factory.getContext();
        dao = factory.getDao(connection, StoreProduct.class);
    }

    @Test
    public void testGetByPK() throws Exception {
        StoreProduct sp = (StoreProduct) dao.getByPK(new KeyDb(1,1));
        Assert.assertNotNull(sp);
        // com.ovchingus.core.core.insert(new Store("ashan", "hzGde"));
    }


}
