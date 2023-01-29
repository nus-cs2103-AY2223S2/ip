package duke.task;

import duke.command.*;

public class Parser {
    static String[] arr;

    public static String[] splitCommand(String command) {
        if (command.contains("/")) {
            String[] temp2;
            String[] temp = command.split(" ", 2);
            if (temp[0].equals("deadline")) {
                temp2 = temp[1].split("/", 2);
            } else {
                temp2 = temp[1].split("/");
            }
            temp2[1] = temp2[1].replace("/", "-");
            arr = new String[temp.length + temp2.length - 1];
            arr[0] = temp[0];
            System.arraycopy(temp2, 0, arr, temp.length - 1, temp2.length);
        } else {
            arr = command.split(" ", 2);
        }
        return arr;
    }

    public static Command parse(String command) {
        Command parsedCommand = null;
        String[] arr = splitCommand(command);
        String commandType = arr[0];

        switch (commandType) {
            case "bye":
                parsedCommand = new ExitCommand();
                break;
            case "delete":
                int taskNum = Integer.parseInt(arr[1]);
                parsedCommand = new DeleteCommand(taskNum);
                break;
            case "todo":
                try {
                    parsedCommand = new AddCommand(arr[0], arr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("task cannot be empty!");
                } finally {
                    break;
                }
            case "deadline":
                parsedCommand = new AddCommand(arr[0], arr[1], arr[2]);
                break;
            case "event":
                parsedCommand = new AddCommand(arr[0], arr[1], arr[2], arr[3]);
                break;
            case "unmark":
                int taskNumber = Integer.parseInt(arr[1]);
                parsedCommand = new UnmarkCommand(taskNumber);
                break;
            case "mark":
                int getTaskNum = Integer.parseInt(arr[1]);
                parsedCommand = new MarkCommand(getTaskNum);
                break;
            case "list":
                parsedCommand = new ListCommand();
                break;
            default:
                System.out.println("No such command!");
        }
        return parsedCommand;
    }
}
