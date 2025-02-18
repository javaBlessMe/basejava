/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int resumeQuantity = 0;

    void clear() {
        for (int i = 0; i < resumeQuantity; i++) {
            storage[i] = null;
        }
        resumeQuantity = 0;
    }

    void save(Resume r) {
        storage[resumeQuantity] = r;
        resumeQuantity++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumeQuantity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume[] tempStorage = new Resume[10000];
        int pointDivergence = 0;
        for (int i = 0; i < resumeQuantity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                pointDivergence++;
                continue;
            }
            tempStorage[i - pointDivergence] = storage[i];
        }
        storage = tempStorage;
        resumeQuantity--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] filledStorage = new Resume[resumeQuantity];
        System.arraycopy(storage, 0, filledStorage, 0, resumeQuantity);
        return filledStorage;
    }

    int size() {
        return resumeQuantity;
    }
}
