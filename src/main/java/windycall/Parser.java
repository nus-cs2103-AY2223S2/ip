package windycall;

/**
 * Deals with making sense of the user command by splitting
 * user command and translation
 */
public class Parser {

    public Parser() {

    }

    /**
     * Returns an operation type representing what users want to do
     *
     * @param command command input by users
     * @return a enumeration type representing a specific operation
     */
    public OperationType getOperationType(String command) {
        if (command.equals("list")) return OperationType.LIST;
        String[] parts = command.split(" ");
        switch (parts[0]) {
        case "mark":
            return OperationType.MARK;
            //break;
        case "unmark":
            return OperationType.UNMARK;
            //break;
        case "delete":
            return OperationType.DELETE;
            //break;
        default:
            return OperationType.ADDTASK;
            //break;
        }
    }

    /**
     * Returns the translated index of mark operation while
     * handling invalid input
     *
     * @param parts parts of String of user command split by space
     * @return an index representing which task user want to mark
     */
    public int getMarkIndex(String[] parts) {
        if (parts.length == 1) {
            System.out.println("     You should input a number to mark/unmark a task");
            return -1;
        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            Ui.space();
            System.out.println("☹ OOPS!!! You should input a number");
        }
        return num;
    }

    public int getUnmarkIndex(String[] parts) {
        if (parts.length == 1) {
            System.out.println("     You should input a number to mark/unmark a task");
            return -1;
        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            Ui.space();
            System.out.println("☹ OOPS!!! You should input a number");
        }
        return num;
    }

    public int getDeleteIndex(String[] parts) {
        if (parts.length == 1) {
            System.out.println("     You should input a number to delete a task");
            return -1;
        }
        int num = -1;
        try {
            num = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            Ui.space();
            System.out.println("☹ OOPS!!! You should input a number");
        }
        return num;
    }

    /*
    public String getTodoTaskInformation(String command) {
        return "";
    }

    public String[] getDeadlineInformation(String command) {
    }

    public String getEventInformation(String command) {

    }
     */

}
