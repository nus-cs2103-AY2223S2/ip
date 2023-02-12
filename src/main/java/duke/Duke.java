package duke;
import duke.task.*;
import duke.command.*;
import java.io.*;
import java.util.*;
public class Duke {
    private static Ui ui; //deal with user interaction
    private static TaskList taskList;
    private static Storage storage;
    private static ArrayList<Task> list;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try { 
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke("./duke.txt");
    //CHECKSTYLE.OFF: checkStyleTest 
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    //CHECKSTYLE.ON: checkStyleTest
        System.out.println("Hello from\n" + logo);
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
