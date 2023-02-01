package Duke;

import Duke.Tasks.*;
import java.io.IOException;
import java.lang.String;
/**
 * Contains the Duke object
 */

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param file the existing txt database file  the new txt database file to be saved/created
     */
    public Duke(String file){
        this.storage = new Storage(file);
        this.ui = new Ui();
        list = new TaskList(storage.readnWriteData());
    }

    /**
     * The method of run
     */
    public void run() {
        ui.greet();
        int flag = 0;
        while (flag != -1){
            try{
                String input = ui.readIn();
                flag = Parser.parse(input, list);
                storage.saveData(list);


            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args){
        new Duke("database/data.txt").run();
    }


}

