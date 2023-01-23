package sebastian.main;

import java.util.Scanner;

/**
 * A class in charge of interacting with the user
 */
public class Ui {

    private static Scanner scan = new Scanner(System.in);

    /**
     * Print a single line break ---
     */
    public static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

    /**
     * A customised space
     * @return the customised space as a String
     */
    public static String space() {
        String space = " ";
        return space.repeat(5);
    }

    public void greet() {
        String res = "Greetings, I'm Sebastian. " + getUserGuide();
        this.printFormattedString(res);
    }

    /**
     * Echo whatever the user has typed in
     * @param instruction the user input
     * @return the same user input
     */
    public String echo(String instruction){
        return space() + instruction;
    }

    /**
     * Read in and return user command
     * @return user command
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Print error occurred during the session in customised format
     * @param errorMessage the error message of the error
     */
    public void showError(String errorMessage) {
        this.printFormattedString(errorMessage);
    }

    /**
     * Print a string in customised format
     * @param str the string to be printed in customised format
     */
    public void printFormattedString(String str) {
        lineBreak();
        System.out.println(formatString(str));
        lineBreak();
    }

    /**
     * To format a String
     * @param str the String to be formatted
     * @return the formatted String
     */
    private static String formatString(String str){
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lines.length; i++) {
            if(i == 0 && lines.length > 1) {
                sb.append(space()).append(lines[i]).append("\n") ;
            } else if (i == lines.length - 1) {
                sb.append(space()).append(lines[i]);
            } else {
                sb.append(space()).append(space()).append(lines[i]).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * A user guide to be printed at the start of each session
     * @return user guide
     */
    private String getUserGuide() {
        String guide = "Here are the commands you can give me: \n" +
                "1. todo [a todo task] -- to add a todo to your task list\n" +
                "2. deadline [a deadline] /by yyyy-MM-dd HHmm -- to add a deadline to you task list\n" +
                "3. event [an event] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm -- to add an event to you task list\n" +
                "4. mark [task index] -- to mark a task as done\n" +
                "5. unmark [task index] -- to mark a task as not done\n" +
                "6. list -- to show the tasks on the task list\n" +
                "7. delete [task index] -- to delete a task from the task list\n" +
                "8. get yyyy-MM-dd -- to retrieve the tasks on a specific date\n" +
                "9. bye -- to exit the session\n" +
                "You can start giving commands below";
        return guide;
    }
}
