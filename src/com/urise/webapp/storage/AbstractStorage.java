package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract boolean isResumeExist(String uuid);

    protected abstract void saveResume(Resume r);

    protected abstract void updateResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    public Resume get(String uuid) {
        if (isResumeExist(uuid)) {
            return getResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume r) {
        if (isResumeExist(r.uuid)) {
            updateResume(r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        if (!isResumeExist(r.uuid)) {
            saveResume(r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public void delete(String uuid) {
        if (isResumeExist(uuid)) {
            deleteResume(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}
