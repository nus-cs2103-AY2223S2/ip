package duke;

import java.util.Scanner;
import duke.exception.*;
import duke.message.DukeMessage;
import duke.message.MessageGenerator;
import duke.message.MessageStatus;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.load());
        Parser parser = new Parser();
        MessageGenerator messageGenerator = new MessageGenerator(taskList, storage);
        DukeMessage initMessage = new DukeMessage(MessageStatus.START);
        System.out.println(initMessage);

        Scanner scanner = new Scanner(System.in);

        while (!end && scanner.hasNextLine()) {
            String userMessage = scanner.nextLine();
            if (userMessage.equals("bye")) {
                end = true;
                scanner.close();
            }
            try {
                MessageStatus responseStatus = parser.process(userMessage);
                DukeMessage dukeResponse =  messageGenerator.generate(responseStatus, userMessage);
                System.out.println(dukeResponse);
            } catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }

        }


    }
}
