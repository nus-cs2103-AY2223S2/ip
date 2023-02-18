package meggy.task;

import meggy.MeggyTime;
import meggy.Resource;
import meggy.exception.MeggyException;

/** {@link UserTask} with a due time. */
public class DdlTask extends UserTask {
    /** Bracketed icon of task type. */
    public static final String LABEL = getTaskTypeLabel(Resource.CMD_DDL);
    /** 'Due' keyword formatted to be looked up in user input during parsing. */
    public static final String DUE_KEYWORD_FORMATTED = fmtKeyword(Resource.KW_DUE);
    /** Formatted 'Due' keyword length. Cached for later use. */
    public static final int DUE_LEN = DUE_KEYWORD_FORMATTED.length();
    /** Due time. */
    public final MeggyTime due;

    /**
     * @param desc   Non-null. Description string of task.
     * @param due    Non-null. Due time.
     * @param origin Non-null. The line (command removed) that created this task.
     */
    private DdlTask(String desc, MeggyTime due, String origin) throws MeggyException {
        super(desc, origin);
        assert due != null;
        this.due = due;
    }

    /**
     * Parses description and due time from arguments. Factory method.
     *
     * @param args Non-null. The line (command and extra space removed) that created this task.
     */
    public static DdlTask of(String args) throws MeggyException {
        assert args != null;
        final int kwIdx = args.indexOf(DUE_KEYWORD_FORMATTED);
        // If no key word in args, time is set to "N/A".
        final String desc;
        final MeggyTime due;
        if (kwIdx < 0) {
            desc = args;
            due = MeggyTime.NA;
        } else {
            desc = args.substring(0, kwIdx).trim();
            due = MeggyTime.of(args.substring(kwIdx + DUE_LEN));
        }
        return new DdlTask(desc, due, args);
    }

    /** @inheritDoc */
    @Override
    public String recreateCmd() {
        return Resource.CMD_DDL + ' ' + args;
    }

    /** Two {@link DdlTask} objects are equal iff they have same (non-null) description and due time. */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DdlTask)) {
            return false;
        }
        final DdlTask other = (DdlTask) o;
        return due.equals(other.due) && desc.equals(other.desc);
    }

    /** @inheritDoc */
    @Override
    public String toString() {
        return LABEL + super.toString() + " (by: " + due + ')';
    }
}
