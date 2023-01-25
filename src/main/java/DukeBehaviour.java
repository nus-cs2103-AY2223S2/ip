import com.sun.source.tree.TryTree;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Arrays;


/**
 * Class to encapsulate behavior of personal assistant Duke!
 * @author Sze Jian Cheng
 */

public class DukeBehaviour {
    Boolean isActive = true;
    ArrayList<Task> taskList;
    Path dataPath;

    /**
     * Public constructor for the DukeBehaviour Object.
     * @param None
     * @return None
     */
    public DukeBehaviour() {
        System.out.println("DukeBehaviour constructor called...");
        System.out.println("Attempting to load data...");
        initMemData();
        //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java

    }

    private void initMemData() {
        Path dirPath = Paths.get(".", "data");
        System.out.println("data path: " + dirPath.toAbsolutePath());
        boolean directoryExists = java.nio.file.Files.exists(dirPath);
        System.out.println("Does the data folder exist?: " + directoryExists);
        try {
            //This method creates a directory if it does not exist yet, but will not
            //throw an error even if it exists, and so is safe to call.
            Files.createDirectories(dirPath);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        dataPath = Paths.get(dirPath.toString(), "DukeMem.ser");
        boolean dataExists = java.nio.file.Files.exists(dataPath);
        System.out.println("Does the data exist?: " + dataExists);
        if (!dataExists) {
            updateMem(new ArrayList<>());
        }

        try {
            FileInputStream readData = new FileInputStream(dataPath.toString());
            ObjectInputStream readStream = new ObjectInputStream(readData);
            taskList = (ArrayList<Task>) readStream.readObject();
        } catch (IOException e){
            System.out.println("Error in loading data, previous session could not be restored.");
            System.out.println(e.getMessage());
            System.out.println("Creating new tasklist...");
            taskList = new ArrayList<>();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateMem(ArrayList<Task> taskList) {
        try {
            FileOutputStream writeData = new FileOutputStream(dataPath.toString());
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(taskList);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Public method for DukeBehaviour to receive user input in the form of Strings.
     * @param userIn user input of type String
     * @return None
     * @exception DukeException on input error
     */
    public void receiveInput(String userIn){
        try {
            if (userIn.equals("bye")) {
                //System.out.println("exit command received, exiting...");
                updateMem(taskList);
                isActive = false;
            } else if (userIn.equals("list")) {
                displayList();
            } else if (userIn.split(" ")[0].equals("mark")) {
                mark(userIn);
            } else if (userIn.split(" ")[0].equals("unmark")) {
                unmark(userIn);
            } else if (userIn.split(" ")[0].equals("delete")) {
                delete(userIn);
            } else {
                addTask(userIn);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Private method for handling delete command.
     * @param userIn user input of type String
     * @return None
     * @throws DukeException on index out-of-range
     */
    private void delete(String userIn) throws DukeException{
        int index = Integer.parseInt(userIn.split(" ")[1]);
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
        taskList.remove(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void echo(String userIn){
        System.out.println(userIn);
    }
    /**
     * Private method for handling mark command.
     * @param userIn user input of type String
     * @return None
     * @throws DukeException on index out-of-range
     */
    private void mark(String userIn) throws DukeException{
        int index = Integer.parseInt(userIn.split(" ")[1]);
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
        currTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask);
    }
    /**
     * Private method for handling unmark command.
     * @param userIn user input of type String
     * @return None
     * @throws DukeException on index out-of-range
     */
    private void unmark(String userIn) throws DukeException{
        int index = Integer.parseInt(userIn.split(" ")[1]);
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask);
    }
    /**
     * Private method for handling all task-related commands.
     * @param userIn user input of type String
     * @return None
     */
    private void addTask(String userIn){
        String taskType = userIn.split(" ")[0];
        try{
            switch (taskType) {
                case "todo":
                    addToDo(userIn);
                    break;
                case "deadline":
                    addDeadline(userIn);
                    break;
                case "event":
                    addEvent(userIn);
                    break;
                default:
                    throw new DukeException("I'm sorry, I could not understand that command.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Private method for handling commands regarding Events.
     * @param userIn user input of type String
     * @return None
     */
    private void addEvent(String userIn) {
        String descAndTimes = userIn.replace("event", "");
        Event newEvent = new Event(
                descAndTimes.split("/")[0].trim(),
                descAndTimes.split("/")[1].replace("from", "").trim(),
                descAndTimes.split("/")[2].replace("to", "").trim()
        );
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for handling commands regarding Deadlines.
     * @param userIn user input of type String
     * @return None
     */
    private void addDeadline(String userIn) {
        String descAndBy = userIn.replace("Deadline", "");
        Deadline newDeadline = new Deadline(descAndBy.split("/")[0].trim(), descAndBy.split("/")[1].replace("by", "").trim());
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for handling commands regarding ToDos.
     * @param userIn user input of type String
     * @return None
     */
    private void addToDo(String userIn) throws DukeException{
        if (userIn.split(" ").length == 1) {
            throw new DukeException("todo cannot have no description!");
        }
        ToDo newToDo = new ToDo(userIn.replace("ToDo", ""));
        taskList.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for printing list of tasks stored.
     * @return None
     */
    private void displayList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("End of task list.");
    }
}
