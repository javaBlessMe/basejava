package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();
//48:54
    @Override
    protected boolean isResumeExist(String uuid) {
        return getIndex(uuid) >= 0;
    }

    @Override
    protected void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateResume(Resume r) {
        int index = getIndex(r.uuid);
        storage.add(index,r);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return storage.get(index);
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        storage.remove(index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            String resumeUuid = storage.get(i).uuid;
            if (resumeUuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
