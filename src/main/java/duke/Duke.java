package duke;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(String.format("Hello from\n%s\nWhat can I do for you?", logo));

    }

    private static void printMessage(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n\n", message.replace("\n", "\n     "));
        System.out.print("    ____________________________________________________________\n\n");
    }
}
