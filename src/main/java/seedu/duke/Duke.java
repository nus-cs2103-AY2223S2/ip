package seedu.duke;

/**
 * Represents the user input processing part of the application
 */
public class Duke {

    private static TaskList ls = new TaskList();

    /**
     * Constructor initializes application by loading an existing save file or
     * by creating a new save file
     */
    public Duke() {

        if (Storage.saveExists()) {
            ls = Storage.loadSave();
        } else {
            Storage.createSave();
            ls = new TaskList();
        }
    }

    /**
     * Gets response from UI and returns it to the application window
     * @param input User input
     * @return Response
     */
    public String getResponse(String input) {
        String response = Ui.respond(input, ls);
        System.out.println(response);
        return response;
    }
}
