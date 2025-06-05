package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(int resumeCount, Resume r) {
        storage[resumeCount] = r;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage[(int) searchKey] = storage[resumeCount - 1];
        storage[resumeCount - 1] = null;
        resumeCount--;
    }
}
