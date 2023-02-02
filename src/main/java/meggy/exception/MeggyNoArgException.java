package meggy.exception;

import meggy.Resource;

/** Deals with the absence of argument after commands that needs argument(s). */
public class MeggyNoArgException extends MeggyException {
    /** Constructor needs no argument because error message is always {@code Resource.ERR_NO_ARGS} */
    public MeggyNoArgException() {
        super(Resource.ERR_NO_ARGS);
    }
}
