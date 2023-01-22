import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.load());
        MessageProcessor messageProcessor = new MessageProcessor(taskList, storage);
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
                DukeMessage dukeResponse = messageProcessor.process(userMessage);
                System.out.println(dukeResponse);
            } catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }

        }


    }
}
