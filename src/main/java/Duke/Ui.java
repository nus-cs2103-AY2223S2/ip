package Duke;

public class Ui {

    public Ui() {
    }

    /**
     * Greet to the user when first run the program.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Say goodbye to the user when "bye" command detected.
     */
    public void bye() {
        System.out.println("\t--------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------");
    }
}
