package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    private HashMap<String, Resume> storage = new HashMap<>();


    @Override
    protected boolean isExisting(Object searchKey) {
        return storage.containsKey(searchKey.toString());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storage.put(searchKey.toString(), r);
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storage.put(searchKey.toString(), r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get(searchKey.toString());
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove(searchKey.toString());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> values = storage.values();

        return values.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
