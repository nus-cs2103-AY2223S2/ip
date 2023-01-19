public class MeggyIOBException extends MeggyException {
    /**
     * @param idx Index to program. Starts with 0.
     */
    public MeggyIOBException(int idx, int listSize) {
        super(Resource.errOutOfBounds(idx, listSize));
    }
}
