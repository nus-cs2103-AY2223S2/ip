package duke;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;

import java.io.IOException;
/**
 * Manages the reading and writing of the list of task into
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
    static String FILENAME = "/dukeList.txt";
    //Full directory with dukeList.txt
    private java.io.File path = new java.io.File(directory + FILENAME);
    //New TaskList<Task> to read / write from/into
    private TaskList<Task> tasks = new TaskList<>();
    static int SIZE_OF_BOX = 3;

    /**
     * Makes the default constructoe exlicit
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
     * Overwrites previous written data and writes new data
     * @param listOfTasks New list of tasks in String
     * @exception IOException
     */
    void writeToFile(String listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(directory + FILENAME, false);
            if (!listOfTasks.equals("")) {
                fileWriter.write(listOfTasks);
            } else {
                fileWriter.write("");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
    /**
     * Processes and manipulates the text from file
     * because the text is stored as an array, and not as commands from the
     * user, since it does not make sense to store as the latter.
     *
     * @exception IOException
     */
    void readFromFile() {
        try {
            String path = System.getProperty("user.dir") + FILENAME;
            Scanner scanner = new Scanner(new File(path));
            String inputFromFile = "";
            String[] inputArr = {};
            inputFromFile = scanner.useDelimiter("\\A").next();
            inputArr = inputFromFile.substring(1, inputFromFile.length() - 1).split(",");
            for (String task : inputArr) {
                boolean isInvalidTask = task.length() == 1 || task.length() == 0;
                if (isInvalidTask) {
                    break;
                }
                boolean isInitialWhiteSpace = ("" + task.charAt(0)).equals(" ");
                if (isInitialWhiteSpace) {
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
     * Processes the saved task to check if it is marked as done or undone.
     */
    void markTask(String task) {
        boolean isMark = ("" + task.charAt(1)).equals(Parser.MARK_SYMBOL) ||
                ("" + task.charAt(4)).equals(Parser.MARK_SYMBOL);
        if (isMark) {
            this.tasks = Parser.mark(this.tasks.numberOfTasks() - 1, this.tasks);
        }
    }
    /**
     * Changes the format of the saved task that is not todo, deadline or
     * event so that the same method in TaskList can be invoked
     */
    void rephraseNoDate(String input) {
        this.tasks = this.tasks.add(new Task(input.substring(4)));
        markTask(input);
    }
    /**
     * Changes the format of the saved todo task so that the same method in
     * TaskList can be invoked
     */
    void rephraseToDo(String input) {
        this.tasks = Parser.toDo(input.substring(7),this.tasks);
        markTask(input);
    }
    /**
     * Changes the format of the saved deadline task so that the same method in
     * TaskList can be invoked
     */
    void rephraseDeadline(String input) {
        int indexOfBracket = input.indexOf(" (");
        String task = input.substring(7, indexOfBracket);
        String deadlineDate = input.substring(indexOfBracket + (2* SIZE_OF_BOX), input.length() - 1);
        this.tasks = Parser.deadline(task, deadlineDate, this.tasks);
        markTask(input);
    }
    /**
     * Changes the format of the saved events task so that the same method in
     * TaskList can be invoked
     */
    void rephraseEvents(String input) {
        int indexOfFrom = input.indexOf("(from: ");
        int indexOfTo = input.indexOf("(to: ");
        String task = input.substring(7, indexOfFrom);
        String fromDate = input.substring(indexOfFrom + (2 * SIZE_OF_BOX), indexOfTo - 1);
        String toDate =  input.substring(indexOfTo + SIZE_OF_BOX + 1, input.length() - 1);

        fromDate = fromDate.trim();
        toDate = toDate.trim();

        System.out.println("fromDate:" + fromDate);
        System.out.println("toDate:" + toDate);
        this.tasks = Parser.events(task, fromDate, toDate, this.tasks);
        markTask(input);
    }
    /**
     * Gets the current set of tasks
     * @return TaskList<Task>
     */
    TaskList<Task> getTasks() {
        return tasks;
    }

}
