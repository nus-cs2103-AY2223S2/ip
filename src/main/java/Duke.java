import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);

    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    public Duke(String memoryPath) {
        ui = new Ui();
        storage = new Storage(memoryPath);
        try {
            allTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            allTasks = new TaskList();
        }
    }

    public void run() {
        //...
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private static void printLine() {
        System.out.println("----------------------------------------------------");
    }

    private static void printList() {
        if (allTasks.size() == 0) {
            System.out.println("You have zero tasks now!");
            return;
        }
        System.out.println("Your tasks so far!!");
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            String toPrint = String.format("%d. %s", i + 1, task.toString());
            System.out.println(toPrint);
        }
    }

    private static boolean handleCommands(String rawCommand) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command = rawCommand;
        String arguments = "";
        if (commandIndex != -1) {
            // There is a space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1).trim();
        }

        switch (command) {
        case "bye":
            System.out.println("Sad...Alright bye!");
            return false;
        case "list":
            printList();
            break;
        case "mark":
            try {
                int markIndex = Integer.parseInt(arguments) - 1;
                changeTaskCompletionStatus(markIndex, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.MARK);
            }
            break;
        case "unmark":
            try {
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                changeTaskCompletionStatus(unmarkIndex, false);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.UNMARK);
            }
            break;
        case "todo":
            if (arguments.trim().equals("")) {
                throw new IlegalCommandException(Commands.TODO);
            }
            addToList(arguments, TaskType.TODO, null, null, false, true);
            break;
        case "deadline":
            try {
                int slashIndex = arguments.indexOf('/');
                String dateByString = arguments.substring(slashIndex + 4);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateBy = LocalDateTime.parse(dateByString, dateFormat);
                addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE, null, dateBy, false, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DEADLINE);
            }
            break;
        case "event":
            try {
                int firstSlashIndex = arguments.indexOf('/');
                String startAndEnd = arguments.substring(firstSlashIndex + 6);
                int secondSlashIndex = startAndEnd.indexOf('/');
                String startString = startAndEnd.substring(0, secondSlashIndex - 1);
                String endString = startAndEnd.substring(secondSlashIndex + 4);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(startString, dateFormat);
                LocalDateTime end = LocalDateTime.parse(endString, dateFormat);
                // TODO: Check if start date is after end date
                addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT, start, end, false, true);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.EVENT);
            }
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(arguments) - 1;
                deleteTask(deleteIndex);
            } catch (Throwable e) {
                throw new IlegalCommandException(Commands.DELETE);
            }
            break;
        default:
            throw new IlegalCommandException(Commands.UNRECOGNIZED);
        }
        return true;
    }

    public static void main(String[] args) {
        loadTasks();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printLine();
        System.out.println(logo);
        System.out.println("Hope you are doing great!");
        System.out.println("What can I do for you?");
        printLine();

        boolean promptAgain = true;
        while (promptAgain) {
            System.out.println("Enter your prompt below:");
            String command = sc.nextLine();
            try {
                promptAgain = handleCommands(command);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            printLine();
        }

        saveTasks();
    }
}
