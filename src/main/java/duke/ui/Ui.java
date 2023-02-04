package duke.ui;

/**
 * Represents the UI of the bot.
 */
public class Ui {
    private String botName;

    /**
     * Constructor for UI.
     *
     * @param name Name of chatbot.
     */
    public Ui(String name) {
        this.botName = name;
    }

    /**
     * Creates a decorated response message.
     * @param s Message.
     * @return String representing Duke's response.
     */
    public String formResponse(String s) {
        return s;
    }

    /**
     * Shows welcome message.
     * @return String representing welcome message.
     */
    public String showWelcome() {
        return this.formResponse(botName + " activated.");
    }

    /**
     * Displays terminating message and closes the scanner.
     * @return String representing goodbye message.
     */
    public String terminate() {
        return this.formResponse("Not staying for Taco Tuesday? :<");
    }

    public void printSaveNotFound() {
        this.print("An error occurred while locating save file.");
    }

    public void printFileCreatedSuccessfully() {
        this.print("New save file created.");
    }

    public void printCreatingFile() {
        this.print("Save file detected. Loading...");
    }

    public void printSavingFile() {
        this.print("Saving file...");
    }

    public void printSavingFileError() {
        this.print("Something went wrong with saving file.");
    }

    public void print(String s) {
        System.out.println(s);
    }

}
