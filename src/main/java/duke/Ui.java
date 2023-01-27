package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Deals with interactions with the user
 */
public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Parser parser;

    /**
     * Constructs a new Ui instance
     * 
     * @param parser Parser instance
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

     /**
     * Receives command given by the user and pass to parser to run the command
     * 
     * @param tasks Arraylist containing task objects
     * @param storage Storage class that manages save and loading
     * @return true if command is bye, otherwise return false
     * @throws IOException If an I/O error occurs
     */
    public boolean receiveInput(TaskList tasks, Storage storage) throws IOException {
        String[] words;
        boolean isCommandBye = false;
        System.out.print("Type your input below: \n");
        words = br.readLine().split(" ");

        try { 
            isCommandBye = parser.runCommand(tasks, storage, words);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Number out of range/empty. Please try again!");
        } catch(NumberFormatException e) {
            System.out.println("Invalid number. Please enter a number!");
        } catch(InvalidCommandException e) {
            System.out.println(e.getMessage());
        } 
        horizontalLine();
        return isCommandBye;
    }


    /**
     * Prints a double horizontal line
     */ 
    private static void horizontalLine() { 
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
}
