public class MeggyException extends Exception {
    public MeggyException(String msg) {
        super(Resource.errBase + msg);
    }

    /**
     * Function that throws Meggy exception
     */
    public interface Function<I, O> {
        O apply(I in) throws MeggyException;
    }
}
