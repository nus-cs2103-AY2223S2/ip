package cluck;

import java.io.File;

import cluck.commands.Command;
import cluck.exceptions.CluckException;
import cluck.parser.Parser;
import cluck.taskList.TaskList;
import cluck.ui.Ui;



public class Cluck {
    private static final String SAVE_DIR_STRING = "SavedData";
    private static final String SAVE_FILE_STRING = "CluckSave.txt";

    private TaskList taskList;
    private Ui ui;
    private boolean isRunning = true;

    /**
     * @param strNum String of interest.
     * @return returns true if strNum is a number in string format, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Cluck class contains tasklist, User interface and parser needed.
     */
    public Cluck() {
        File savedFile = new File(SAVE_FILE_STRING);
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    /**
     * Starts cluck instance such that it loads saved data and
     * begins taking in user commands.
     */
    public void start() {
        ui.greetUser();
        String userInput;
        String response;
        Command currCommand;
        while (this.isRunning) {
            userInput = ui.readInput();
            try {
                currCommand = Parser.commandFactory(userInput);
                response = currCommand.execute(taskList);
            } catch (CluckException e) {
                response = e.getMessage();
            }

            ui.printResponse(response);
        }
    }

    /**
     * Saves taskList and stop cluck instance
     */
    public void stop() {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {


    }
}
