/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int resumeCount = 0;

    void clear() {
        for (int i = 0; i < resumeCount; i++) {
            storage[i] = null;
        }
        resumeCount = 0;
    }

    void save(Resume r) {
        storage[resumeCount] = r;
        resumeCount++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int pointDivergence = 0;
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                pointDivergence++;
                continue;
            }
            storage[i - pointDivergence] = storage[i];
        }
        if (pointDivergence != 0) {
            storage[resumeCount - 1] = null;
            resumeCount--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] onlyResumes = new Resume[resumeCount];
        System.arraycopy(storage, 0, onlyResumes, 0, resumeCount);
        return onlyResumes;
    }

    int size() {
        return resumeCount;
    }
}
