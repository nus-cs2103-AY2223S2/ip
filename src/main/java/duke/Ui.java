package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Parser parser;
    private MainWindow mainWindow;

    /**
     * Constructs a new Ui instance
     * 
     * @param parser Parser instance
     * @param mainWindow Controller for MainWindow
     */
    public Ui(Parser parser, MainWindow mainWindow) {
        this.parser = parser;
        this.mainWindow = mainWindow;
    }

    /**
     * Receives command given by the user and pass to parser to run the command
     * 
     * @param tasks Arraylist containing task objects
     * @param storage Storage class that manages save and loading
     * @param input Input text from user
     * @return true if command is bye, otherwise return false
     */
    public boolean receiveInput(TaskList tasks, Storage storage, String input) {
        String[] words;
        boolean isCommandBye = false;
        words = input.split(" ");

        try { 
            isCommandBye = parser.runCommand(tasks, storage, words);
        } catch (IndexOutOfBoundsException e) {
            mainWindow.sendDukeResponse("Number out of range/Input empty. Please try again!");
        } catch (NumberFormatException e) {
            mainWindow.sendDukeResponse("Invalid number. Please enter a number!");
        } catch (InvalidCommandException e) {
            mainWindow.sendDukeResponse(e.getMessage());
        } catch (EmptyDescriptionException e) {
            mainWindow.sendDukeResponse(e.getMessage());
        }
        return isCommandBye;
    }
}
