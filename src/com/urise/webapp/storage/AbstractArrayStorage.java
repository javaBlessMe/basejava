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

    @Override
    public Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    public void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    @Override
    public void updateResume(Resume r) {
        int position = getIndex(r.uuid);
        storage[position] = r;
    }

    @Override
    public void saveResume(Resume r) {
        if (resumeCount == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        addResume(resumeCount, r);
        resumeCount++;
    }

    @Override
    public void deleteResume(String uuid) {
        removeResume(uuid);
        resumeCount--;
    }

    @Override
    protected boolean isResumeExist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    protected abstract void removeResume(String uuid);

    protected abstract void addResume(int resumeCount, Resume r);

    protected abstract int getIndex(String uuid);
}
