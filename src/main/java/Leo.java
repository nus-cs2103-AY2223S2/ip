import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

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
    private Ui ui;

    public Leo() {
        ui = new Ui();
        readFile();
    }


    public String getResponse(String input) throws LeoTaskException {
        assert !input.equals("") : "Input should not be empty\n";
        String[] request = parser.parseRequest(input);
        String response = "";
        if (taskList == null) {
            taskList = new TaskList();
        }
        response = taskList.processRequestGUI(request);
        if (response.equals("It was nice talking, see you soon!\n")) {
            Storage.writeObjectToFile(taskList);
            parser.close();
        }
        assert !response.equals("") : "Response should not be empty\n";
        return response;
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

    /**
     * Exits Leo.
     */
    public void exit() {
        Storage.writeObjectToFile(taskList);
        parser.close();
    }
}