package duke.parser;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Makes sense of the file input from the local file.
 */
public class FileInputParser {
    protected enum TaskType {
        T,
        D,
        E
    }

    private enum MinimumLengths {
        TODO(3),
        DEADLINE(4),
        EVENT(5);

        public final int length;

        MinimumLengths(int length) {
            this.length = length;
        }
    }

    /**
     * Processes one task from the list of tasks in file that is stored in the local storage.
     *
     * @param input One task to be processed.
     * @throws DukeException Throws exception if input format is invalid.
     */
    public static Task parse(String input) throws DukeException {
        String[] splitInputs = input.split(" ~ ");
        return getTask(getTaskType(splitInputs), splitInputs);
    }

    protected static TaskType getTaskType(String[] splitInputs) throws DukeException {
        try {
            return TaskType.valueOf(splitInputs[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
    }

    /**
     * Returns Task depending on the file input.
     *
     * @param taskType specified in the file input.
     * @param splitInputs from an array of Strings.
     * @return Task.
     * @throws DukeException if invalid file input format is used.
     */
    protected static Task getTask(TaskType taskType, String[] splitInputs) throws DukeException {
        switch (taskType) {
        case T:
            checkFileInputFormat(splitInputs.length, MinimumLengths.TODO.length,
                    "Todo task is of invalid format in the file.");
            return new Todo(splitInputs[2], splitInputs[1]);
        case D:
            checkFileInputFormat(splitInputs.length, MinimumLengths.DEADLINE.length,
                    "Deadline task is of invalid format in the file.");
            return new Deadline(String.format("%s /by %s", splitInputs[2], splitInputs[3]), splitInputs[1]);
        case E:
            checkFileInputFormat(splitInputs.length, MinimumLengths.EVENT.length,
                    "Event task is of invalid format in the file.");
            return new Event(String.format("%s /from %s /to %s", splitInputs[2], splitInputs[3],
                    splitInputs[4]), splitInputs[1]);
        default:
            return null;
        }
    }

    /**
     * Checks the format of the input in the local file.
     *
     * @param inputLength of the task in the local file.
     * @param minimumLength of the input.
     * @param errorMessage to be printed on the program.
     */
    private static void checkFileInputFormat(int inputLength, int minimumLength, String errorMessage) {
        boolean isValidLength = inputLength >= minimumLength;
        assert isValidLength : String.format("Local file data/tasks.txt does not have valid format. %s",
                errorMessage);
    }
}
