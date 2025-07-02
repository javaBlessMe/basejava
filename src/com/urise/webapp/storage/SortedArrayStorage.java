package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
*/

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected void addResume(int resumeCount, Resume r) {
        int index = (int) getSearchKey(r.uuid);
        index = (index + 1) * (-1);
        if (resumeCount - index >= 0) System.arraycopy(storage, index, storage, index + 1, resumeCount - index);
        storage[index] = r;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, resumeCount, RESUME_COMPARATOR);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        int key = (int)searchKey;
        System.arraycopy(storage, key + 1, storage, key, resumeCount - key);
        storage[resumeCount - 1] = null;
        resumeCount--;
    }
}
