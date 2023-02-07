package duke.helper;

public class Ui {
    /**
     * helper method to print proper indentation.
     *
     * @param toPrint the message to be printed.
     */
    public String print(String toPrint) {
        String line = "____________________________________________________________\n";
        return line + " " + toPrint + line;
    }

    public String printError(Exception e) {
        return print(e.getMessage());
    }

    public String printGreeting() {
        return print("Nyahello! I'm Nyako!\n What can I do for you nya?\n");
    }

    public String printBye() {
        return print("Bye bye nya!\n");
    }
}
