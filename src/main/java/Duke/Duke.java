package Duke;

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
     * The method of run
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
       }
       catch (IOException e){
           e.printStackTrace();
       }
       return response;
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args){
        new Duke().run();
    }


}

