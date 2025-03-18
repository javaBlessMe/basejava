package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected int resumeCount = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return resumeCount;
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Резюме с uiid " + uuid + " нет базе");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    public void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    public void update(Resume r) {
        int position = getIndex(r.uuid);
        if (position < 0) {
            System.out.println("Резюме с uiid " + r.uuid + " не существует");
            return;
        }
        storage[position] = r;
    }

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

    protected abstract int getIndex(String uuid);
}
