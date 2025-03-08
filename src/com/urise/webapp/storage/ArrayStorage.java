package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int resumeCount = 0;

    public void clear() {
        for (int i = 0; i < resumeCount; i++) {
            storage[i] = null;
        }
        resumeCount = 0;
    }

    public void save(Resume r) {
        if (resumeCount == storage.length) {
            System.out.println("В базу уже записано максимально возможное количество резюме - " + resumeCount);
        } else if (getPosition(r.uuid) < 0) {
            storage[resumeCount] = r;
            resumeCount++;
        } else {
            System.out.println("Резюме с uiid " + r.uuid + " уже есть в базе");
        }

    }

    public Resume get(String uuid) {
        if (getPosition(uuid) >= 0) {
            return storage[getPosition(uuid)];
        } else {
            System.out.println("Резюме с uiid " + uuid + " нет базе");
            return null;
        }
    }

    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position >= 0) {
            storage[position] = storage[resumeCount - 1];
            storage[resumeCount - 1] = null;
            resumeCount--;
        } else {
            System.out.println("Резюме с uiid " + uuid + " не существует");
        }
    }

    public void update(Resume r) {
        int position = getPosition(r.uuid);
        if (position > 0) {
            storage[position] = r;
        } else {
            System.out.println("Резюме с uiid " + r.uuid + " не существует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] onlyResumes = new Resume[resumeCount];
        System.arraycopy(storage, 0, onlyResumes, 0, resumeCount);
        return onlyResumes;
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
