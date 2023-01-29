package duke;

import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class Duke {
    private final Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList);
    }
    public void run() {
        ui.greet();
        parser.getTaskType();
        storage.save(taskList);
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


