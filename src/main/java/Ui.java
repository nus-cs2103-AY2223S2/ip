public class Ui {

    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    public void printOutput(String output) {
        System.out.println(formatOutput(output));
    }

    public void showGreeting() {
        printOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D");
    }

    public void showBye() {
        printOutput("Well, I'm off! Hope to see you again soon :)");
    }

    public void showExceptionError(Exception e) {
        printOutput(e.getMessage());
    }

    public void showDateTimeParseError() {
        printOutput("Woah.. Error parsing date time for deadline task. Please enter the by datetime in this format yyyy-mm-dd (e.g., 2023-10-15).");
    }

    public void showLoadingError() {
        printOutput("No initial data found. I'll be generating a new task list for you..");
    }

    public void showSavingError() {
        printOutput("Uh oh... An error occurred while I was trying to save your latest task list :0");
    }
}
