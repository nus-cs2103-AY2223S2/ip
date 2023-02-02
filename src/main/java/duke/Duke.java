package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    enum Type {
        TODO, DEADLINE, EVENT
    }

    private static TaskList tasks;
    private static Ui ui;
    private static final String FILEPATH = "duke.txt";
    private static Storage storage;
    public static void main(String[] args) {
        initDuke();
    }

    public static void initDuke() {
        ui.displayLogo();
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.loadFile());
            
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        System.out.println(tasks);
        boolean startDuke = true;
        Scanner sc = new Scanner(System.in);
        while (startDuke) {
            try {
                String command = sc.nextLine();
                ui.displayLine();
                Command c = Parser.parse(command);
                c.initCommand(tasks, ui, storage);
                startDuke = c.isTerminated();
            }
            catch (DukeException e) {
                System.out.println(e);
            }
            finally {
                ui.displayLine();
            }
        }
    }
}
