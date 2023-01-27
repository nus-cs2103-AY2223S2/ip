import java.util.Scanner;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Iris {
    private final TaskList tasks;
    private String input;
    private TaskStore taskStore;
    private Ui ui;

    public Iris() {
        this.taskStore = new TaskStore();
        this.tasks = this.taskStore.parse();
        this.ui = new Ui();
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
                Ui.output(e.message);
            }
        }
    }

    public static void main(String[] args) {
        new Iris().run();
    }
}
