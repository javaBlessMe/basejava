import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
       super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverLimit() {
        for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException se) {
                Assert.fail("При сохранении резюме под номером " + i + " произошла ошибка");
            }
        }
        storage.save(new Resume());
    }
}
