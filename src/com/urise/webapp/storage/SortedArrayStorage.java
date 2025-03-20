package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        super.save(r);
        int index = getIndex(r.uuid);
        if (index > -10000 && index < 0) {
            index = (index + 1) * (-1);
            if (resumeCount - index >= 0) System.arraycopy(storage, index, storage, index + 1, resumeCount - index);
            storage[index] = r;
            resumeCount++;
        }
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
        if (getIndex(uuid) >= 0) {
            System.arraycopy(storage, getIndex(uuid) + 1, storage, getIndex(uuid), resumeCount - getIndex(uuid));
            storage[resumeCount - 1] = null;
            resumeCount--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, resumeCount, searchKey);
    }
}
