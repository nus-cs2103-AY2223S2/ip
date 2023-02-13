import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeParseException;



public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    Parser.parse(input,tasks);
                    break;
                }
                Parser.parse(input, tasks);
                storage.save(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        new Duke("./data/duke.txt").run();

    }

}

