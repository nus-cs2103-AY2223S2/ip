public class MeggyNFException extends MeggyException {
    /**
     * @param arg The string that can't be parsed.
     */
    public MeggyNFException(String arg) {
        super(Resource.errNFE(arg));
    }
}
