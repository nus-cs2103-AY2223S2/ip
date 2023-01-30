package domain.entities.core;

/**
 * Interface for data that can be serialized.
 */
public interface Serializable {
    /**
     * Serializes the data.
     *
     * @return the serialized String representation of the data.
     */
    String serialize();
}
