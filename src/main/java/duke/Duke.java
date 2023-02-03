package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.fxml.FXML;

/**
 * Duke is the main class that directly handles the user input, and abstract
 * encapsulates various information that is needed to store and display
 * results to the user. This includes:
 * <ul>
 * <li> The list of Tasks that the user keys in
 * <li> Reading and writing information from / to the hardisk of the user's
 * machine
 * <li> Various responses by Duke bot customised according to a football
 * character
 * </ul>
 * It has methods for different levels to satsify the iterative
 * implementation. Jar file executes the main class from here, as well as
 * Gradle
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @see Scanner
 * @see TaskList
 * @see Storage
 * @see Ui
 * @since 11
 */
public class Duke {
    private TaskList<Task> taskList = new TaskList<Task>();
    private Storage storage;
    private Ui ui;



    void moreOop() {
        storage = new Storage();
        storage.readFromFile();
        //Store byte array
        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        PrintStream oldPrintStream = System.out;
        System.setOut(printStream);
        //Instantiate taskList and storage
        this.taskList = storage.getTasks();
        storage.createDirectory();
        //Instantiate new UI
        ui = new Ui();
        ui.showWelcome();
        //Reset previous System
        System.out.flush();
        System.setOut(oldPrintStream);
        System.out.println(storeString.toString());
        //Set while loop flag
        boolean isExit = false;
        //Initalise while loop
        while (!isExit) {
            ui.readCommand();
            //Reset byte array
            storeString = new ByteArrayOutputStream();
            printStream = new PrintStream(storeString);
            System.setOut(printStream);
            //Custom duke commands
            taskList = ui.execute(taskList);
            //Reset System
            System.out.flush();
            System.setOut(oldPrintStream);
            System.out.println(storeString.toString());
            //Store file
            storage.writeToFile(taskList.toString());
        }
    }

    @FXML
    protected String getResponse(String input) {
        //return storeString.toString();

        storage = new Storage();
        storage.readFromFile();
        this.taskList = storage.getTasks();
        storage.createDirectory();

        TaskScheduler taskScheduler = new TaskScheduler(1);
        taskScheduler.addRecurringEvent();

        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        PrintStream oldPrintStream = System.out;
        System.setOut(printStream);

        /*
        ui = new Ui();
        */

        ui = new Ui(input);
        /*
        ui.showWelcome();
        Label greeting = new Label(storeString.toString());
        */
        //System.out.flush();

        //System.out.println(storeString.toString());
        //System.setOut(oldPrintStream);
//        storeString = new ByteArrayOutputStream();
//        printStream = new PrintStream(storeString);
//        System.setOut(printStream);


        taskList = ui.execute(taskList);

        System.out.flush();
        System.setOut(oldPrintStream);
        //System.out.println(storeString.toString());

        storage.writeToFile(taskList.toString());
        System.out.println("Store string " + storeString.toString());
        return storeString.toString();

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.moreOop();
    }

}
