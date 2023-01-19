import java.util.ArrayList;
import java.util.HashMap;

/**
 * {@link UserTask} with an optional start time and an optional end time.
 */
public class EventTask extends UserTask {
    /**
     * Bracketed icon of task type.
     */
    public final static String label = getTaskTypeLabel(Resource.cmdEvent);
    /**
     * 'Start' keyword formatted to be looked up in user input during parsing.
     */
    public final static String sttFmt = formatKeyword(Resource.kwStt);
    /**
     * 'End' keyword formatted to be looked up in user input during parsing.
     */
    public final static String endFmt = formatKeyword(Resource.kwEnd);
    /**
     * Start time.
     */
    public final String start;
    /**
     * end time.
     */
    public final String end;

    /**
     * @param desc  Non-null. Parsed description string.
     * @param start Non-null. Parsed start time string.
     * @param end   Non-null. Parsed end time string.
     */
    private EventTask(String desc, String start, String end) throws MeggyException {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method. Parses description, start time, and end time from arguments.
     *
     * @param args Non-null. User input line with command removed.
     */
    public static EventTask of(String args) throws MeggyException {
        final int argLen = args.length();
        // All positions of keywords.
        final ArrayList<KwIdxPair> kwIdxs = new ArrayList<>();
        // For simplicity, end of argument string is also a keyword position.
        kwIdxs.add(new KwIdxPair(null, argLen));
        // Record all keyword positions
        for (String keyword : new String[]{sttFmt, endFmt}) {
            final int idx = args.indexOf(keyword);
            if (idx >= 0)
                kwIdxs.add(new KwIdxPair(keyword, idx));
        }
        kwIdxs.sort(KwIdxPair::compareTo);
        // Assign the substring between 2 adjacent keywords to the left one.
        final HashMap<String, String> kwValue = new HashMap<>();
        final int nIndeces = kwIdxs.size() - 1;
        for (int i = 0; i < nIndeces; i++) {
            KwIdxPair pair = kwIdxs.get(i);
            kwValue.put(pair.keyword, args.substring(pair.idx + pair.keyword.length(), kwIdxs.get(i + 1).idx));
        }
        // The substring before the 1st keyword is description.
        final int descLim = kwIdxs.get(0).idx;
        final String desc = descLim >= argLen ? args : args.substring(0, descLim);
        // If "start" keyword is in args, write to start time variable. Otherwise use default.
        final String start = kwValue.getOrDefault(sttFmt, Util.noFound);
        // If "end" keyword is in args, write to end time variable. Otherwise use default.
        final String end = kwValue.getOrDefault(endFmt, Util.noFound);
        return new EventTask(desc.trim(), start.trim(), end.trim());
    }

    @Override
    public String toString() {
        return label + super.toString() + " (from: " + start + " to: " + end + ')';
    }

    /**
     * Keyword-position pair. Enables index sorting of keywords by index positions.
     */
    private static class KwIdxPair implements Comparable<KwIdxPair> {
        public final String keyword;
        public final int idx;

        private KwIdxPair(String keyword, int idx) {
            this.keyword = keyword;
            this.idx = idx;
        }

        @Override
        public int compareTo(KwIdxPair o) {
            return Integer.compare(idx, o.idx);
        }
    }
}
