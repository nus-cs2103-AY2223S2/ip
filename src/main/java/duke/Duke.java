package duke;
import java.io.IOException;
public class Duke {

    public static final String DIRECTORY_PATH = "data";
    public static final String FILE_PATH = "data/duke.txt";
    Storage storage;
    TaskList<Task> taskList;

    Parser parser;
    Ui ui;

    public Duke(String filePath, String directoryPath) {
        this.storage = new Storage(filePath, directoryPath);
        try {
            this.taskList = storage.readFile();
        } catch (NeroException e) {
            this.taskList = new TaskList<Task>();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public TaskList<Task> getTaskList() {
        return taskList;
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean toEnd = false;
        while (!toEnd) {
            String originalString = ui.readLine();
            try {
                toEnd = parser.parseCommand(originalString, taskList, ui);
                storage.saveFile(taskList);
            } catch (IOException e) {
                ui.printFileNotFound();
            } catch (NeroException ne) {
                ne.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(FILE_PATH, DIRECTORY_PATH).run();
    }
}

