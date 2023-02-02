package duke;
import java.io.File;
import java.io.FileWriter;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Handles storing of Tasks in hard drive.
 */
public class Storage {
    /** File path to stored file in hard drive. */
    private String filePath;
    /**
     * Creates a new Storage session.
     * @param filePath File path to stored file in hard drive.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads a list of tasks from hard drive.
     * @return A list of tasks stored in hard drive.
     * @throws DukeException when there is an error loading tasks from hard drive.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("No existing tasks found on this device. New storage created: " + file.getName());
            } else {
                System.out.println("Existing tasks found on this device have been loaded into this session. " +
                        "No action is needed.");
            }
        } catch (Exception e) {
            throw new DukeException();
        }
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] nextLine = scanner.nextLine().split(" ", 3);
                Boolean isCompleted = nextLine[0].equals("1") ? true : false;
                String firstWord = nextLine[1];
                if (firstWord.equals("deadline")) {
                    String bodyMessage = nextLine[2];
                    DeadLine task = new DeadLine("deadline", bodyMessage, isCompleted);
                    tasks.add(task);
                } else if (firstWord.equals("event")) {
                    String bodyMessage = nextLine[2];
                    Event task = new Event("event", bodyMessage, isCompleted);
                    tasks.add(task);
                } else if (firstWord.equals("todo")) {
                    String bodyMessage = nextLine[2];
                    ToDo task = new ToDo("todo", bodyMessage, isCompleted);
                    tasks.add(task);
                }
            }
            scanner.close();
            return tasks;
        }   catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * Updates task completion status in hard drive.
     * @param taskData A String that identifies the task in hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void changeTaskStatus(String taskData) throws DukeException {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);
            StringBuffer buffer = new StringBuffer();
            String isCompleted = taskData.split(" ", 2)[0];
            String description =   taskData.split(" ", 2)[1];
            String updatedDescription = isCompleted.equals("1") ? "0 " + description : "1 " + description;
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskData)) {
                    buffer.append(updatedDescription + "\n");
                } else {
                    buffer.append(nextLine + "\n");
                }
            }
            String fileContents = buffer.toString();
            sc.close();
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(fileContents);
            writer.close();
        }
        catch (Exception e) {
            throw new DukeException();
        }
    }
    /**
     * Deletes task from hard drive.
     * @param taskData A String that identifies the task in hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void deleteTask(String taskData) throws DukeException {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskData)) {
                    //do nothing
                } else {
                    buffer.append(nextLine + "\n");
                }
            }
            String fileContents = buffer.toString();
            sc.close();
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(fileContents);
            writer.close();
        }
        catch (Exception e) {
           throw new DukeException();
        }
    }

    /**
     * Adds a task to hard drive.
     * @param input Description of task to be added to hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void addTask(String input) throws DukeException {
        try {
            //Instantiating the File class
            File myObj = new File("duke.txt");
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(myObj);
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + "\n");
            }
            String fileContents = buffer.toString();
            sc.close();
            //Replacing the old line with new line
            fileContents = fileContents + input + "\n";
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(fileContents);
            writer.close();
        }
        catch (Exception e) {
            throw new DukeException();
        }
    }
}




