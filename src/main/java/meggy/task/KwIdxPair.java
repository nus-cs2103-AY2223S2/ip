package meggy.task;

/** Keyword-position pair. Enables index sorting of keywords by index positions. */
public class KwIdxPair implements Comparable<KwIdxPair> {
    public final String keyword;
    public final int idx;

    KwIdxPair(String keyword, int idx) {
        this.keyword = keyword;
        this.idx = idx;
    }

    /** Two {@link KwIdxPair} are compared by index only. */
    @Override
    public int compareTo(KwIdxPair o) {
        return Integer.compare(idx, o.idx);
    }
}
