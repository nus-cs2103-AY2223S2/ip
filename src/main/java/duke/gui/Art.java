package duke.gui;

public class Art {
    /**
     * Represents the ASCII artwork on the start of the Duke program
     */
    String logo;

    /**
     * Constructor for an instance of Art
     */
    public Art() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Method to display ASCII artwork
     */
    public void show() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }
}
