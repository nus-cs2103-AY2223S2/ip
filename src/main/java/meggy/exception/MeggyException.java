package meggy.exception;

import meggy.Resource;

/** {@link Exception} that will eventually print out with {@code Resource.ERR_BASE} prefix. */
public class MeggyException extends Exception {
    /**
     * Adds prefix to message.
     *
     * @param msg Non-null. The detailed message.
     */
    public MeggyException(String msg) {
        super(Resource.ERR_BASE + msg);
    }
}
