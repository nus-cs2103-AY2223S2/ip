public class Ui {

    private static final String LINE =
            "\t____________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public void greet() {
        System.out.println("Hello from\n"
                + LOGO
                + "\nWhat can I do for you?");
    }

    public void printOutput(String text) {
        System.out.println(LINE);
        System.out.println("\t " + text);
        System.out.println(LINE);
    }

    public void printLine() {
        System.out.println(LINE);
    }
}
