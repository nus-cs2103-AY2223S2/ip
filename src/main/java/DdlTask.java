public class DdlTask extends UserTask {
    public final static String label = getTaskTypeLabel(Resource.cmdDdl);
    /**
     * 'Time' keyword formatted to be looked up in user input during parsing.
     */
    public final static String kwFmt = formatKeyword(Resource.kwDdl);
    public final static int kwLen = kwFmt.length();
    public final String time;

    /**
     * Constructor of parsed strings.
     */
    private DdlTask(String desc, String time) {
        super(desc);
        this.time = time;
    }

    /**
     * Factory method. Parses time and description.
     */
    public static DdlTask of(String args) {
        final int kwIdx = args.indexOf(kwFmt);
        // If no key word in args, time is set to "N/A".
        final String desc, time;
        if (kwIdx < 0) {
            desc = args;
            time = Util.noFound;
        } else {
            desc = args.substring(0, kwIdx).trim();
            time = args.substring(kwIdx + kwLen).trim();
        }
        return new DdlTask(desc, time);
    }

    @Override
    public String toString() {
        return label + super.toString() + " (by: " + time + ')';
    }
}
