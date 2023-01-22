package utils;

/**
 * An interface for performing the marshalling action.
 *
 * @param <T> The type to marshal to.
 */
public interface IMarshal<T> {
    T marshal();
}
