package Meggy.Exception;

/**
 * Function that throws {@link MeggyException}.
 */
public interface Function<I, O> {
    O apply(I in) throws MeggyException;
}
