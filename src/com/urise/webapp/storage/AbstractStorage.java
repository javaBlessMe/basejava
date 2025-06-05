package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isExisting(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void saveResume(Resume r, Object searchKey);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract Resume getResume( Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getResume( searchKey);
    }

    public void update(Resume r) {
     Object searchKey = getExistingSearchKey(r.uuid);
         updateResume(r, searchKey);

    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.uuid);
        saveResume(r, searchKey);

    }

    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
            deleteResume( searchKey);
    }

    private Object getExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if(isExisting(searchKey)){
            return searchKey;
        }
        else {
            throw new NotExistStorageException (uuid);
        }
    }

    private Object getNotExistingSearchKey(String uuid){
        Object searchKey = getSearchKey(uuid);
        if(isExisting(searchKey)){
            throw new ExistStorageException (uuid);
        }
        else  {
            return searchKey;
        }
    }
}
