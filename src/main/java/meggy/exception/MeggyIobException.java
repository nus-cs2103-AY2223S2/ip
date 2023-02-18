package meggy.exception;

import meggy.Resource;

/** Converts upcoming {@link IndexOutOfBoundsException} into {@link MeggyException}. */
public class MeggyIobException extends MeggyException {
    /**
     * @param idx      Index (starts with 0) in question.
     * @param listSize Size of list that would cause {@link IndexOutOfBoundsException} when queried with idx.
     */
    public MeggyIobException(int idx, int listSize) {
        super(Resource.fmtErrOutOfBounds(idx, listSize));
    }
}
