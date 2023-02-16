package duke;

/**
 * This is Duke who initialises Joe the Java Bot!
 */
public class Duke {

    private static Joe joe;

    public Duke() {
        joe = new Joe();
    }

    /**
     * Gets Joe to handle the input.
     *
     * @param input Input to handle.
     * @return String Joe's response.
     */
    static String handleResponse(String input) {
        return joe.handleResponse(input);
    }

    /**
     * Runs Joe, my java bot!
     *
     * @param args Input arguments, will be ignored.
     */
    public static void main(String[] args) {
        String logo = "\t     |             \n"
            + "\t     |  _ \\    _ \\ \n"
            + "\t \\   | (   |   __/ \n"
            + "\t\\___/ \\___/  \\___|\n";

        String greeting = "\tHello! I'm Joe\n\tWhat Can I do for you?";

        Ui.show("Hello from\n" + logo);
        Ui.show(greeting);

        while (true) {
            String output = handleResponse(Ui.nextLine());
            if (output == null) {
                Ui.show("Are you sure you inputted a correct response?");
                continue;
            }
            Ui.show(output);
            if (output.equals("\tBye. Hope to see you again soon!")) {
                System.exit(0);
            }
        }
    }

}
