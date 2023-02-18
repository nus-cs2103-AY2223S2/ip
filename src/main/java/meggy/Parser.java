package meggy;

import java.util.Map;

import meggy.exception.Function;
import meggy.exception.MeggyException;
import meggy.exception.MeggyNfException;
import meggy.exception.MeggyNoArgException;

/** Contains various static parsing methods. */
public class Parser {
    /** @deprecated Class with all methods static should not be initialized. */
    private Parser() {
    }

    /**
     * Parses the index integer from the first arg in args string.
     *
     * @param args Non-null. Trimmed arguments string.
     * @return Parsed idx (starts with 0)
     * @throws MeggyNoArgException If args string is empty.
     * @throws MeggyNfException    If args string's first word is not a signed 32-bit {@link Integer}.
     */
    public static int parseIdx(String args) throws MeggyException {
        assert args != null;
        final String arg = get1stArg(args);
        if (arg.isEmpty()) {
            throw new MeggyNoArgException();
        }
        final int idx;
        try {
            idx = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new MeggyNfException(arg);
        }
        return idx;
    }

    /**
     * Parses text line into command, arguments, and finds job according to job table. All continuous whitespaces are
     * replaced with a single whitespace.
     *
     * @param <E>      The return type of job functions in job table.
     * @param jobTable Non-null. The mapping from command name to command job function.
     * @param line     Non-null. Unprocessed User's input line.
     * @return Parsed command, job, and argument encapsulated in an {@code JobAndArg} object.
     */
    public static <E> JobAndArg<E> parseJobAndArg(Map<String, Function<String, E>> jobTable, String line) {
        assert jobTable != null;
        assert line != null;
        //Multiple whitespace characters are treated as 1 whitespace.
        line = line.replaceAll("[ \t\r\n\f]+", " ").trim();
        //Parse command and args
        final int spaceIdx = line.indexOf(' ');
        final boolean noSpace = spaceIdx < 0;
        final String cmd = (noSpace ? line : line.substring(0, spaceIdx)).toLowerCase();
        final Function<String, E> job = jobTable.get(cmd);
        //No corresponding job? args is line. Otherwise, args is everything after first space. No space? Empty args.
        final String args = job == null ? line : noSpace ? "" : line.substring(spaceIdx + 1).trim();
        return new JobAndArg<>(cmd, job, args);
    }

    /**
     * Gets first argument in string that separates args with whitespace.
     *
     * @param args Non-null. Trimmed arguments string.
     * @return The substring before the 1st whitespace character, or original string if no whitespace exists.
     */
    public static String get1stArg(String args) {
        assert args != null;
        final int whiteSpaceIdx = args.indexOf(' ');
        return whiteSpaceIdx < 0 ? args : args.substring(0, whiteSpaceIdx);
    }

    /**
     * Entry class that stores parsed value of command string, the corresponding job function, and args string.
     *
     * @param <E> The return type of the job function.
     */
    public static class JobAndArg<E> {
        public final String cmd;
        /**
         * The command-specific function corresponding that will take {@code args} as arguments. Null if the command is
         * unknown.
         */
        public final Function<String, E> job;
        public final String args;

        private JobAndArg(String cmd, Function<String, E> job, String args) {
            this.cmd = cmd;
            this.job = job;
            this.args = args;
        }
    }
}
