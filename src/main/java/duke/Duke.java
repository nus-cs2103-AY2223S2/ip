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
    private Ui ui;
    private static final String FILEPATH = "duke.txt";
    private Storage storage;

    public Duke() {
        ui = new Ui();
        ui.displayLogo();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.loadFile());
            
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        System.out.println(tasks);
    }

    public static void main(String[] args) {
        new Duke().initDuke();
    }



    public void initDuke() {
        boolean isTerminated = false;
        Scanner sc = new Scanner(System.in);
        while (!isTerminated) {
            try {
                String command = sc.nextLine();
                ui.displayLine();
                Command c = Parser.parse(command);
                c.initCommand(tasks, ui, storage);
                isTerminated = c.isTerminated();
            }
            catch (DukeException e) {
                System.out.println(e);
            } 
            catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e);
            }
            finally {
                ui.displayLine();
            }
        }
        sc.close();
    }
}
