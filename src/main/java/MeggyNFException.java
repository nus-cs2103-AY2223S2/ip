/**
 * Converts {@link NumberFormatException} into {@link MeggyException}.
 */
public class MeggyNFException extends MeggyException {
    /**
     * @param arg Non-null. The string that can't be parsed.
     */
    public MeggyNFException(String arg) {
        super(Resource.errNFE(arg));
    }
}
