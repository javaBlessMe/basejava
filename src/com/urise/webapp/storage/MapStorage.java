package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    private HashMap<Integer, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExisting(Object searchKey) {
        return searchKey!=null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {

    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {

    }

    @Override
    protected Resume getResume(Object searchKey) {
        return null;
    }

    @Override
    protected void deleteResume(Object searchKey) {

    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }
}
