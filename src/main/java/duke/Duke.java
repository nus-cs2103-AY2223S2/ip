package duke;// Tan Matthew Simon Castaneda

import duke.helper.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private String filepath;
    private Storage storage;
    private TaskList taskList;

    private Ui ui;

    public Duke(String filepath) {
        this.filepath = filepath;
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = storage.loadTask();
        } catch (Exception e) {
            System.out.println("honggan");
            System.out.println(e.getMessage());
        }
        ui.startUp();



    }

    public void run() {

        while(true) {
            String input = ui.readLine();
            String[] command = input.split(" ");

            if (input.equals("bye")) {
                this.storage.saveTask(this.taskList);
                break;
            } else {
                Parser.run(input, command, this.taskList);
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
