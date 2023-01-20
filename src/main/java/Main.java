import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Duke duke = new Duke();

        // greeting
        duke.print_structured_string(duke.greeting());

        // read input
        Scanner scanner = new Scanner(System.in);

        // main loop
        String inMsg = null;
        while (true) {
            inMsg = scanner.nextLine();
            if (duke.isEnd(inMsg)) {
                break;
            }

            try {
                if (checkCommand(inMsg,Command.LIST)) {
                    duke.print_structured_string(duke.listTasksMsg());
                } else if (checkCommand(inMsg,Command.MARK)) {
                    int idx = Integer.parseInt(inMsg.substring(5)) - 1;
                    duke.print_structured_string(duke.markTaskDone(idx));
                } else if (checkCommand(inMsg,Command.UNMARK)) {
                    int idx = Integer.parseInt(inMsg.substring(7)) - 1;
                    duke.print_structured_string(duke.unmarkTaskDone(idx));
                } else if (checkCommand(inMsg, Command.TODO)){
                    String todoName = getCommandContent(inMsg, Command.TODO);
                    ToDo todo = new ToDo(todoName);
                    duke.print_structured_string(duke.addTask(todo));
                } else if (checkCommand(inMsg, Command.DEADLINE)) {
                    String deadlineContent = getCommandContent(inMsg, Command.DEADLINE);
                    Deadline ddl = new Deadline(deadlineContent);
                    duke.print_structured_string(duke.addTask(ddl));
                } else if (checkCommand(inMsg, Command.EVENT)) {
                    String eventContent = getCommandContent(inMsg, Command.EVENT);
                    Event event = new Event(eventContent);
                    duke.print_structured_string(duke.addTask(event));
                } else if (checkCommand(inMsg, Command.DELETE)) {
                    String indexToDelete = getCommandContent(inMsg, Command.DELETE);
                    duke.print_structured_string(duke.deleteTask(Integer.parseInt(indexToDelete)));
                } else {
                    throw new DukeException("  OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                duke.print_structured_string(e.toString());
            } catch (NumberFormatException e) {
                duke.print_structured_string("Please enter a number.");
            }
        }

        // bye-bye message
        duke.print_structured_string(duke.endMsg());
    }

    
    public static boolean checkCommand(String s, Command c) {
        boolean isCommand = false;
        switch (c) {
            case LIST:
                isCommand = s.equalsIgnoreCase("list");
                break;
            case MARK:
            case UNMARK:
            case TODO:
            case DEADLINE:
            case EVENT:
            case DELETE:
                isCommand = s.toUpperCase().startsWith(c.name());
                break;
        }
        return isCommand;
    }

    public static String getCommandContent(String s, Command c) throws DukeException {
        String commandString = c.name().toLowerCase();
        if ((!commandString.equals("list")) && s.length() <= commandString.length() + 1) {
            throw new DukeException("The command argument is not complete.");
        }
        return s.substring(s.indexOf(commandString) + commandString.length() + " ".length());
    }
}
