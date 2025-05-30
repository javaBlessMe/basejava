package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void removeResume(String uuid) {
        System.arraycopy(storage, getIndex(uuid) + 1, storage, getIndex(uuid), resumeCount - getIndex(uuid));
        storage[resumeCount - 1] = null;
    }

    @Override
    protected void addResume(int resumeCount, Resume r) {
        int index = getIndex(r.uuid);
        index = (index + 1) * (-1);
        if (resumeCount - index >= 0) System.arraycopy(storage, index, storage, index + 1, resumeCount - index);
        storage[index] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, resumeCount, searchKey);
    }

}
