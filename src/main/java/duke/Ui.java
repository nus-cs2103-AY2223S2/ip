package duke;

/**
 * The Ui class is to deal with interactions with users.
 */
public class Ui {
    /**
     * Default display.
     */

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        System.out.println("Please follow the date format: yyyy-MM-dd hh:mm (e.g. 2019-10-15 18:00), "
                + "otherwise your input will be invalid.");
        printDashes();
    }

    /**
     * Prints dash line as divider.
     */
    public void printDashes() {
        System.out.println("****************************************");
    }

    /**
     * Prints a line.
     *
     * @param s Thing to be printed.
     */
    public void println(String s) {
        System.out.println(s);
    }
}
