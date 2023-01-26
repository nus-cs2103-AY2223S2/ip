package duke;

/**
 * <h1>Duke task checklist</h1>
 * The Duke program helps keep track of your ongoin task.
 * <p>
 *
 * @author Chin Jun An
 * @version 1.0
 * @since 2023
 */
public class Duke {
    /**
     * Represents a Duke program.
     */
    private Art ar;
    private Storage st;
    private Functions fn;
    private UI ui;

    /**
     * Constructor for a Duke instance. Load tasks previously saved.
     *
     * @param fp Indicate the file path to save task scheduled
     */
    public Duke(String fp) {
        this.ar = new Art();
        try {
            st = new Storage(fp);
            TaskList tl = st.load();
            this.fn = new Functions(tl, st);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.ui = new UI(fn);
    }

    /**
     * Main method to run Duke from the console
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Method to run the Duke program
     */
    public void run() {
        ar.show();
        boolean flag = true;
        while (flag) {
            ui.getInput();
            //could use enums here to check user input before going into switch case
            try {
                flag = ui.action();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input...");
            }
        }
    }
}
