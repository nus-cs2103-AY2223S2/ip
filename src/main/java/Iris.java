import java.util.Scanner;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Iris {
    private TaskList tasks = null;
    private String input;
    private TaskStore taskStore;
    private Ui ui;

    public Iris() {
        this.ui = new Ui();
        this.taskStore = new TaskStore();
        try {
            this.tasks = this.taskStore.parse();
        } catch (IrisException e) {
            Ui.output("Error while parsing stores file: " + e.getMessage() + "\nResetting the task list.");
            if (this.tasks == null) {
                this.tasks = new TaskList();
                this.taskStore.reset();
            }
        }
    }

    private void run() {
        ui.greet();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                input = this.ui.readInput();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.taskStore);
                isEnd = command.isEnd();
            } catch (IrisException e) {
                Ui.output(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Iris().run();
    }
}
