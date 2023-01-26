import java.util.Scanner;
import java.io.IOException;
import java.time.DateTimeException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke
{
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadRecord());
            tasks.printList();
            ui.printDashes();
        } catch (IOException e) {
            System.out.println("Error occurs when try to load.");
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                if (Parser.parseInput(tasks, sc.nextLine().trim())) {
                    storage.writeToFile(tasks);
                }
            } catch (DukeException e) {
                ui.println(e.eMessage);
            } catch (NumberFormatException e) {
                ui.println("The operation must follow by a integer");
            } catch (IOException e) {
                ui.println("Error occurs when try to access your file");
            } catch (DateTimeException e) {
                ui.println("Invalid date time format, please try again!");
            }
            ui.printDashes();
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("./userRecords/records.txt");
        duke.run();
    }
}
