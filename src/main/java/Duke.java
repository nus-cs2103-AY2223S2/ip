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
        String[] delimited = input.split(" ");
        int index;
        switch (delimited[0].toLowerCase()) {
            case "bye":
                this.isActive = false;
                this.printOutput("\t Bye. Hope to see you again soon!");
                break;
            case "list":
                System.out.println(LINE);
                this.tasklist.viewList();
                System.out.println(LINE);
                break;
            case "mark":
                index = Integer.parseInt(delimited[1]) - 1;
                if (this.tasklist.mark(index)) {
                    printOutput("\t I've marked this as done:\n \t " + this.tasklist.get(index));
                } else {
                    printOutput("\t The selected task has already been marked as done.");
                }
                break;
            case "unmark":
                index = Integer.parseInt(delimited[1]) - 1;
                if (this.tasklist.unmark(index)) {
                    printOutput("\t I've marked this as not done yet:\n \t " + this.tasklist.get(index));
                } else {
                    printOutput("\t The selected task has not yet been marked as done.");
                }
                break;
            default:
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
