package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Parser parser;
    
    public Ui(Parser parser) {
        this.parser = parser;
    }

     /**
     * Receive command given by the user and pass to parser to run the command
     * 
     * @param taskList Arraylist containing task objects
     * @param storage Storage class that manages save and loading
     * @return return True if command is bye, otherwise return false
     */
    public boolean receiveInput(TaskList taskList, Storage storage) throws IOException {
        String[] words;
        boolean isCommandBye = false;
        System.out.print("Type your input below: \n");
        words = br.readLine().split(" ");

        try { 
            isCommandBye = parser.runCommand(taskList, storage, words);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Number out of range/empty. Please try again!");
        } catch(NumberFormatException e) {
            System.out.println("Invalid number. Please enter a number!");
        } catch(InvalidCommandException e) {
            System.out.println("Invalid command. Pleas try again!");
        } 
        horizontalLine();
        return isCommandBye;
    }


    /**
     * Print a double horizontal line
     */ 
    private static void horizontalLine() { 
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }
}
