import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================\n";
        System.out.print(welcomeMessage);

        TaskList taskList = new TaskList();
        String userPrompt = ">> ";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(userPrompt);
            String userInput = sc.nextLine();

            if (userInput.equals("list")) {
                taskList.handleListCommand();
            } else if (userInput.matches("^mark.*$") || userInput.matches("^unmark.*$")) {
                taskList.handleMarkUnmarkCommand(userInput);
            } else if (userInput.equals("bye")) {
                taskList.handleByeCommand();
                sc.close();
                break;
            } else {
                taskList.createNewTask(userInput);
            }
        }
    }
}
