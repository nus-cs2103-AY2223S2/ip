package genie.main;

import genie.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks in the specified file. Loads every line of task on the file and stores it in
 * the current task list available for viewing upon using the "list" command.
 */
public class Storage {
    private static final char TODO = 'T';
    private static final char EVENT = 'E';
    private static final char DEADLINE = 'D';
    private static final String PATH = "./data/genie.txt";
    private static FileWriter fw;
    private ArrayList<String> loadedTasks;

    /**
     * A constructor for Storage class. Creates an <code>ArrayList&lt;String&gt</code> to store all the read tasks
     * from file.
     */
    public Storage() {
        this.loadedTasks = new ArrayList<>();
    }

    /**
     * Loads data from the specified file. Creates the necessary directory and file in a specified location if it is
     * unavailable on the user's local folder.
     * @return <code>TaskList</code> containing an array of Tasks read from the file
     * @throws IOException if an error occurs while loading the data
     */
    // @@author mandykqh-reused
    // Reused from https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create
    // -the-files-in-that-direct
    // with minor modifications
    public TaskList loadData() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()){
            directory.mkdir();
        }
        File file = new File(PATH);
        if (file.createNewFile()) {
            return new TaskList();
        } else {
            return readTextFileToList(file);
        }
    }
    //@@author
    /**
     * Reads contents of the loaded .txt file into a <code>TaskList</code>.
     * @param f loaded file
     * @return <code>TaskList</code>
     * @throws IOException if an error occurs in reading the file
     */

    public TaskList readTextFileToList(File f) throws IOException {
        Scanner fileString = new Scanner(f);
        TaskList tasks = new TaskList();
        while (fileString.hasNext()) {
            String strTask = fileString.nextLine();
            char taskLetter = strTask.charAt(1);
            loadedTasks.add(strTask);
            switch (taskLetter) {
            case TODO:
                tasks.addToDoFromFile(strTask);
                break;
            case EVENT:
                tasks.addEventFromFile(strTask);
                break;
            case DEADLINE:
                tasks.addDeadlineFromFile(strTask);
                break;
            }
        }
        return tasks;
    }

    /**
     * Closes <code>FileWriter</code>.
     * @throws IOException if an error occurs in closing <code>FileWriter</code>
     */
    public void closeFileWriter() throws IOException {
        fw.close();
    }

    /**
     * Saves <code>TaskList</code> to the specified .txt file
     * @param list task list
     * @throws IOException if an error occurs in writing task into the file
     */
    public void saveListToFile(ArrayList<Task> list) throws IOException {
            fw = new FileWriter(PATH);
            for(Task t : list) {
                String formatted = t.toFileFormat();
                fw.write(formatted);
                fw.write("\n");
            }
    }

    /**
     * Gets <code>TaskList</code> from the loaded data.
     * @return task list in <code>ArrayList&lt;String&gt;</code>
     */
    public ArrayList<String> getLoadedTaskList() {
        return loadedTasks;
    }

    public boolean isEmpty() {
        return loadedTasks.size() == 0;
    }
}
