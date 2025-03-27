package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected int resumeCount = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return resumeCount;
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Резюме с uiid " + uuid + " нет базе");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    public void clear() {
        Arrays.fill(storage, null);
        resumeCount = 0;
    }

    public void update(Resume r) {
        int position = getIndex(r.uuid);
        if (position < 0) {
            System.out.println("Резюме с uiid " + r.uuid + " не существует");
            return;
        }
        storage[position] = r;
    }

    public void save(Resume r) {
        if (resumeCount == storage.length) {
            System.out.println("В базу уже записано максимально возможное количество резюме - " + resumeCount);
        } else if (getIndex(r.uuid) >= 0) {
            System.out.println("Резюме с uiid " + r.uuid + " уже есть в базе");
        } else {
            addResume(resumeCount, r);
            resumeCount++;
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Резюме с uiid " + uuid + " не существует");
            return;
        }
        removeResume(uuid);
        resumeCount--;
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
