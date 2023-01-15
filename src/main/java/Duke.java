import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================";
        System.out.print(welcomeMessage);

        TaskList taskList = new TaskList();
        String userPrompt = "\n>> ";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(userPrompt);
            String userInput = sc.nextLine();

            String[] tokens = userInput.split(" ");
            String action = tokens[0];

            switch (action) {
            case "list":
                taskList.handleListCommand();
                break;

            case "mark":
            case "unmark":
                taskList.handleMarkUnmarkCommand(tokens);
                break;

            case "todo":
                taskList.handleTodoCommand(tokens);
                break;

            case "deadline":
                taskList.handleDeadlineCommand(tokens);
                break;

            case "event":
                taskList.handleEventCommand(tokens);
                break;

            case "bye":
                taskList.handleByeCommand();
                sc.close();
                return;

            default:
                System.out.println("Recognised actions: list, mark, unmark, todo, deadline, event, bye");
                break;
            }
        }
    }
}
