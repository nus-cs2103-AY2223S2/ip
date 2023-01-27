package duke;

import java.io.IOException;

/**
 * This class is for the starting screen and the loop the commands in Ui class
 * 
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng 
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        boolean hasEnded;
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.loadFile());
        Parser parser = new Parser();
        Ui ui = new Ui(parser);

        while(true) {
            hasEnded = ui.receiveInput(tasks, storage);
            if(hasEnded){
                break;
            }
        }
    }
}
