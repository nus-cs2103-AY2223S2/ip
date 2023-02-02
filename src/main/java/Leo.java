import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import leo.parser.Parser;
import leo.storage.Storage;
import leo.task.LeoTaskException;
import leo.task.TaskList;
import leo.ui.Ui;

/**
 * Main class for Leo.
 */
public class Leo {

    private Parser parser = new Parser();
    private TaskList taskList;
    private Ui ui = new Ui();
    public static void main(String[] args) throws LeoTaskException {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        new Leo().start();
    }

    /**
     * Starts the Leo program.
     * @throws LeoTaskException
     */
    public void start() throws LeoTaskException {
        //Get saved state
        ui.greetUser();
        String[] request = parser.parseRequest(); // reads in command fed by user
        readFile();

        if (taskList == null) {
            taskList = new TaskList();
        }
        
        while (!request[0].equals("bye")) {
            taskList.processRequest(request);
            request = parser.parseRequest();
        }
        
        Storage.writeObjectToFile(taskList);
        ui.printExit();
        parser.close();
    }

    /**
     * Reads the saved state of the task list from the file.
     */

    private void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream("taskList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            taskList = (TaskList) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException i) {
            taskList = new TaskList();
            Storage.writeObjectToFile(taskList);
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("TaskList class not found");
            c.printStackTrace();
            return;
        }
    }
}