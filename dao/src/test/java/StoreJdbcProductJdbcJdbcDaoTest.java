import com.ovchingus.dao.DaoFactoryOLD;
import com.ovchingus.dao.GenericDaoOLD;
import com.ovchingus.dao.KeyDb;
import com.ovchingus.dao.jdbc.DaoFactoryJdbc;
import com.ovchingus.dao.jdbc.model.StoreProductJdbc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class StoreJdbcProductJdbcJdbcDaoTest {

    private GenericDaoOLD dao;

    @Before
    public void setUp() throws Exception {

        DaoFactoryOLD<Connection> factory = new DaoFactoryJdbc();
        Connection connection = factory.getContext();
        dao = factory.getDao(connection, StoreProductJdbc.class);
    }

    @Test
    public void testGetByPK() throws Exception {
        StoreProductJdbc sp = (StoreProductJdbc) dao.getByPK(new KeyDb(1, 1));
        Assert.assertNotNull(sp);
        // com.ovchingus.core.core.insert(new StoreJdbc("ashan", "hzGde"));
    }


}
