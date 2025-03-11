package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int resumeCount = 0;

    public void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    public void save(Resume r) {
        if (resumeCount == storage.length) {
            System.out.println("В базу уже записано максимально возможное количество резюме - " + resumeCount);
        } else if (getPosition(r.uuid) >= 0) {
            System.out.println("Резюме с uiid " + r.uuid + " уже есть в базе");
        } else {
            storage[resumeCount] = r;
            resumeCount++;
        }

    }

    public Resume get(String uuid) {
        if (getPosition(uuid) < 0) {
            System.out.println("Резюме с uiid " + uuid + " нет базе");
            return null;
        }
        return storage[getPosition(uuid)];
    }

    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uiid " + uuid + " не существует");
            return;
        }

        storage[position] = storage[resumeCount - 1];
        storage[resumeCount - 1] = null;
        resumeCount--;
    }

    public void update(Resume r) {
        int position = getPosition(r.uuid);
        if (position < 0) {
            System.out.println("Резюме с uiid " + r.uuid + " не существует");
            return;
        }
        storage[position] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    public int size() {
        return resumeCount;
    }

    private int getPosition(String uuid) {
        int position = -1;
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return position;
    }
}
