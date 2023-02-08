package duke;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    private static TaskList tasks;
    private Ui ui;
    private static final String FILEPATH = "duke.txt";
    private Storage storage;
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.loadFile());
            
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        ui.displayTaskList(tasks);
    }


//    public void initDuke() {
//        boolean isTerminated = false;
//        Scanner sc = new Scanner(System.in);
//        while (!isTerminated) {
//            try {
//                String command = sc.nextLine();
//                ui.displayLine();
//                Command c = Parser.parse(command);
//                c.initCommand(tasks, ui, storage);
//                isTerminated = c.isTerminated();
//            }
//            catch (DukeException e) {
//                System.out.println(e);
//            }
//            catch (IllegalArgumentException e) {
//                System.out.println("ERROR: " + e);
//            }
//            finally {
//                ui.displayLine();
//            }
//        }
//        sc.close();
//    }

    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            return c.initCommand(tasks, ui, storage);
        }
        catch (DukeException e) {
            return e.getMessage();
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
