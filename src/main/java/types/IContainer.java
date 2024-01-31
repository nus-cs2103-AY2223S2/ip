package types;

import java.util.List;

/**
 * An abstraction for persistence layer.
 * @param <T> Type of record to store.
 */
public interface IContainer<T> {
    /**
     * Add a new record.
     * @param record Record to add.
     */
    void add(T record);

    /**
     * Flush all records in the buffer to the desired location.
     */
    void flush();

    /**
     * Replace the content with the list of records.
     * @param records Records to push.
     */
    void push(List<T> records);

    /**
     * Parse the content and return as a list.
     * @return List of records.
     */
    List<T> fetch();
}
