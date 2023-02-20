package duke.storage;

/**
 * Provides a interface standard for objects that are meant to be stored in file.
 * Similar to Java's `Serializable` interface.
 */
public interface Storable {

    /**
     * Returns a string representation of this object.
     * Attributes of this object should be separated by commas.
     *
     * @return String of an object.
     */
    String toCsv();

}
