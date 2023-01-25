import java.util.Scanner;

public class Duke {

    private static Scanner scanner;
    private static DukeList taskList;
    public static void initialiseDuke() {
        try {
            scanner = new Scanner(System.in);
            System.out.println(DukeIO.LOGO);
            System.out.println(DukeIO.wrapContent(DukeIO.GREET));
            taskList = new DukeList();
        } catch (DukeException e) {
            System.out.println(DukeIO.wrapContent(e.getMessage()));
        }

    }
    static void waitForInput() {
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.startsWith(DukeIO.MARK_COMMAND)) {
                    taskList.markTask(input);
                    continue;
                }
                if (input.startsWith(DukeIO.UNMARK_COMMAND)) {
                    taskList.unmarkTask(input);
                    continue;
                }
                if (input.startsWith(DukeIO.DELETE_COMMAND)) {
                    taskList.deleteTask(input);
                    continue;
                }
                if (input.equals(DukeIO.FAREWELL_COMMAND)) {
                    System.out.println(DukeIO.wrapContent(DukeIO.FAREWELL));
                    return;
                }
                if (input.equals(DukeIO.LIST_COMMAND)) {
                    System.out.println(DukeIO.wrapContent(taskList.toString()));
                    continue;
                }
                if (Task.isCreateTaskCommand(input)) {
                    taskList.addTask(input);
                    continue;
                }
                throw new DukeException(DukeIO.INVALID_COMMAND_EXCEPTION_MESSAGE);
            } catch (DukeException e) {
                System.out.println(DukeIO.wrapContent(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        initialiseDuke();
        waitForInput();
    }
}
