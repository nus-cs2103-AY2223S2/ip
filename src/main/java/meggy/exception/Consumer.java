package meggy.exception;

/**
 * Functional consumer interface that supports {@link MeggyException} signature.
 *
 * @param <I> Input type.
 */
public interface Consumer<I> {
    /**
     * Consumer with {@link MeggyException} signature.
     *
     * @throws MeggyException
     */
    void accept(I in) throws MeggyException;
}
