package duke;
import java.io.*;
import java.util.*;

import duke.command.*;
/**
 * main duke class. helps abstract all the other components
 */
public class Duke {
    private static Ui ui; //deal with user interactionss
    private static TaskList taskList;
    private static Storage storage;

    public Duke() {
        String filePath = "./duke.txt";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    public String getResponse(String input) {
        String response = "";
        try {
            response = ui.parseIn(input, taskList, ui, storage);
            this.storage.save(taskList);
            assert response instanceof String : "Response not a String";
            return response;
        } catch (Exception e) {
            return e.toString();
        }

    }
    //just in case still want to run cli version
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.ui.printLogo();
        while (true) {
            System.out.print('\n');
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("bye")) {
                System.out.println("No don't go!!");
                break;
            }
            ui.line(in.length());
            try {
                Command c = Parser.parseIn(in);
                c.execute(taskList, ui, storage);
                duke.storage.save(taskList);
            } catch (DukeException e) {
                //empty catch, error should have been handled by parser or command
            }
            ui.line(in.length());
        }
    }
}
