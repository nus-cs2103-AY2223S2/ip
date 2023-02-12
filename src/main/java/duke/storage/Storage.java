package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.TaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;


/**
 * Stores instructions and data in this class
 */
public class Storage {
    private final String filePath;
    private Ui ui;
    private ArrayList<Task> list = new ArrayList<Task>(100);

    /**
     * Initialises to load the file when system begins
     *
     * @param filePath pathname of the file of type String
     * @param ui       respective ui class
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Prints list items according to list index
     *
     * @return print display list
     */
    public String displayList() {
        String printList = "";
        for (int i = 0; i < list.size(); i++) {
            printList = printList + "\n" + (i + 1 + "." + list.get(i));
        }
        return ui.listMessage(printList);
    }

    /**
     * Marks the list item using index
     *
     * @param index the list index according to the list array
     */
    public void markListItem(int index) {
        list.get(index).toBeMarked();
    }

    /**
     * Unamrks the list item using index
     *
     * @param index the list index according to the list array
     */
    public void unmarkListItem(int index) {
        list.get(index).toBeUnmarked();
    }

    /**
     * Adds to do item to its class
     *
     * @param input user input of type String
     */
    public void addTodoItem(String input) {
        list.add(new Todo(input.substring(5, input.length())));
        this.ui.listInfo(list.size());
    }

    /**
     * Adds deadline item to its class
     *
     * @param first_word  reads the name of the task
     * @param second_word reads the date of completion of the task
     */
    public void addDeadlineItem(String first_word, String second_word) {
        list.add(new Deadline(first_word, second_word));
    }

    /**
     * Adds events item to its class
     *
     * @param name      reads the name of the task
     * @param startTime reads the start date and time for the tasks
     * @param endTime   reads the end date and time for the tasks
     */
    public void addEventItem(String name, String startTime, String endTime) {
        list.add(new Event(name, startTime, endTime));
    }

    /**
     * Deletes item from the list using its index
     *
     * @param index the list index according to the list array
     */
    public void deleteListItem(int index) {
        Task temp = list.get(index);
        list.remove(index);
        System.out.println("The Duke has removed this task: " + temp);
        this.ui.listInfo(list.size());
    }

    /**
     * Finds word that contains in existing tasks
     *
     * @param input to be entered by the user
     * @return print find list
     */
    public String findListItem(String input) {
        String itemNumber = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(input)) {
                itemNumber += ((i + 1) + ". " + list.get(i)) + "\n";
            }
        }
        return "Following are the results found from searching " + input + ": \n" + itemNumber;
    }

    /**
     * Updates item in the list of the same task type
     *
     * @param index location of the item is stored in the list
     * @param input string contains item information
     * @return alert message about updated item
     * @throws TaskException return a exception with a custom message
     */
    public String updateItem(int index, String input) throws TaskException {
        Task temp = list.get(index);
        temp.updateTask(input);
        return "The selected item has been updated";
    }

    //Code extracted from https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java

    /**
     * Loads data from duke.txt
     */
    public void loadFileData() {
        try {
            File file = new File("./data");
            if (file.exists()) {
                File txtFile = new File(filePath);
                FileReader fileReader = new FileReader(txtFile);
                readToFile(fileReader);
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from duke.txt
     *
     * @param file reads the contents in file
     */
    public void readToFile(FileReader file) {
        try {
            BufferedReader reader = new BufferedReader(file);
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates list to duke.txt
     */
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task t : list) {
                writer.write(t.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
