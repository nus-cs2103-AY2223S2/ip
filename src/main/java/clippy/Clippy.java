package clippy;

import clippy.command.Command;
import clippy.command.Parser;
import clippy.exception.ClippyException;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class Clippy {
    private static final String logo = "   _____  _  _                      \n" +
            "  / ____|| |(_)                     \n" +
            " | |     | | _  _ __   _ __   _   _ \n" +
            " | |     | || || '_ \\ | '_ \\ | | | |\n" +
            " | |____ | || || |_) || |_) || |_| |\n" +
            "  \\_____||_||_|| .__/ | .__/  \\__, |\n" +
            "               | |    | |      __/ |\n" +
            "               |_|    |_|     |___/ ";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Clippy() {
        this.ui = new Ui(">>>", "###");
        this.storage = new Storage(this.ui);
        this.taskList = new TaskList(storage.loadState());
    }

    public void run() {
        boolean shouldContinue = true;
        ui.prettyPrint("Hello! I'm Clippy, your lightweight personal assistant.");
        ui.prettyPrint("What can I do for you today?");

        while (shouldContinue) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(ui, taskList, storage);
                shouldContinue = c.shouldContinue();
            } catch (ClippyException e) {
                ui.systemPrint(e.toString());
            } catch (Exception e) {
                ui.systemPrint("System error: " + e.toString());
                shouldContinue = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        new Clippy().run();
    }
}