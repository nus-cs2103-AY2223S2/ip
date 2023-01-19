/**
 * {@link Exception} that will eventually print out with {@code Resource.errBase} prefix.
 */
public class MeggyException extends Exception {
    /**
     * Adds prefix to msg
     *
     * @param msg Non-null. The detailed message.
     */
    public MeggyException(String msg) {
        super(Resource.errBase + msg);
    }

    /**
     * Function that throws {@link MeggyException}.
     */
    public interface Function<I, O> {
        O apply(I in) throws MeggyException;
    }
}
