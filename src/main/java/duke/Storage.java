package duke;

import java.util.Scanner;

import java.io.PrintStream;

import java.io.FileWriter;
import java.io.File;

import java.io.IOException;

/**
 * Storage class manages the reading and writing of the list of task into
 * the user machine's hardisk. In the main class, Duke, the Storage class is
 * first used to check if there is the dukeList.txt file found in a dynamic
 * directory, which is OS independent. If the file is found, date from the
 * file is read and processed into TaskList<Task>. If the file is not found,
 * the text file is created, and everytime a user changes the list of tasks,
 * the new list of task is saved into the text file. The old list of task is
 * overwritten to save space. 
 *
 * @author Muhammad Reyaaz 
 * @version %I% %G% 
 * @since 11
 * @see TaskList
 */

class Storage {
    
    //Dynamic directory
    private String directory = System.getProperty("user.dir");
    //Full directory with dukeList.txt
    private java.io.File path = new java.io.File(directory + "/dukeList.txt");
    //New TaskList<Task> to read / write from/into
    private TaskList<Task> tasks = new TaskList<>();
    
    
    /**
    * Sole constructor. (For invocation by subclass
    * constructors, typically implicit)
    */

    protected Storage() {
    }

    /**
     * Checks if the dukeList text file exist in the correct dynamic
     * directory, OS independent. 
     *
     * @return pathPresent
     *
     */

    boolean isDirectory() {
        return path.exists();
    }

    /**
     * Creates the file in the dyanamic directory located in
     * ip/src/main/java
     *
     * @exception IOException 
     */

    void createDirectory() {
        try {
            if (!isDirectory()) {
                path.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
    
    /**
     * Whenever the user list of task changes, the previous data in the
     * dukeList is overwritten to a new list of tasks. 
     *
     * @param listOfTasks New list of tasks in String
     * @exception IOException 
     */

    void writeToFile(String listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(directory + "/dukeList.txt",false);
            fileWriter.write(listOfTasks);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    /**
     * The text from dukeList.txt is processed. Manipulation is needed
     * because the text is stored as an array, and not as commands from the
     * user, since it does not make sense to store as the latter. 
     *
     * @exception IOException
     */

    void readFromFile() {
        try {
          String path = System.getProperty("user.dir") + "/dukeList.txt";
          Scanner scanner = new Scanner(new File(path));
          String inputFromFile = scanner.useDelimiter("\\A").next();
          String[] inputArr = inputFromFile.substring(1,inputFromFile.length()-1).split(",");
          for (String task : inputArr) {  
            if (("" + task.charAt(0)).equals(" ")) {
                task = task.substring(1);
            }
            if (isSymbol(task, Parser.MARK_SYMBOL) || isSymbol(task, " ")) {
                rephraseNoDate(task);
            } else if (isSymbol(task, Parser.TODO_SYMBOL)) {
                rephraseToDo(task);
            } else if (isSymbol(task, Parser.DEADLINE_SYMBOL)) {
                rephraseDeadline(task);
            } else if (isSymbol(task, Parser.EVENT_SYMBOL)) {
                rephraseEvents(task);
            }
          }
        } catch(IOException e) {
            this.createDirectory();
        }
    }
    
    /**
     * Checks if the stored task is a todo, deadline or event.
     * 
     * @param task Task in the array of tasks in the dukeList.txt file
     * @param symbol How the task is demarcated 
     * @return hasSymbol
     */

    boolean isSymbol(String task, String symbol) {
        if (("" + task.charAt(1)).equals(symbol) || ("" + task.charAt(2)).equals(symbol)) {
            return true;

        } else {
            return false;
        }
    }
    
    /**
     * Process the saved task to check if it is marked as done or undone.
     */

    void markTask(String task) {
        boolean isMark = ("" + task.charAt(1)).equals(Parser.MARK_SYMBOL) ? true : false;
        if (isMark) {
            this.tasks = Parser.mark(this.tasks.numberOfTasks() - 1, this.tasks);
        } 
    }
    
    /**
     * Change the format of the saved task that is not todo, deadline or
     * event so that the same method in TaskList can be invoked
     */

    void rephraseNoDate(String input) {
        this.tasks = this.tasks.add(new Task(input.substring(4)));
        markTask(input);
    }

    /**
     * Change the format of the saved todo task so that the same method in
     * TaskList can be invoked
     */
    
    void rephraseToDo(String input) {
        this.tasks = Parser.toDo(input.substring(7),this.tasks);
        markTask(input);
    }

    /**
     * Change the format of the saved deadline task so that the same method in
     * TaskList can be invoked
     */
    
    void rephraseDeadline(String input) {
        int indexOfBracket = input.indexOf(" (");
        this.tasks = Parser.deadline(input.substring(7, indexOfBracket), input.substring(indexOfBracket + 6, input.length() - 1), this.tasks);
        markTask(input);
    }

    /**
     * Change the format of the saved events task so that the same method in
     * TaskList can be invoked
     */
    
    void rephraseEvents(String input) { 
        int indexOfFrom = input.indexOf("(from: ");
        int indexOfTo = input.indexOf("(to: ");
        this.tasks = Parser.events(input.substring(8, indexOfFrom), input.substring(indexOfFrom + 6, indexOfTo - 1), input.substring(indexOfTo + 4, input.length() - 1), this.tasks);
        markTask(input);
    }
    
    /**
     * Getter to get the current set of tasks 
     * @return TaskList<Task> 
     */

    TaskList<Task> getTasks() {
        return this.tasks;
    }
    
}
