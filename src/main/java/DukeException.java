/**
 * Mother of all Exceptions for duke
 */
public class DukeException extends Exception {

    /**
     * Constructor to pass on forward to Exception
     * @param msg Message to pass to Exception
     */
    public DukeException(String msg) {
        super(msg);
    }
    private final static String OOPS = "☹ OOPS!!!";

    public static String OOPS() {
        return OOPS;
    }

    /**
     * For Exceptions involving Missing something.
     */
    public static class Missing extends DukeException {

        /**
         * Constructor to pass on forward to Exception
         * @param msg Message to pass to Exception
         */
        private Missing(String msg) {
            super(msg);
        }

        /**
         * For Exceptions involving Missing Description.
         */
        public static class Description extends Missing {
            /**
             * Description for particular Task type is missing.
             * Example: Event [Description] /from [String] /to [String]
             * Missing [Description] will lead to this error message.
             * @param taskType Name of the task type where this error occurs
             */
            public Description(String taskType) {
                super(String.format("%s Missing description for %s", OOPS, taskType));
            }
        }
        /**
         * For Exceptions involving Missing Parameters.
         */
        public static class Parameter extends Missing {
            /**
             * Parameter missing for particular keyword.
             * Example: Mark [Parameter]
             * Missing [Parameter] will lead to this error message.
             * @param taskType
             */
            public Parameter(String taskType) {
                super(String.format("%s Missing parameter(s) for %s", OOPS, taskType));
            }
        }

        /**
         * For Exceptions involving Missing Options.
         */
        public static class Options extends Missing {
            /**
             * Parameter missing for particular keyword.
             * Example: Deadline [Parameter] /by [Parameter]
             * Missing `/by` will lead to this error message.
             * @param taskType
             */
            public Options(String taskType) {
                super(String.format("%s Missing or incomplete options(s) for %s", OOPS, taskType));
            }
        }
    }

    /**
     * For Exceptions involving Invalid entries
     */
    public static class Invalid extends DukeException {
        /**
         * Constructor to pass on forward to Exception
         * @param msg Message to pass to Exception
         */
        private Invalid(String msg) {
            super(msg);
        }

        /**
         * For Exceptions involving Invalid Index
         */
        public static class Index extends Invalid {
            /**
             * Index is out of bound for particular collection.
             * Example:
             * User tries to delete entry `9` when the task list is empty.
             * @param ind User supplied input.
             */
            public Index(int ind) {
                super(String.format("%s The list does not contain index %d", OOPS, ind));
            }
        }

        /**
         * For Exceptions involving Invalid Input
         */
        public static class Input extends Invalid {
            /**
             * Generic exception handler for any Invalid Inputs.
             * @param msg The message to be passed to Exception
             */
            public Input(String msg) {
                super(String.format("%s %s", OOPS, msg));
            }
        }

        /**
         * For Exceptions involving Invalid Commands
         */
        public static class Command extends Invalid {
            /**
             * User supplied command does not exist.
             * Informs user of invalid command.
             */
            public Command() {
                super(String.format("%s I'm sorry, but I don't know what you just said means :-(", OOPS));
            }
        }
    }

    /**
     * For Exceptions involving Unimplemented features
     */
    public static class Unimplemented extends DukeException {
        /**
         * Constructor to pass on forward to Exception
         */
        public Unimplemented() {
            super("Apologise the command you just typed has not available yet.");
        }
    }
}
