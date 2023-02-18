package meggy.task;

import java.util.ArrayList;
import java.util.HashMap;

import meggy.MeggyTime;
import meggy.Resource;
import meggy.exception.MeggyException;

/** {@link UserTask} with a start time and an end time. */
public class EventTask extends UserTask {
    /** Bracketed icon of task type. */
    public static final String LABEL = getTaskTypeLabel(Resource.CMD_EVENT);
    /** 'Start' keyword formatted to be looked up in user input during parsing. */
    public static final String START_KEYWORD_FORMATTED = formatKeyword(Resource.KW_STT);
    /** 'End' keyword formatted to be looked up in user input during parsing. */
    public static final String END_KEYWORD_FORMATTED = formatKeyword(Resource.KW_END);
    /** Start time. */
    public final MeggyTime start;
    /** End time. */
    public final MeggyTime end;

    /**
     * @param desc  Non-null. Parsed description string.
     * @param start Non-null. Parsed start time.
     * @param end   Non-null. Parsed end time.
     * @param args  The line (command and extra space removed) that created this task.
     */
    private EventTask(String desc, MeggyTime start, MeggyTime end, String args) throws MeggyException {
        super(desc, args);
        assert start != null;
        assert end != null;
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method. Parses description, start time, and end time from arguments.
     *
     * @param args Non-null. The line (command and extra space removed) that created this task.
     */
    public static EventTask of(String args) throws MeggyException {
        assert args != null;
        final int argLen = args.length();
        // All positions of keywords.
        final ArrayList<KwIdxPair> kwIdxs = new ArrayList<>();
        // For simplicity, end of argument string is also a keyword position.
        kwIdxs.add(new KwIdxPair(null, argLen));
        // Record all keyword positions
        for (String keyword : new String[]{START_KEYWORD_FORMATTED, END_KEYWORD_FORMATTED}) {
            final int idx = args.indexOf(keyword);
            if (idx >= 0) {
                kwIdxs.add(new KwIdxPair(keyword, idx));
            }
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
        final String start = kwValue.get(START_KEYWORD_FORMATTED);
        // If "end" keyword is in args, write to end time variable. Otherwise use default.
        final String end = kwValue.get(END_KEYWORD_FORMATTED);
        return new EventTask(desc.trim(), MeggyTime.of(start), MeggyTime.of(end), args);
    }

    /** @inheritDoc */
    @Override
    public String recreateCmd() {
        return Resource.CMD_EVENT + ' ' + args;
    }

    /** @inheritDoc */
    @Override
    public String toString() {
        return LABEL + super.toString() + " (from: " + start + " to: " + end + ')';
    }

    /** Two {@link EventTask} objects are equal iff they have same (non-null) description, due time, and start time. */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EventTask)) {
            return false;
        }
        final EventTask other = (EventTask) o;
        return start.equals(other.start) && end.equals(other.end) && desc.equals(other.desc);
    }
}
