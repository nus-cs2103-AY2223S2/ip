package duke;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/eren.txt";
    private static final String DIR_PATH = "./data/";

    /**
     * Saves the list of task in txt file
     *
     * @param tasks Takes in the current list of task
     */ 
    public void saveFile(ArrayList<Task> tasks) {
        String fileContent;
        String dateTime;
        fileContent = "  TYPE  | COMPLETED | DETAILS | DATE\n";

        for (Task t : tasks) {
            fileContent+= getTaskType(t);
            fileContent += t.isDone() ? "    YES    | " : "    NO     | ";
            fileContent += t.getDescription() + " | " ;
            fileContent += (t.getDateTime() == null ? "---" : t.getDateTime()) + "\n";
        }
        File file = new File(FILE_PATH);

        try {
            Files.createDirectories(Paths.get(DIR_PATH));
        } catch (IOException e) {
            System.out.println("Error creating folder");
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
        }
        writeToFile(fileContent);
    }

    /**
     * Writes to file.
     * 
     * @param description File content to be written to the file.
     */ 
    public void writeToFile(String description) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(description);
            fw.close();
          } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
          }
    }


    /**
     * Loads the list of task from txt file.
     */ 
    public ArrayList<Task> loadFile() {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        try {   
            if (file.exists()) {
                readFile(file, tasks);
                assert tasks.size() >= 0 : "Something is wrong with the data file";
            }
        } catch (IOException e) {
            System.out.println("Error when loading file, a new file will be created");
        }
        return tasks;
    }

    /**
     * Returns a string representation of the task type.
     *
     * @param task Task to be saved.
     * @return String of task type.
     */
    public String getTaskType(Task task) {
        String fileType = "";
        if (task.getType() == 'T') {
            fileType += "  Todo  |";
        } else if (task.getType() == 'D') {
            fileType += "Deadline|";
        } else {
            fileType += "  Event |";
        }
        return  fileType;
    }

    /**
     * Reads file stored in user computer.
     *
     * @param file File to read from.
     * @param tasks List of task to populate with using data read.
     * @throws IOException Throws exception when file cannot be read or failed I/O operation.
     */
    public void readFile(File file, ArrayList<Task> tasks) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String currLine;
        String[] splitInput;
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        LocalDateTime dateTime;

        while ((currLine = fileReader.readLine()) != null) {
            splitInput = currLine.split("\\|");
            if(splitInput[0].contains("Deadline")) {
                dateTime = LocalDateTime.parse(splitInput[3].trim(), formatter);
                task = new Deadline(splitInput[2].trim(),dateTime);
            } else if (splitInput[0].contains("Todo")) {
                task = new Todo(splitInput[2].trim());
            } else if (splitInput[0].contains("Event")) {
                String[] splitToAndFrom = (splitInput[3].trim()).split(" - ");
                dateTime = LocalDateTime.parse(splitToAndFrom[0], formatter);
                LocalDateTime dateTimeTo = LocalDateTime.parse(splitToAndFrom[1], formatter);
                task = new Event(splitInput[2].trim(), dateTime, dateTimeTo);
            } else {
                continue;
            }
            if (splitInput[1].contains("YES")) {
                task.mark();
            }
            tasks.add(task);
        }
        fileReader.close();
    }
}
