/**
 * Converts upcoming {@link IndexOutOfBoundsException} into {@link MeggyException}.
 */
public class MeggyIOBException extends MeggyException {
    /**
     * @param idx      Index (starts with 0) in question.
     * @param listSize Size of list that would cause {@link IndexOutOfBoundsException} when queried with idx.
     */
    public MeggyIOBException(int idx, int listSize) {
        super(Resource.errOutOfBounds(idx, listSize));
    }
}
