package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/*
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static String FILE_PATH = "./data/eren.txt";
    private static String DIR_PATH = "./data/";

    /**
     * Saves the list of task in txt file
     */ 
    public void saveFile(ArrayList<Task> tasks) {
        String fileContent;
        String dateTime;
        fileContent = "  TYPE  | COMPLETED | DETAILS | DATE\n";
        for (Task t : tasks) {
            dateTime = null;
            if (t.getType() == 'T') {
                fileContent += "  Todo  |";
            }
            else if (t.getType() == 'D') {
                fileContent += "Deadline|";
                if (t instanceof Deadline) {
                    Deadline task = (Deadline) t;
                    dateTime = task.getDateTime();
                }
            }
            else {
                fileContent += "  Event |";
                if (t instanceof Event) {
                    Event task = (Event) t;
                    dateTime = task.getDateTime();
                }
            }
            fileContent += t.isDone() ? "    YES    | " : "    NO     | ";
            fileContent += t.getDescription() + " | " ;
            fileContent += (dateTime == null ? "---" : dateTime) + "\n";
        }
        File file = new File(FILE_PATH);
        try {
            Files.createDirectories(Paths.get(DIR_PATH));
        } catch(IOException e){
            System.out.println("Error creating folder");
        }
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error saving to file");
            }
        }
        writeToFile(fileContent);
    }

    /**
     * Writes to file
     * 
     * @param description File content to be written to the file
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
     * Loads the list of task from txt file
     */ 
    public ArrayList<Task> loadFile() {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        try {   
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String currLine;
                Boolean hasSkipped = true;
                String[] splitInput;
                Task task;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
                LocalDateTime dateTime;
                while ((currLine = fileReader.readLine()) != null) {
                    if(!hasSkipped) {
                        splitInput = currLine.split("\\|");
                        if(splitInput[0].contains("Deadline")) {
                            dateTime = LocalDateTime.parse(splitInput[3].trim(), formatter); 
                            task = new Deadline(splitInput[2].trim(),dateTime);
                        } else if(splitInput[0].contains("Todo")) {
                            task = new Todo(splitInput[2].trim());
                        } else {
                            String[] splitToAndFrom = (splitInput[3].trim()).split(" - ");
                            dateTime = LocalDateTime.parse(splitToAndFrom[0], formatter);
                            LocalDateTime dateTimeTo = LocalDateTime.parse(splitToAndFrom[1], formatter);
                            task = new Event(splitInput[2].trim(), dateTime, dateTimeTo);
                        }
                        if(splitInput[1].contains("YES"))
                            task.mark();
                        tasks.add(task);
                    }
                    hasSkipped = false;
                }
                assert tasks.size() >= 0 : "Something is wrong with the data file";
                fileReader.close();
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Error when loading file, a new file will be created");
        }
        return new ArrayList<Task>();
    }
}
