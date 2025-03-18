package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    //private int resumeCount = 0;


    @Override
    public void save(Resume r) {
        if (resumeCount == storage.length) {
            System.out.println("В базу уже записано максимально возможное количество резюме - " + resumeCount);
        } else if (getIndex(r.uuid) >= 0) {
            System.out.println("Резюме с uiid " + r.uuid + " уже есть в базе");
        } else {
            storage[resumeCount] = r;
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

        storage[position] = storage[resumeCount - 1];
        storage[resumeCount - 1] = null;
        resumeCount--;
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
