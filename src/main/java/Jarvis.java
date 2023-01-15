import exception.CommandParseException;
import exception.JarvisException;
import exception.InvalidActionException;

import java.util.Scanner;

public class Jarvis {
    public static final String BOT_NAME = Jarvis.class.getSimpleName();

    public static void main(String[] args) {
        Printer printer = new Printer(BOT_NAME);
        TaskList taskList = new TaskList();

        String logo = "     _   _    ______     _____ ____  \n" +
                "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
                " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
                "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
                " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

        System.out.println(logo);
        printer.printStandardResponse(Printer.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                printer.printUserCaret();
                continue;
            }

            Command command;
            try {
                command = new Command(new Scanner(line));
            } catch (InvalidActionException e) {
                printer.printStandardResponse(Printer.Response.CONFUSED);
                continue;
            }

            if (command.hasAction(Command.Action.BYE)) {
                break;
            } else if (command.hasAction(Command.Action.LIST)) {
                printer.printResponse(taskList.getTasksForPrint());
            } else {
                try {
                    if (command.hasAction(Command.Action.MARK_DONE) || command.hasAction(Command.Action.MARK_UNDONE)) {
                        printer.printResponse(taskList.setTaskDone(command));
                    } else if (command.hasAction(Command.Action.DELETE_TASK)) {
                        printer.printResponse(taskList.deleteTask(command));
                    } else if (command.hasAction(Command.Action.CREATE_TODO)) {
                        printer.printResponse(taskList.addTask(command.toToDoTask()));
                    } else if (command.hasAction(Command.Action.CREATE_DEADLINE)) {
                        printer.printResponse(taskList.addTask(command.toDeadlineTask()));
                    } else if (command.hasAction(Command.Action.CREATE_EVENT)) {
                        printer.printResponse(taskList.addTask(command.toEventTask()));
                    }
                } catch (JarvisException e) {
                    printer.printErrorResponse(e.getFriendlyMessage());
                }
            }
        }

        printer.printStandardResponse(Printer.Response.GOODBYE);
        scanner.close();
    }
}
