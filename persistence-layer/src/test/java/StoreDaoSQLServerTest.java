import com.ovchingus.persistence.sqlserver.dao.StoreDaoSQLServer;
import com.ovchingus.persistence.sqlserver.entities.StoreEntitySQLServer;
import org.junit.Assert;
import org.junit.Test;

public class StoreDaoSQLServerTest {

    @Test
    public void findByNameTest() {
        StoreEntitySQLServer store;
        String name = "store1";

        StoreDaoSQLServer dao = new StoreDaoSQLServer();
        dao.openCurrentSession();
        store = dao.findByName(name);
        dao.closeCurrentSession();
        Assert.assertNotNull(store);
    }
}
