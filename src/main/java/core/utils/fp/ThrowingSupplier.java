package core.utils.fp;

/**
 * The functional interface for a supplier that can throw an exception.
 *
 * @param <R> the return type of the supplier, i.e. the type of the object to
 *            be supplied.
 * @param <E> the type of the exception that can be thrown.
 */
@FunctionalInterface
public interface ThrowingSupplier<R, E extends Exception> {
    /**
     * Supplies an object.
     *
     * @return the object supplied.
     * @throws E if an exception occurs.
     */
    R supply() throws E;
}
