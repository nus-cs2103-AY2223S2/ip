package duke;

import java.util.Scanner;

/**
 * The main class of the Duke Task Manager application.
 * This class is responsible for starting the application, 
 * initializing the task list, and handling user inputs.
 * @author @tricixg
 * @version 1.0
 */
public class Duke {
 
    /**
     * The main method of the application.
     * This method initializes the task list, loads tasks from the saved file,
     * greets the user, and handles user inputs.
     * @param args
     */
    public static void main(String[] args) {
        TaskList list = new TaskList();
        try {
            Storage.loadFile(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Ui.greet();
        Scanner sc = new Scanner(System.in);
        

        while (true) {
            String userInput = sc.nextLine();
            String[] splitInput = userInput.split("\\s+");
            Parser.parseInput(splitInput);
            try {
                Storage.saveToFile(list);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}



