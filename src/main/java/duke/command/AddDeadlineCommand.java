package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidArgumentException;
import duke.exception.MissingArgumentException;
import duke.parser.DateTimeParser;
import duke.parser.InputValidator;
import duke.storage.TaskList;
import duke.task.Deadline;

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
        Deadline newDeadline = tasks.addDeadline(description, dueDate);

        String response = String.format("Great! I've added this task for you\n %s \n"
                + "You have %d tasks in the list.", newDeadline, tasks.numOfTask());

        return response;


    }
}
