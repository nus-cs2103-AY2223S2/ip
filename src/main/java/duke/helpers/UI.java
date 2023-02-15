package duke.helpers;

public class UI {
    private static final String LINE = "____________________________________________________________\n";
    private boolean terminated;
    private Parser parser;
    private TaskList taskList;

    public UI(TaskList taskList) {
        this.terminated = false;
        this.parser = new Parser(taskList);
        this.taskList = taskList;
    }

    public void process(String instr) {
        boolean isBye = parser.parse(instr.toLowerCase());
        if (isBye) {
            this.terminated = true;
        }
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public void greeting() {
        String msg = "Hello! I'm\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        if (this.taskList.isEmpty()) {
            msg += "You have no previously saved tasks.\n";
        } else {
            msg += "Your previously saved tasks have been successfully loaded!\n";
        }

        msg += "How can I help you today?\n";

        printWithLines(msg);
    }

    public static void printWithLines(String msg) {
        System.out.println(LINE + msg + LINE);
    }
}
