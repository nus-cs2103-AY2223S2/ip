import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "Jarvis";

    public static void main(String[] args) {
        Echo echo = new Echo(BOT_NAME);
        ToDoList toDoList = new ToDoList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        echo.printLine(String.format("Hello, I'm %s, how may I help you?", BOT_NAME));
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                echo.printLines(toDoList.getTasksForPrint());
            } else if (!command.isEmpty()) {
                echo.printLine(toDoList.addTask(command));
            }

            System.out.print("> ");
        }
        echo.printLine("Goodbye!");
    }
}
