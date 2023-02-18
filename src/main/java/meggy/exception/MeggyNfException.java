package meggy.exception;

import meggy.Resource;

/** Converts {@link NumberFormatException} into {@link MeggyException}. */
public class MeggyNfException extends MeggyException {
    /** @param arg Non-null. The string that can't be parsed. */
    public MeggyNfException(String arg) {
        super(Resource.fmtErrNfe(arg));
    }
}
