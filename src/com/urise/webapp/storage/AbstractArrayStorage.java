package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    public static final int STORAGE_LIMIT = 10000;

    protected int resumeCount = 0;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return resumeCount;
    }


    public void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    @Override
    public void updateResume(Resume r, Object searchKey) {
        int position = (int) searchKey;
        storage[position] = r;
    }

    @Override
    public void saveResume(Resume r, Object searchKey) {
        if (resumeCount == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        addResume(resumeCount, r);
        resumeCount++;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected boolean isExisting(Object searchKey) {
        return (int)searchKey>=0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    protected abstract void addResume(int resumeCount, Resume r);

}
