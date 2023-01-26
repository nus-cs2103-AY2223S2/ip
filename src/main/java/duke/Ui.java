package duke;

public class Ui {

    private static final String AUTHOR = "lhy-hoyin";
    private static final String LOGO
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public Ui() {
        // Empty
    }

    void display(Object obj) {
        System.out.println(obj);
    }
    public void println(String message) {
        System.out.println(message);
    }
    public void warn(String message) {
        System.out.println("OOPS! " + message);
    }

    static void printProgramInfo() {
        System.out.println(LOGO);
        System.out.println("Developed by: " + AUTHOR);
    }
    void displayLine() {
        println("____________________________________________________________");
    }
}
