import exception.CommandParseException;
import exception.JarvisException;
import exception.InvalidActionException;
import exception.MissingParameterException;

import java.util.Scanner;

public class Jarvis {
    public static final String BOT_NAME = Jarvis.class.getSimpleName();

    public static void main(String[] args) {
        Echo echo = new Echo(BOT_NAME);
        TaskList taskList = new TaskList();

        String logo = "     _   _    ______     _____ ____  \n" +
                "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
                " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
                "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
                " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

        System.out.println(logo);
        echo.printStandardResponse(Echo.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isBlank()) continue;

            Command command;
            try {
                command = new Command(line);
            } catch (CommandParseException e) {
                echo.printStandardResponse(Echo.Response.CONFUSED);
                continue;
            }

            if (command.hasAction(Command.Action.BYE)) {
                break;
            } else if (command.hasAction(Command.Action.LIST)) {
                echo.printResponse(taskList.getTasksForPrint());
            } else {
                try {
                    if (command.hasAction(Command.Action.MARK_DONE) || command.hasAction(Command.Action.MARK_UNDONE)) {
                        echo.printResponse(taskList.setTaskDone(command));
                    } else if (command.hasAction(Command.Action.DELETE_TASK)) {
                        echo.printResponse(taskList.deleteTask(command));
                    } else if (command.hasAction(Command.Action.CREATE_TODO)) {
                        echo.printResponse(taskList.addTask(command.toToDoTask()));
                    } else if (command.hasAction(Command.Action.CREATE_DEADLINE)) {
                        echo.printResponse(taskList.addTask(command.toDeadlineTask()));
                    } else if (command.hasAction(Command.Action.CREATE_EVENT)) {
                        echo.printResponse(taskList.addTask(command.toEventTask()));
                    }
                } catch (InvalidActionException e) {
                    echo.printStandardResponse(Echo.Response.CONFUSED);
                } catch (JarvisException e) {
                    echo.printErrorResponse(e.getFriendlyMessage());
                }
            }
        }

        echo.printStandardResponse(Echo.Response.GOODBYE);
        scanner.close();
    }
}
