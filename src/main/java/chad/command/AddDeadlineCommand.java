package chad.command;

import java.time.LocalDateTime;

import chad.exception.InvalidArgumentException;
import chad.exception.MissingArgumentException;
import chad.parser.DateTimeParser;
import chad.parser.InputValidator;
import chad.storage.TaskList;
import chad.task.Deadline;

/**
 * Class use to handle command: add deadline.
 * Allows user to add deadline into the task list.
 */
public class AddDeadlineCommand extends Command {

    private String request;

    /**
     * Constructor to create deadline task according user's request.
     * @param request user's request to be processed into deadline duke.task.
     */
    public AddDeadlineCommand(String request) {
        this.request = request;
    }

    /**
     * Execute the <code>Deadline</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response after added deadline into task list.
     * @throws MissingArgumentException
     * @throws InvalidArgumentException
     */
    @Override
    public String execute(TaskList tasks) throws MissingArgumentException, InvalidArgumentException {

        String[] normalisedRequest = InputValidator.normaliseDeadlineRequest(request);
        String description = normalisedRequest[0];
        String deadline = normalisedRequest[1];

        LocalDateTime dueDate = DateTimeParser.parse(deadline);
        Deadline duplicateChecker = new Deadline(description, dueDate);

        if (InputValidator.checkDuplicates(tasks, duplicateChecker)) {
            return String.format("You have already added this into your task list.\n Duplicated Task: %s",
                    duplicateChecker.toString());
        }

        Deadline newDeadline = tasks.addDeadline(description, dueDate);
        String response = String.format("Great! I've added this task for you\n %s \n"
                + "You have %d tasks in the list.", newDeadline, tasks.numOfTask());

        return response;


    }
}
