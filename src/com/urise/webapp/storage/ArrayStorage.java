package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public void save(Resume r) {
        super.save(r);
        if (getIndex(r.uuid) > -10000 && getIndex(r.uuid) < 0) {
            storage[resumeCount] = r;
            resumeCount++;
        }
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
        if (getIndex(uuid) >= 0) {
            storage[getIndex(uuid)] = storage[resumeCount - 1];
            storage[resumeCount - 1] = null;
            resumeCount--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
