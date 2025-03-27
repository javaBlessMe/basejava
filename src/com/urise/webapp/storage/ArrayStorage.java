package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void removeResume(String uuid) {
        storage[getIndex(uuid)] = storage[resumeCount - 1];
        storage[resumeCount - 1] = null;
    }

    @Override
    protected void addResume(int resumeCount, Resume r) {
        storage[resumeCount] = r;
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
