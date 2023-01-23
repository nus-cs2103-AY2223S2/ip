import java.util.Scanner;
import exception.*;
import message.DukeMessage;
import message.MessageGenerator;
import message.MessageStatus;
import parser.Parser;
import storage.Storage;
import task.TaskList;


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
