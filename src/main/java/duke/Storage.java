package duke;
import duke.exceptions.NeroException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the reading, writing and saving of duke.txt file
 */
public class Storage {

    private String filePath;
    private String directoryPath;

    /**
     * Constructor for Storage
     * @param filePath String containing file path where duke.txt is stored
     * @param directoryPath String containing directory path where duke.txt is stored
     */
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    /**
     * Reads the duke.txt file and saves the saved tasks into a task list
     * @return Task list containing saved tasks
     * @throws NeroException Throws when file is not found
     */
    TaskList readFile() throws NeroException {
        TaskList taskList = new TaskList();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        assert directory.exists();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] cleanedInputs = line.split(" \\| ");
                boolean isDone = (cleanedInputs[1].equals("0")) ? false : true;
                if (cleanedInputs[0].equals("T")) {
                    taskList.addTask(new ToDo(cleanedInputs[2], isDone));
                } else if (cleanedInputs[0].equals("D")) {
                    String cleanedDuration = cleanedInputs[3].replace("by:", "");
                    taskList.addTask(new Deadline(cleanedInputs[2], isDone, cleanedDuration));
                } else if (cleanedInputs[0].equals("E")) {
                    String[] splitDates = cleanedInputs[3].split(" ");
                    String cleanedStartDate = splitDates[0].replace("from: ", "");
                    String cleanedEndDate = splitDates[1].replace("to: ", "");
                    taskList.addTask(new Event(cleanedInputs[2], isDone,
                            cleanedStartDate, cleanedEndDate));
                }
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new NeroException("File was not found!");
        }
    }

    /**
     * Saves the current task list into duke.txt file
     * @param taskList Task list containing all current tasks
     * @throws IOException Throws when file is not found
     */
    void saveFile(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                fw.write(taskList.get(i).toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("The file was not found!");
        }
    }
}
