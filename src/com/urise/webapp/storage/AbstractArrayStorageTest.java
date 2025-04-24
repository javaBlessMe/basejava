package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private final Resume resume1 = new Resume(UUID_1);
    private final Resume resume2 = new Resume(UUID_2);
    private final Resume resume3 = new Resume(UUID_3);

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
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(resume1);
        Assert.assertEquals(resume1, storage.get(resume1.uuid));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resume1, resumes[0]);
        Assert.assertEquals(resume2, resumes[1]);
        Assert.assertEquals(resume3, resumes[2]);
    }

    @Test
    public void save() throws Exception {
        Resume rNew = new Resume("uuid4");
        storage.save(rNew);
        Assert.assertEquals(rNew, storage.get("uuid4"));
        Assert.assertEquals(4, storage.size());
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
}
