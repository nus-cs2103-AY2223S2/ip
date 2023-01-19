import java.util.ArrayList;
import java.util.HashMap;

public class EventTask extends UserTask {
    public final static String label = getTaskTypeLabel(Resource.cmdEvent);
    /**
     * 'Start' keyword formatted to be looked up in user input during parsing.
     */
    public final static String kwSttFmt = formatKeyword(Resource.kwStt);
    /**
     * 'End' keyword formatted to be looked up in user input during parsing.
     */
    public final static String kwEndFmt = formatKeyword(Resource.kwEnd);

    public final String start;
    public final String end;

    /**
     * @param desc  Parsed string description of task.
     * @param start Parsed start time string of task.
     * @param end   Parsed end time string of task.
     */
    private EventTask(String desc, String start, String end) throws MeggyException {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public static EventTask of(String args) throws MeggyException {
        final int argLen = args.length();
        final ArrayList<KwIdxPair> indeces = new ArrayList<>();
        indeces.add(new KwIdxPair(null, argLen));
        for (String keyword : new String[]{kwSttFmt, kwEndFmt}) {
            final int idx = args.indexOf(keyword);
            if (idx >= 0)
                indeces.add(new KwIdxPair(keyword, idx));
        }
        indeces.sort(KwIdxPair::compareTo);
        final HashMap<String, String> kwValue = new HashMap<>();
        final int nIndeces = indeces.size() - 1;
        for (int i = 0; i < nIndeces; i++) {
            KwIdxPair pair = indeces.get(i);
            kwValue.put(pair.keyword, args.substring(pair.idx + pair.keyword.length(), indeces.get(i + 1).idx));
        }
        final int descLim = indeces.get(0).idx;
        final String desc = descLim >= argLen ? args : args.substring(0, descLim);
        final String start = kwValue.getOrDefault(kwSttFmt, Util.noFound);
        final String end = kwValue.getOrDefault(kwEndFmt, Util.noFound);
        return new EventTask(desc.trim(), start.trim(), end.trim());
    }

    @Override
    public String toString() {
        return label + super.toString() + " (from: " + start + " to: " + end + ')';
    }

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
