package skittles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    //public static final File info = new File("./data/data.txt");

    private static File info;
    private final String filePath;

    /**
     * Constructor for Data.
     * @param filePath takes in a String specifying file path.
     */
    public Data(String filePath) {
        info = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads the Tasks stored in the form of an ArrayList<Task>.
     * @return An ArrayList of type Task with all Tasks previously stored.
     */
    public static ArrayList<Task> loadUpInfo() {

        try {
            Scanner scanner = new Scanner(info);
            ArrayList<Task> addedTasks = new ArrayList<>();
            while (scanner.hasNext()) {
                try {
                    addedTasks.add(convertTextToTasks(scanner.nextLine()));
                } catch (IllegalStateException e) {
                    System.err.println(e);
                }
            }
            return addedTasks;

        } catch (FileNotFoundException e) {
            produceNewFile();
            return new ArrayList<Task>();
        }

    }

    /**
     * Writes the Task to the current text file.
     */
    public static void addInsideFile(Task taskToWrite) throws SkittlesException {
        if (!info.exists()) {
            produceNewFile();
        }
        try {
            FileWriter updater = new FileWriter("./data/data.txt", true);
            updater.write(taskToWrite.convertToText() + System.lineSeparator());
            updater.close();
        } catch (IOException e) {
            throw new SkittlesException("Hey I can't seem to update your tasks!");
        }

    }

    /**
     * Updates the Tasks stored in the txt file.
     */
    public static void updateTaskInTxt (ArrayList<Task> skittlesList) throws SkittlesException {
        try {
            FileWriter updater = new FileWriter("./data/data.txt");
            for (Task task : skittlesList) {
                updater.write(task.convertToText());
            }
            updater.close();
        } catch (IOException e) {
            throw new SkittlesException("Hey I'm unable to update your tasks!");
        }
    }

    /**
     * Converts text String into a Task object.
     * @param txt i.e. the String that is read from the txt file.
     * @return a new Task interpreted from the text file.
     */
    public static Task convertTextToTasks(String txt) {
        String[] words = txt.split(" \\| ");
        //read[0] will be like T, or E, or D
        String taskType = words[0];
        Task itemToAdd;

        switch(taskType) {
        case "D":
            itemToAdd = new Deadline(words[2], words[3]);
            break;
        case "E":
            itemToAdd = new Event(words[2], words[3], words[4]);
            break;
        case "T":
            itemToAdd = new ToDo(words[2]);
            break;
        default:
            throw new IllegalStateException("Hey I'm not sure what category this task is!");
        }

        if (words[1].equals("1")) {
            itemToAdd.strike();
        }
        return itemToAdd;
    }

    /**
     * Creates a new txt file if there currently is nothing stored.
     */
    public static void produceNewFile() {
        try {
            info.getParentFile().mkdirs();
            info.createNewFile();
        } catch (IOException e) {
            System.err.println("Hey we're unable to create a new file for you. Your tasks won't be saved!");
        }
    }

}
