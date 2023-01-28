import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {

    // Create the file to write to, if it doesn't already exist

    /**
     * Constructor for a fileManager object
     */
    public FileManager() {
        File folder = new File("./data/");
        File dataFile = new File("./data/duke.txt");
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException  e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(ArrayList<Task> taskList) {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                myWriter.write(task.encode() + "\n"); // Code out this decode function in Tasks class to return string
            }
                myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Task decode(String data) {
        // Use examples in qn page to decode into tasks
        char taskType = data.charAt(0);
        Task currentTask = null;
        switch (taskType) {
            case 'T':
                if (data.charAt(4) == 'X') {
                    currentTask = new Todo(true, "todo " + data.substring(8));
                } else {
                    currentTask = new Todo(false, "todo " + data.substring(8));
                }
                break;
            case 'D':
                String tempString = data.substring(8);
                String taskName = tempString.substring(0, tempString.indexOf(" | "));
                String deadline = tempString.substring(tempString.indexOf(" | ") + 3);
                String reformattedInput = "deadline " + taskName + " /by " + deadline;
                if (data.charAt(4) == 'X') {
                    currentTask = new Deadline(true, reformattedInput);
                } else {
                    currentTask = new Deadline(false, reformattedInput);
                }
                break;
            case 'E':
                String temp = data.substring(8);
                String taskTitle = temp.substring(0, temp.indexOf(" | "));
                String datesString = temp.substring(temp.indexOf(" | ") + 3);
                String fromDate = datesString.substring(0, datesString.indexOf(" | "));
                String toDate = datesString.substring(datesString.indexOf(" | ") + 3);
                String newInput = "event " + taskTitle + " /from " + fromDate + " /to " + toDate;
                if (data.charAt(4) == 'X') {
                    currentTask = new Event(true, newInput);
                } else {
                    currentTask = new Event(false, newInput);
                }
                break;
        }
        return currentTask;
    }

    public ArrayList<Task> read() {
        ArrayList<Task> retrievedList = new ArrayList<>();
        try {
            File myObj = new File("./data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                retrievedList.add(this.decode(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return retrievedList;
    }
}
