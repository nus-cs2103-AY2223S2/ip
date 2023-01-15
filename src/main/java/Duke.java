import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Jarvis";

    public static void main(String[] args) {
        Echo echo = new Echo(BOT_NAME);
        TaskList taskList = new TaskList();

        String logo = "     _   _    ______     _____ ____  \n" +
                "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
                " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
                "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
                " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

        System.out.println(logo);
        echo.printResponse(String.format("Hello, I'm %s, how may I help you?", BOT_NAME));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Command command = new Command(scanner.nextLine());

            if (command.hasAction(Command.Action.BYE)) {
                break;
            } else if (command.hasAction(Command.Action.LIST)) {
                echo.printResponse(taskList.getTasksForPrint());
            } else if (command.hasAction(Command.Action.MARK_DONE) || command.hasAction(Command.Action.MARK_UNDONE)) {
                echo.printResponse(taskList.setTaskDone(command.toString()));
            } else if (command.hasAction(Command.Action.CREATE_TODO)) {
                echo.printResponse(taskList.addTask(command.toToDoTask()));
            } else if (command.hasAction(Command.Action.CREATE_DEADLINE)) {
                echo.printResponse(taskList.addTask(command.toDeadlineTask()));
            } else if (command.hasAction(Command.Action.CREATE_EVENT)) {
                echo.printResponse(taskList.addTask(command.toEventTask()));
            } else if (!command.isEmpty()) {
                echo.printResponse("I don't quite understand, please try again.");
            }
        }
        echo.printResponse("Goodbye!");
    }
}
