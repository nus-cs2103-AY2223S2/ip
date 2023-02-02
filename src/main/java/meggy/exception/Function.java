package meggy.exception;

/**
 * Functional interface that supports {@link MeggyException} signature.
 *
 * @param <I> Input type.
 * @param <O> Output type.
 */
public interface Function<I, O> {
    /**
     * Function with {@link MeggyException} signature.
     *
     * @throws MeggyException
     */
    O apply(I in) throws MeggyException;
}
