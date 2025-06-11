import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String wrongUuid = "dummy";

    private static final Resume resume1;
    private static final Resume resume2;
    private static final Resume resume3;
    private static final Resume resume4;

    static {
        resume1 = new Resume(UUID_1);
        resume2 = new Resume(UUID_2);
        resume3 = new Resume(UUID_3);
        resume4 = new Resume(UUID_4);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    public AbstractStorageTest(Storage storage) {
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
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Resume[] fact = storage.getAll();
        for (int i = 0; i < expected.length; i++) {
            if (Arrays.binarySearch(expected, 0, expected.length, fact[i]) < 0) {
                Assert.fail();
            }
        }
        Assert.assertEquals(expected.length, fact.length);
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
        assertSize(2);
        assertGet(resume1);
    }

    @Test
    public void get() throws Exception {
        assertGet(resume2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        assertGet(new Resume(wrongUuid));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(wrongUuid);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(wrongUuid));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(resume2);
    }

    private void assertSize(int size) {
        int factSize = storage.size();
        Assert.assertEquals(size, factSize);
    }

    private void assertGet(Resume resume) {
        Resume receiveResume = storage.get(resume.uuid);
        Assert.assertEquals(resume, receiveResume);
    }
}
