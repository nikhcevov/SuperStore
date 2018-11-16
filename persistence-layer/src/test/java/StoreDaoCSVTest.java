import com.ovchingus.persistence.CSV.StoreDaoCSV;
import com.ovchingus.persistence.CSV.model.StoreEntityCSV;
import org.junit.Before;
import org.junit.Test;

public class StoreDaoCSVTest {

    private StoreDaoCSV dao;

    @Before
    public void setUp() {
        dao = new StoreDaoCSV();
    }

    @Test
    public void PersistTest() {
        StoreEntityCSV a = new StoreEntityCSV(12, "d1d", "dad1");
        StoreEntityCSV b = new StoreEntityCSV(244, "ai", "d");
        dao.persist(a);
        dao.persist(b);
    }

    @Test
    public void testa() {
        //dao.persist(new StoreEntityCSV(244, "ai", "d"));
        dao.delete(new StoreEntityCSV(244, "ai", "d"));
    }

    @Test
    public void updateTest() {
        StoreEntityCSV se = new StoreEntityCSV(2, "magaz", "updated");
        dao.update(se);
    }

    @Test
    public void deleteAllTest() {
        dao.deleteAll();
    }
}
