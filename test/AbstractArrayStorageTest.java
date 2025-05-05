import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        Resume[] clearStorage = new Resume[0];
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(storage.getAll(), clearStorage);
    }

    @Test
    public void update() throws Exception {
        Resume updateResume = new Resume("uuid1");
        storage.update(updateResume);
        Assert.assertSame(updateResume, storage.get("uuid1"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expected = new Resume[3];
        expected[0] = resume1;
        expected[1] = resume2;
        expected[2] = resume3;
        Assert.assertArrayEquals(expected,storage.getAll());
    }

    @Test
    public void save() throws Exception {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        assertGet(newResume);
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
        storage.get("uuid1");
    }

    @Test
    public void get() throws Exception {
        Resume r = storage.get("uuid2");
        Assert.assertEquals(resume2, r);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(resume2);
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

    private void assertSize(int size){
        int factSize = storage.size();
        Assert.assertEquals(size,factSize);
    }

    private void assertGet(Resume resume){
        Resume receiveResume = storage.get(resume.uuid);
        Assert.assertEquals(resume, receiveResume);
    }
}
