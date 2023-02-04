package duke.ui;

import java.util.Scanner;

/**
 * Represents the UI of the bot.
 */
public class Ui {
    private String botName;
    private Scanner sc;

    /**
     * Constructor for UI.
     *
     * @param name Name of chatbot.
     */
    public Ui(String name) {
        this.botName = name;
        this.sc = new Scanner(System.in);
    }

    /**
     * Creates a decorated response message with self-adjusting width
     * to match message length.
     * @param s Message.
     */
    public String formResponse(String s) {
        return s;
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        this.formResponse(botName + "Bot activated.");
    }

    /**
     * Reads user input and returns it.
     * @return User's input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays terminating message and closes the scanner.
     */
    public void terminate() {
        this.formResponse("Bot terminated.");
        this.sc.close();
    }

    public void showSaveNotFound() {
        this.formResponse("An error occurred while locating save file.");
    }

    public void showFileCreatedSuccessfully() {
        this.formResponse("New save file created.");
    }

    public void showCreatingFile() {
        this.formResponse("Save file detected. Loading...");
    }

    public void showSavingFile() {
        this.formResponse("Saving file...");
    }

    public void showError(String s) {
        this.formResponse(s);
    }

}
