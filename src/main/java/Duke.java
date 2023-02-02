import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class DukeException extends Exception {

    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() {
        boolean isRunning = true;
        //listReader();
        while (isRunning) {
            try {
                isRunning = Parser.talk();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }



}
