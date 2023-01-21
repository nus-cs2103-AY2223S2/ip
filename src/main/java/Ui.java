/**
 * The Ui class represents Duke's user interface that interacts with the user.
 */
public class Ui {
    /**
     * The Commands enum represents Duke's available commands.
     */
    public enum Commands {
        ECHO, LIST, PRIORITY, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;

    }

    private static Ui instanceUi = new Ui();

    private Ui() {}

    public static Ui getInstance() {
        return instanceUi;
    }

    /**
     * Prints Duke's greetings.
     */
    public void greet() {
        // @formatter:off
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        // @formatter:on

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------\n");
    }

    /**
     * Prints the partitions, ----, then prints the string in-between. \n is required for the end of the
     * string. <blockquote> ---------------------
     * <p>
     * your string here
     * <p>
     * --------------------- </blockquote>
     *
     * @param s The string in between the ---- partitions.
     */
    public void printWithPartition(String s) {
        System.out.println("---------------------");
        System.out.print(s);
        System.out.println("---------------------");
    }

}
