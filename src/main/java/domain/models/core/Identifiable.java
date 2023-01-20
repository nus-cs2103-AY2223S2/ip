package domain.eventloop;

/**
 * An interface for objects that have a id, so that in a sense they could be
 * located in hash tables using this key.
 */
public interface Identifiable {
    /**
     * Gets the id of the Keyable object, which can be stored as keys for a
     * HashMap.
     * @return the id of the Keyable object.
     */
    String getId();
}
