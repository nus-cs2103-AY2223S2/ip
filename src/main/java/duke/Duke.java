package duke;

import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";
    private final Parser parser;
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList);
    }
    public void run() {
        ui.greet();
        parser.getTaskType();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }
}


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


