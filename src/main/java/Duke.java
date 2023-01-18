import java.util.Scanner;

public class Duke {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private static Enum getCommand(String userInput) throws DukeException {
        String strCommand = userInput.split(" ", 2)[0];
        try {
            Enum command = Commands.valueOf(strCommand.toUpperCase());
            return command;
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command");
        } catch (NullPointerException e) {
            throw new DukeException("No command given");
        }
    }

    private static int parseMarkOrDeleteCommands(String userInput) throws DukeException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new DukeException("Mark / Unmark / Delete commands require an integer argument referring to task number");
        } else {
            try {
                int taskNumber = Integer.parseInt(splitStr[1]);
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new DukeException("Format of argument cannot be parsed as an integer");
            }
        }
    }

    private static String parseTodoCommand(String userInput) throws DukeException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new DukeException("Todo command requires a task description");
        } else {
            return splitStr[1];
        }
    }

    private static String[] parseDeadlineCommand(String userInput) throws DukeException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new DukeException("Deadline command requires task description and /by argument");
        } else {
            String[] output = splitStr[1].split(" /by ", 2);
            if (output.length < 2) {
                throw new DukeException("Deadline command requires task description and /by argument");
            } else {
                return output;
            }
        }
    }

    private static String[] parseEventCommand(String userInput) throws DukeException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new DukeException("Event command requires task description, /from argument and /to argument");
        } else {
            String[] splitFrom = splitStr[1].split(" /from ", 2);
            if (splitFrom.length < 2) {
                throw new DukeException("Event command requires task description, /from argument and /to argument");
            } else {
                String[] splitTo = splitFrom[1].split(" /to ", 2);
                if (splitTo.length < 2) {
                    throw new DukeException("Event command requires task description, /from argument and /to argument");
                } else {
                    return new String[] {splitFrom[0], splitTo[0], splitTo[1]};  // [0] is description of task; [1] is from; [2] is to
                }
            }
        }
    }


    public static void main(String[] args) {
        Printer p = new Printer();
        String text;
        Enum command;
        Scanner s = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean flag = true;

        p.printWelcome();

        while (flag) {
            text = s.nextLine();
            try {
                command = Duke.getCommand(text); // will catch any invalid command alrdy
                if (command.equals(Commands.BYE)) {
                    p.printExit();
                    flag = false;
                } else if (command.equals(Commands.LIST)) {
                    p.print(taskList.listTasks());
                } else if (command.equals(Commands.MARK)) {
                    p.print(taskList.markTask(parseMarkOrDeleteCommands(text)));
                } else if (command.equals(Commands.UNMARK)) {
                    p.print(taskList.unmarkTask(parseMarkOrDeleteCommands(text)));
                } else if (command.equals(Commands.DELETE)) {
                    p.print(taskList.deleteTask(parseMarkOrDeleteCommands(text)));
                } else {
                    if (command.equals(Commands.TODO)) {
                        p.print(taskList.addTask(parseTodoCommand(text)));
                    } else if (command.equals(Commands.DEADLINE)) {
                        String[] parsed = parseDeadlineCommand(text); // parsed[0] is description of task; parsed[1] is by
                        p.print(taskList.addTask(parsed[0], parsed[1]));
                    } else if (command.equals(Commands.EVENT)) {
                        String[] parsed = parseEventCommand(text); // parsed[0] is description of task; parsed[1] is from; parsed[2] is to
                        p.print(taskList.addTask(parsed[0], parsed[1], parsed[2]));
                    } else {
                        p.print("MISSED AN EXCEPTION: Somehow ended up at the end of the if-else blocks"); //should be unnecessary
                    }
                }
            } catch (DukeException e) {
                p.print(e.getMessage());
            }
        }
    }
}
