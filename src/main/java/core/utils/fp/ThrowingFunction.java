package core.utils.fp;

/**
 * The functional interface for a function that can throw an exception.
 *
 * @param <T> the type of the parameter.
 * @param <R> the return type of the function.
 * @param <E> the type of the exception that can be thrown.
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
    /**
     * Applies the function to the given parameter.
     *
     * @param param the parameter to which the function will be applied.
     * @return the result of the function.
     * @throws E if an exception occurs.
     */
    R apply(T param) throws E;
}
