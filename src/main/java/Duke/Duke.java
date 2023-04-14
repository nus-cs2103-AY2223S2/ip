package Duke;

import Duke.Exceptions.EmptyDescriptionException;
import Duke.Tasks.TaskList;
import java.io.IOException;
import java.lang.String;

/**
 * Contains the Duke object
 */

public class Duke  {
    private  Storage storage;
    private  TaskList list;
    private Ui ui;

    private static String FILE = "database/data.txt";






    /**
     * Constructor for Duke.
     *
     */
    public Duke(){
        this.storage = new Storage(FILE);
        this.ui = new Ui();
        this.list = new TaskList(storage.readnWriteData());
    }

    /**
     * Executes the program by greeting user and response appropriately depending
     * on the users input
     */
    public void run() {
        this.ui.greet();
        while (Parser.getFlag()){
                String input = ui.readIn();
                System.out.println(getResponse(input));
        }
    }
    public String getResponse(String input) {
        String response = "";
        try {
            response = Parser.parse(input, this.list);
            storage.saveData(list);
       } catch (IOException  | EmptyDescriptionException e) {
            return String.format("%s\n", e);
       }
       return response;
    }

    /**
     * Creates duke object and invokes run() method on it
     * @param args
     */
    public static void main(String[] args){
        new Duke().run();
    }


}

