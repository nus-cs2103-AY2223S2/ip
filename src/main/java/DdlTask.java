public class DdlTask extends UserTask {
    public final static String label = Util.parenthesize(Character.toUpperCase(Resource.cmdDdl.charAt(0)));
    public final static String keyword = '/' + Resource.kwDdl + ' ';
    public final static int kwLen = keyword.length();
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
        final int kwIdx = args.indexOf(keyword);
        // If no key word in args, time is set to "N/A".
        return kwIdx < 0 ? new DdlTask(args, "N/A") : new DdlTask(args.substring(0, kwIdx), args.substring(kwIdx + kwLen));
    }

    @Override
    public String toString() {
        return label + super.toString() + " (by: " + time + ')';
    }
}
