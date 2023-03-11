package crystal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import crystal.task.Deadline;
import crystal.task.Event;
import crystal.task.Task;
import crystal.task.Todo;


/**
 * Represents the Storage task.
 */

public class Storage {
    private String filepath;

    /**
     * Constructor for Storage class.
     *
     * @param filepath filepath where the list is stored at.
     */

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Method to write to the file.
     *
     * @param filepath  Filepath to store the list at
     * @param textToAdd Text to be printed on the file
     */
    //Save to file
    public void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Method to save the file.
     *
     * @param tasks Tasklist to be saved
     */
    public void saveFile(TaskList tasks) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).toExport() + "\n");
        }

        try {
            writeToFile(this.filepath, str + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /**
     * Method to read the file and return a Task arraylist.
     *
     * @throws CrystalException When the date format is not recognised
     */
    //Load file
    public ArrayList<Task> readFileContents() throws CrystalException {

        File file = new File(this.filepath); // create a File for the given file path
        ArrayList<Task> temp = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String nextInput = "";
                nextInput = s.nextLine();
                String description = "";
                String priority = "";
                description = nextInput.substring(8);
                priority = nextInput.substring(6, 8).trim();

                int priorityNum = Integer.parseInt(priority);
                boolean isPriority = false;
                if (priorityNum != 0) {
                    isPriority = true;
                }
                boolean isComplete = false;
                if (nextInput.contains("| 0 |")) {
                    isComplete = true;
                }
                if (nextInput.startsWith("T")) {
                    Task newTask = new Todo(description.trim());
                    newTask.setIsDone(isComplete);
                    newTask.setIsPriority(isPriority);
                    newTask.setPriorityNum(priorityNum);
                    temp.add(newTask);

                } else if (nextInput.startsWith("D")) {

                    int index = description.lastIndexOf("|");
                    String time = description.substring(description.lastIndexOf("|") + 1);
                    description = description.replace(description.substring(index), "");
                    try {
                        Task newTask = new Deadline(description.trim(), time.trim());
                        newTask.setIsDone(isComplete);
                        newTask.setIsPriority(isPriority);
                        newTask.setPriorityNum(priorityNum);
                        temp.add(newTask);
                    } catch (CrystalException e) {
                        System.out.println("Wrong date format! Please change!");
                    }

                } else if (nextInput.startsWith("E")) {
                    int index = description.lastIndexOf("|");
                    int endIndex = description.lastIndexOf(">");
                    String startTime = description.substring(description.lastIndexOf("|") + 1,
                            description.lastIndexOf(">") - 1);
                    String endTime = description.substring(description.lastIndexOf(">") + 1);
                    description = description.replace(description.substring(index), "");
                    Task newTask = new Event(description.trim(), startTime.trim(), endTime.trim());
                    newTask.setIsDone(isComplete);
                    newTask.setIsPriority(isPriority);
                    newTask.setPriorityNum(priorityNum);
                    temp.add(newTask);

                }

            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return temp;
    }


}
