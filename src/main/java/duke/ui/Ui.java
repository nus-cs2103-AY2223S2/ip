package duke.ui;

/**
 * Represents the UI of the bot.
 */
public class Ui {
    private String botName;

    /**
     * Constructor for a UI that handle forming bot responses.
     *
     * @param name Name of chatbot.
     */
    public Ui(String name) {
        this.botName = name;
    }

    /**
     * Creates a response message.
     * @param s Message.
     * @return String representing Duke's response.
     */
    public String formResponse(String s) {
        return s;
    }

    /**
     * Returns a welcome message.
     * @return String representing welcome message.
     */
    public String sayHello() {
        return this.formResponse("LEBRON JAMESSS #kidfromAkron #kingme");
    }

    /**
     * Returns a terminating message.
     * @return String representing goodbye message.
     */
    public String sayGoodbye() {
        return this.formResponse("Not staying for Taco Tuesday? :<");
    }

    /**
     * Prints the string on the output screen.
     * @param s String to print.
     */
    public void print(String s) {
        System.out.println("> " + s);
    }

    public void printFileSaved() {
        this.print("File saved.");
    }

    public void printFileSaveError() {
        this.print("Something went wrong while saving file.");
    }

    public void printFileNotFoundError() {
        this.print("File not found.");
    }
}
