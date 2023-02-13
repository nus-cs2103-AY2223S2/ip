package duke.storage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.DeadLine;
import duke.tasks.Event;
import duke.tasks.ToDo;


/**
 * Handles storing of Tasks in hard drive.
 */
public class Storage {
    private File file;
    /**
     * Creates a new Storage session.
     * @param filePath File path to stored file in hard drive.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

    }
    /**
     * Loads a list of tasks from hard drive.
     * @return A list of tasks stored in hard drive.
     * @throws DukeException when there is an error loading tasks from hard drive.
     */
    public ArrayList<Task> load() {

        //Creates a new tasklist for the current session.
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            //Checks if the file has been created. If no, create the file.
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] nextLine = scanner.nextLine().split(" ", 3);
                //first Word should be a number indicating if task has been completed or not. Initialise boolean value.
                Boolean isCompleted = nextLine[0].equals("1") ? true : false;
                //second word should be either deadline, event, or ToDo.
                String secondWord = nextLine[1];
                //Rest of the message contains details about the task.
                String bodyMessage = nextLine[2];
                switch (secondWord) {
                    case "deadline":
                        DeadLine taskDeadline = new DeadLine("deadline", bodyMessage, isCompleted);
                        tasks.add(taskDeadline);
                        break;
                    case "event":
                        Event taskEvent = new Event("event", bodyMessage, isCompleted);
                        tasks.add(taskEvent);
                        break;
                    case "todo":
                        ToDo taskToDo = new ToDo("todo", bodyMessage, isCompleted);
                        tasks.add(taskToDo);
                    default:
                        //do nothing, task is not valid
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Updates task completion status in hard drive.
     * @param taskIdentifier A String that identifies the task in hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void changeTaskStatus(String taskIdentifier) throws DukeException {
        try {
            String[] taskIdentifierSplit = taskIdentifier.split(" ", 2);
            //The first item in the array is a String indicating status of completion. 1 for done, 0 for undone.
            String completionStatus = taskIdentifierSplit[0];
            //The second item in the array is the description of the task.
            String taskDescription = taskIdentifierSplit[1];

            //update task description
            String updatedTaskDescription = completionStatus.equals("1") ? "0 " + taskDescription
                                                                         : "1 " + taskDescription;

            //To read the file and write the same thing but updating the status of the specified task
            Scanner sc = new Scanner(file);
            StringBuffer write = new StringBuffer();

            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskIdentifier)) {
                    write.append(updatedTaskDescription + "\n");
                } else {
                    write.append(nextLine + "\n");
                }
            }
            String writeToFile = write.toString();
            sc.close();
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(writeToFile);
            writer.close();
        }
        catch (Exception e) {
            throw new DukeException("Oops! There was a problem changing the status of this task. " +
                    "Please check that your command is used correctly. For help, type 'help'.");
        }
    }
    /**
     * Deletes task from hard drive.
     * @param taskIdentifier A String that identifies the task in hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void deleteTask(String taskIdentifier) throws DukeException {
        try {
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(file);
            StringBuffer write = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.equals(taskIdentifier)) {
                    //do nothing
                } else {
                    write.append(nextLine + "\n");
                }
            }
            String writeToFile = write.toString();
            sc.close();
            //instantiating the FileWriter class
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(writeToFile);
            writer.close();
        }
        catch (Exception e) {
           throw new DukeException("Oops! There was a problem deleting this task. " +
                   "Please check that your command is used correctly. For help, type 'help'.");
        }
    }

    /**
     * Adds a task to hard drive.
     * @param newTask Description of task to be added to hard drive.
     * @throws DukeException when there is an error performing the operation.
     */
    public void addTask(String newTask) throws DukeException {
        try {
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(file);
            StringBuffer write = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                write.append(sc.nextLine() + "\n");
            }
            String writeToFile = write.toString();
            sc.close();

            writeToFile = writeToFile + newTask + "\n";
            FileWriter writer = new FileWriter("duke.txt");
            writer.append(writeToFile);
            writer.close();
        }
        catch (Exception e) {
            throw new DukeException("Oops! There was a problem adding this task. " +
                    "Please check that your command is used correctly. For help, type 'help'.");
        }
    }
}




