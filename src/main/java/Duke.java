import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean end = false;
        TaskList taskList = new TaskList();
        MessageProcessor messageProcessor = new MessageProcessor(taskList);
        DukeMessage initMessage = new DukeMessage(MessageStatus.START);
        System.out.println(initMessage);

        while (!end) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();
            if (userMessage.equals("bye")) {
                end = true;
            }
            DukeMessage dukeResponse = messageProcessor.process(userMessage);
            System.out.println(dukeResponse);
        }


    }
}
