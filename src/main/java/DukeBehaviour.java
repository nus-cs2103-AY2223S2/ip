<<<<<<< HEAD
import com.sun.source.tree.TryTree;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Arrays;
=======
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
>>>>>>> branch-Level-8


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
     */
    public void receiveInput(String userIn){
        ArrayList<String> tokens = tokenize(userIn);
        String key = tokens.get(0);
        try {
<<<<<<< HEAD
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
=======
            switch (key){
                case "bye":
                    isActive = false;
                    break;
                case "list":
                    displayList();
                    break;
                case "mark":
                    mark(tokens);
                    break;
                case "unmark":
                    unmark(tokens);
                    break;
                case "delete":
                    delete(tokens);
                    break;
                case "todo":
                    addToDo(tokens);
                    break;
                case "deadline":
                    addDeadline(tokens);
                    break;
                case "event":
                    addEvent(tokens);
                    break;
                default:
                    throw new DukeException("I'm sorry, I could not understand that command.");
>>>>>>> branch-Level-8
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
        System.out.println("Invalid input received! \n datetime could not be parsed, make sure " +
                "that your date follows the format YYYY-MM-DD");
    }
    }

    /**
     * Private method for handling delete command.
     * @param userIn user input of type String
     * @throws DukeException on index out-of-range
     */
    private void delete(ArrayList<String> userIn) throws DukeException{
        if (userIn.size()>2 || userIn.size()==1) {
            throw new DukeException("Invalid input received! \nDelete commands are in the form of: delete i \n(only 1 whitespace allowed)");
        }
        int index = Integer.parseInt(userIn.get(1));
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
     * @throws DukeException on index out-of-range
     */
    private void mark(ArrayList<String> userIn) throws DukeException{
        if (userIn.size()>2 || userIn.size()==1) {
            throw new DukeException("Invalid input received! \nMark commands are in the form of: mark i \n(only 1 whitespace allowed)");
        }
        int index = Integer.parseInt(userIn.get(1));

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
     * @throws DukeException on index out-of-range
     */
    private void unmark(ArrayList<String> userIn) throws DukeException{
        if (userIn.size()>2 || userIn.size() == 1) {
            throw new DukeException("Invalid input received! \nUnmark commands are in the form of: unmark i \n(only 1 whitespace allowed)");
        }
        int index = Integer.parseInt(userIn.get(1));
        if (index < 1 || index > taskList.size()){
            throw new DukeException("index " + index +" not in range!");
        }
        Task currTask = taskList.get(index-1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask);
    }

    /***
     * Remove whitespace from typed input and returns an arraylist of tokens
     * @param input String userinput to be tokenized
     * @return tokens
     */
    private ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(input.split(" ")));
        tokens.removeIf(s -> s.equals(" ") || s.equals(""));
        tokens.forEach(s -> s.trim());
        //System.out.println(tokens);
        return tokens;
    }

    /**
     * Private method for handling commands regarding Events.
     * @param userIn user input of type String
     */
    private void addEvent(ArrayList<String> userIn) throws DukeException, DateTimeParseException{
        //String descAndTimes = userIn.replace("event", "");
        //index 0 should be event
        //index 1-? should be name
        //index containing "/from" should be immediately preceding from param
        //index containing "to" should be immediately preceding to param
        int fromId = userIn.indexOf("/from");
        int toId = userIn.indexOf("/to");
        if (fromId < 0 || toId < 0){
            throw new DukeException("Invalid input received! \nEvent commands are in the form of: event name /from fromtime /to totime \n(remember to include '/from' and '/to')");
        }

        String name = String.join(" ", userIn.subList(1, fromId));
        LocalDate from = LocalDate.parse(String.join(" ", userIn.subList(fromId+1, toId)));
        LocalDate to = LocalDate.parse(String.join(" ", userIn.subList(toId+1, userIn.size())));
        Event newEvent = new Event(name, from, to);
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for handling commands regarding Deadlines.
     * @param userIn user input of type String
     */
    private void addDeadline(ArrayList<String> userIn) throws DukeException, DateTimeParseException {
        LocalDate by;
        //String descAndTimes = userIn.replace("event", "");
        //index 0 should be event
        //index 1-? should be name
        //index containing "/by" should be immediately preceding from param
        int byId = userIn.indexOf("/by");
        if (byId < 0){
            throw new DukeException("Invalid input received! \nDeadline commands are in the form of: deadline name /by bytime \n(remember to include '/by')");
        }

        String name = String.join(" ", userIn.subList(1, byId));
        by = LocalDate.parse(String.join(" ", userIn.subList(byId+1, userIn.size())));
        Deadline newDeadline = new Deadline(name, by);
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for handling commands regarding ToDos.
     * @param userIn user input of type String
     */
    private void addToDo(ArrayList<String> userIn) throws DukeException{
        if (userIn.size() == 1) {
            throw new DukeException("todo cannot have no description!");
        }
        String name = String.join(" ", userIn.subList(1, userIn.size()));
        ToDo newToDo = new ToDo(name);
        taskList.add(newToDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newToDo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    /**
     * Private method for printing list of tasks stored.
     */
    private void displayList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i<taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("End of task list.");
    }
}
