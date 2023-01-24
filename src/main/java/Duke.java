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
            Action action;

            try {
                action = Action.valueOf(tokens[0]);
            } catch (IllegalArgumentException e) {
                System.out.println(new DukeUnknownActionException().getMessage());
                continue;
            }

            try {
                switch (action) {
                case list:
                    taskList.handleListCommand();
                    break;

                case mark:
                case unmark:
                    taskList.handleMarkUnmarkCommand(tokens);
                    break;

                case todo:
                    taskList.handleTodoCommand(tokens);
                    break;

                case deadline:
                    taskList.handleDeadlineCommand(tokens);
                    break;

                case event:
                    taskList.handleEventCommand(tokens);
                    break;

                case delete:
                    taskList.handleDeleteCommand(tokens);
                    break;

                case bye:
                    taskList.handleByeCommand();
                    sc.close();
                    return;

                case dueon:
                    taskList.handleDueOnCommand(tokens);
                    break;
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
