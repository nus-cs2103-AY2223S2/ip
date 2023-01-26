package duke;

public class Ui {

    /**
     * Prints the introduction message when the chatbot is first booted up.
     */
    public void introMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message upon termination of the chatbot.
     */
    public void endMessage() {
        System.out.println("Bye. Hope to see you again!");
    }
}
