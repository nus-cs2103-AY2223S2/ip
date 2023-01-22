package utils;

/**
 * An interface for performing the unmarshalling action.
 *
 * @param <T> The type to unmarshal to.
 */
public interface IUnmarshal<T> {
    T unmarshal();
}
