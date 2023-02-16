package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class to store and load the ArrayList contents into a txt file and vice-versa
 */
public class Storage {
    private File file;

    /**
     * @param filePath the place where the txt file is stored
     * @throws IOException If the file cannot be found
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    /**
     * Save the data from the arraylist into the txt file created/stored
     * @param tasks an arraylist of all the tasks
     */
    public void saveData(ArrayList<Task> tasks) throws IOException {
        File userData = new File("userData");
        if (!userData.exists()) {
            userData.mkdir();
        }
        File dukeTxt = new File(userData, "duke.txt");
        if (!dukeTxt.exists()) {
            dukeTxt.createNewFile();
        } else {
            dukeTxt.delete();
            dukeTxt.createNewFile();
        }
        if (tasks.isEmpty()) {
            return;
        }
        FileWriter fw = new FileWriter(dukeTxt);
        BufferedWriter dukeWrite = new BufferedWriter(fw);

        for (int i = 0; i < tasks.size(); i++) {
            dukeWrite.write(tasks.get(i).toString());
            dukeWrite.newLine();
        }
        dukeWrite.close();
    }

    /**
     * A method to laod the data from the txt file to the arraylist
     * @param tasks an arraylist of all the tasks
     */
    public void loadData(ArrayList<Task> tasks) throws IOException {
        File userData = new File("userData");
        if (!userData.exists()) {
            userData.mkdir();
        }
        File dukeTxt = new File(userData, "duke.txt");
        if (dukeTxt.exists()) {
            FileReader fw = new FileReader(dukeTxt);
            BufferedReader dukeRead = new BufferedReader(fw);
            String line = dukeRead.readLine();
            while (line != null) {
                existingIconChecker(line, tasks);
                line = dukeRead.readLine();
            }
            dukeRead.close();
        }
    }

    /**
     * @param line a string that containing the task descripition
     * @param tasks an arraylist containing all type of tasks
     */
    private void existingIconChecker(String line, ArrayList<Task> tasks) {
        if (line.contains("[D]")) {
            tasks.add(new Deadline(line.replace("[D]", "")));
        } else if (line.contains("[T]")) {
            tasks.add(new ToDo(line.replace("[T]", "")));
        } else if (line.contains("[E]")) {
            tasks.add(new Event(line.replace("[E]", "")));
        } else {
            tasks.add(new Task(line));
        }
    }

    /**
     * A method to delete all the contents in the arraylist and the txt file
     * @param tasks an arraylist of all the tasks
     * @throws IOException when the file cannot be found or created
     */
    public void deleteAll(ArrayList<Task> tasks) throws IOException {
        tasks.clear();
        File dukeTxt = new File("duke.txt");
        dukeTxt.delete();
        dukeTxt.createNewFile();
    }

    /**
     * @param tasks an arraylist of tasks
     * @param index the index to delete
     * @throws IOException exception to handle wrong userinput and output
     */
    public void deleteOne(ArrayList<Task> tasks, int index) throws IOException {
        tasks.remove(index - 1);
        saveData(tasks);
    }
}
