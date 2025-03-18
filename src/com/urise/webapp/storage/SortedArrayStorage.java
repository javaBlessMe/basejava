package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (resumeCount == storage.length) {
            System.out.println("В базу уже записано максимально возможное количество резюме - " + resumeCount);
        } else if (getIndex(r.uuid) >= 0) {
            System.out.println("Резюме с uiid " + r.uuid + " уже есть в базе");
        } else {
            int index = getIndex(r.uuid);
            index = (index + 1) * (-1);
            if (resumeCount - index >= 0) System.arraycopy(storage, index, storage, index + 1, resumeCount - index);
            storage[index] = r;
            resumeCount++;
        }
    }

    @Override
    public void delete(String uuid) {
        int position = getIndex(uuid);
        if (position < 0) {
            System.out.println("Резюме с uiid " + uuid + " не существует");
            return;
        }
        System.arraycopy(storage, position + 1, storage, position, resumeCount - position);
        storage[resumeCount - 1] = null;
        resumeCount--;
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
