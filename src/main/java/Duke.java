// Tan Matthew Simon Castaneda
import java.util.*;
import java.io.*;
import java.nio.file.*;
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
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
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
                CommandManager.run(input, command, this.taskList);
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
