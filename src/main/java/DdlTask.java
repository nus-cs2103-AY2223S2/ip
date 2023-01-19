/**
 * {@link UserTask} with an optional due time.
 */
public class DdlTask extends UserTask {
    /**
     * Bracketed icon of task type.
     */
    public final static String label = getTaskTypeLabel(Resource.cmdDdl);
    /**
     * 'Due' keyword formatted to be looked up in user input during parsing.
     */
    public final static String dueFmt = formatKeyword(Resource.kwDue);
    /**
     * Formatted 'Due' keyword length. Cached for later use.
     */
    public final static int dueLen = dueFmt.length();
    /**
     * Due time.
     */
    public final String due;

    /**
     * @param desc Non-null. Description string of task.
     * @param due  Non-null. Due time.
     */
    private DdlTask(String desc, String due) throws MeggyException {
        super(desc);
        this.due = due;
    }

    /**
     * Factory method. Parses description and due time from arguments.
     *
     * @param args Non-null. User input line with command removed.
     */
    public static DdlTask of(String args) throws MeggyException {
        final int kwIdx = args.indexOf(dueFmt);
        // If no key word in args, time is set to "N/A".
        final String desc, due;
        if (kwIdx < 0) {
            desc = args;
            due = Util.noFound;
        } else {
            desc = args.substring(0, kwIdx).trim();
            due = args.substring(kwIdx + dueLen).trim();
        }
        return new DdlTask(desc, due);
    }

    @Override
    public String toString() {
        return label + super.toString() + " (by: " + due + ')';
    }
}
