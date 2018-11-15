import com.ovchingus.dao.DaoFactoryOLD;
import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.jdbc.DaoFactoryJdbc;
import com.ovchingus.dao.jdbc.KeyDb;
import com.ovchingus.dao.jdbc.model.StoreJdbc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class StoreJdbcJdbcDaoTest {

    private GenericDaoOLD dao;

    @Before
    public void setUp() throws Exception {

        DaoFactoryOLD<Connection> factory = new DaoFactoryJdbc();
        Connection connection = factory.getContext();
        dao = factory.getDao(connection, StoreJdbc.class);
    }

    @Test
    public void testGetByPK() throws Exception {
        StoreJdbc store = (StoreJdbc) dao.getByPK(new KeyDb(1));
        Assert.assertNotNull(store);
        // com.ovchingus.core.core.insert(new StoreJdbc("ashan", "hzGde"));
    }

    @Test
    public void testInsert() throws Exception {
        StoreJdbc store = new StoreJdbc("ashan", "astafieve 7");
        dao.insert(store);
    }

    @Test
    public void testDelete() throws Exception {
        StoreJdbc store = new StoreJdbc(18, "ashan", "astafieve 7");
        dao.delete(store);
    }

}
