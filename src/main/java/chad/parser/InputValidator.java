package chad.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import chad.exception.InvalidArgumentException;
import chad.exception.MissingArgumentException;
import chad.storage.TaskList;
import chad.task.Task;

/**
 * Input validator class to process and clean the user's request.
 */
public class InputValidator {
    private static final ArrayList<String> RESERVED_KEYWORDS = new ArrayList<>(Arrays.asList("/by", "/from", "/to"));
    private static final String DELIMITER_SPACE = " ";

    /**
     * Validates and cleanses the request to <code>Todo</code>
     *
     * @param request request string from the user
     * @return normalised request
     * @throws InvalidArgumentException
     * @throws MissingArgumentException
     */
    public static String normaliseTodoRequest(String request) throws
            InvalidArgumentException, MissingArgumentException {
        String[] req = request.split("todo");

        for (String s : req) {
            if (RESERVED_KEYWORDS.contains(s)) {
                throw new InvalidArgumentException("Do not include the reserved keywords in your request.");
            }
        }

        /* Checks for valid description, i.e., description cannot be empty */
        if (req.length == 0) {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String task = req[1].strip();
        return task;
    }

    /**
     * Validates and cleanses the request to <code>Deadline</code>
     *
     * @param request request string from the user
     * @return normalised request in the form [description, deadline]
     * @throws MissingArgumentException
     */
    public static String[] normaliseDeadlineRequest(String request) throws MissingArgumentException {
        String[] req = request.split("deadline");
        Boolean isContainByKeyword = false;

        /* Checks for the task description in the user's input. */
        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task description");
        }

        /* Checks for the keyword of Deadline in the user's input. */
        String trimmedRequest = req[1].strip();
        for (String s : RESERVED_KEYWORDS) {
            if (trimmedRequest.contains(s)) {
                isContainByKeyword = true;
                break;
            }
        }

        if (!isContainByKeyword) {
            throw new MissingArgumentException("Missing the /by keyword.");
        }

        String[] processedRequest = trimmedRequest.split("/by");
        if (processedRequest.length < 2) {
            throw new MissingArgumentException("Please follow the format deadline `task` /by YYYY-MM-DD HHmm");
        }

        String description = processedRequest[0].strip();
        String deadline = processedRequest[1].strip();
        String[] normalisedRequest = new String[]{description, deadline};

        return normalisedRequest;
    }

    /**
     * Validates and cleanses the request to <code>Event</code>
     *
     * @param request request string from the user
     * @return normalised request in the form [description, from, to]
     * @throws MissingArgumentException
     */
    public static String[] normaliseEventRequest(String request) throws MissingArgumentException {
        String[] req = request.split("event");
        Boolean isContainFromKeyword = false;
        Boolean isContainToKeyword = false;

        if (req.length < 2) {
            throw new MissingArgumentException("☹ OOPS!!! You're missing the task description");
        }

        /* Checks for the keyword of Deadline in the user's input. */
        String trimmedRequest = req[1].strip();
        for (String s : RESERVED_KEYWORDS) {
            if (trimmedRequest.contains(s) && s.equals("/from")) {
                isContainFromKeyword = true;
            }
            if (trimmedRequest.contains(s) && s.equals("/to")) {
                isContainToKeyword = true;
            }
        }

        if (!isContainFromKeyword) {
            throw new MissingArgumentException("Missing the /from keyword.");
        }

        if (!isContainToKeyword) {
            throw new MissingArgumentException("Missing the /to keyword.");
        }

        String[] processedFromRequest = trimmedRequest.split("/from");
        if (processedFromRequest.length < 2) {
            throw new MissingArgumentException("Please follow the format event `event` "
                    + "/from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }
        String description = processedFromRequest[0].strip();

        String[] processedToRequest = processedFromRequest[1].split("/to");
        if (processedToRequest.length < 2) {
            throw new MissingArgumentException("Please follow the format event `event` "
                    + "/from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }

        String from = processedToRequest[0].strip();
        String to = processedToRequest[1].strip();
        String[] response = new String[]{description, from, to};

        return response;
    }

    /**
     * Validates and cleanses the request to <code>Find</code>
     *
     * @param request request string from the user
     * @return normalised request, keyword for the find function
     * @throws MissingArgumentException
     */
    public static String normaliseFindRequest(String request) throws MissingArgumentException {
        String[] req = request.split("find");

        /* Checks for the description for `find` command */
        if (req.length == 0) {
            throw new MissingArgumentException("What do you wish to find?");
        }

        String keyword = req[1].strip();

        return keyword;
    }

    /**
     * Validates and cleanses the request to <code>Remove</code>
     *
     * @param request request string from the user
     * @return normalised request, index for the find function
     * @throws MissingArgumentException
     */
    public static String normaliseIndexRequest(String request, String type) throws MissingArgumentException {
        String[] req = request.split(type);

        /* Checks for the index for remove command. */
        if (req.length < 2) {
            throw new MissingArgumentException("Missing index for deletion!");
        }

        String idx = req[1].strip();

        return idx;
    }

    /**
     * Static method to check if user tried to add a duplicated task into the task list.
     * @param tasks Task list of the user.
     * @param task Task wish to be added by the user.
     * @return True if the there is duplicate, else false.
     */
    public static Boolean checkDuplicates(TaskList tasks, Task task) {
        ArrayList<Task> filteredTasks = tasks.getTasks()
                .stream()
                .filter(t -> t.getDescription().equals(task.getDescription()))
                .collect(Collectors.toCollection(ArrayList::new));
        return !filteredTasks.isEmpty();
    }

}
