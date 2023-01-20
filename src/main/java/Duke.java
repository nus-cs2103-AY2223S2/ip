public class Duke {

    private static final String LINE = "\t____________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private Tasklist tasklist;
    private boolean isActive;

    public Duke() {
        this.tasklist = new Tasklist();
        this.isActive = true;
        this.greet();
    }

    public void greet() {
        System.out.println("Hello from\n" + LOGO + "\nWhat can I do for you?");
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void parseInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            this.isActive = false;
            this.printOutput("\t Bye. Hope to see you again soon!");
        } else {
            this.tasklist.addTask(input);
            this.printOutput("\t added: " + input);
        }
    }

    private void printOutput(String text) {
        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);
    }
}
