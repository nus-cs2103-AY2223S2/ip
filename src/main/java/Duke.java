import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Jarvis";

    public static void main(String[] args) {
        Echo echo = new Echo(BOT_NAME);
        ToDoList toDoList = new ToDoList();

        String logo = "     _   _    ______     _____ ____  \n" +
                "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
                " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
                "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
                " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

        System.out.println(logo);
        echo.printResponse(String.format("Hello, I'm %s, how may I help you?", BOT_NAME));
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                echo.printResponse(toDoList.getTasksForPrint());
            } else if (command.contains("mark") || command.contains("unmarked")) {
                echo.printResponse(toDoList.setTaskDone(command));
            } else if (!command.isEmpty()) {
                echo.printResponse(toDoList.addTask(command));
            }

            System.out.print("> ");
        }
        echo.printResponse("Goodbye!");
    }
}
