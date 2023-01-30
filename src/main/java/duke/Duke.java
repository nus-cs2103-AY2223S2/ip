package duke;

import java.util.Scanner;

import duke.exception.*;
import duke.message.DukeMessage;
import duke.message.MessageGenerator;
import duke.message.MessageStatus;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the main class where response messages are printed to user,
 * in response to user's input.
 */
public class Duke {

    Storage storage = new Storage();
    TaskList taskList = new TaskList(storage.load());
    Parser parser = new Parser();
    MessageGenerator messageGenerator = new MessageGenerator(taskList, storage);

    public Duke() {}

    public String getInitMessage() {
        DukeMessage initMessage = new DukeMessage(MessageStatus.START);
        return initMessage.toString();
    }

    public String getResponse(String input) {

        try {
            MessageStatus responseStatus = parser.process(input);
            DukeMessage dukeResponse = messageGenerator.generate(responseStatus, input);
            return dukeResponse.toString();
        } catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException |
                InvalidEventException e) {
            return e.getMessage();
        }

    }
}
