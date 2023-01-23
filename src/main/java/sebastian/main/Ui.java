package sebastian.main;

import java.util.Scanner;

public class Ui {

    private static Scanner scan = new Scanner(System.in);

    public static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

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

    public String readCommand() {
        return scan.nextLine();
    }

    public void showError(String errorMessage) {
        this.printFormattedString(errorMessage);
    }

    public void printFormattedString(String str) {
        lineBreak();
        System.out.println(formatString(str));
        lineBreak();
    }

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
                "10. find [keyword] -- find tasks containing the keyword\n" +
                "You can start giving commands below";
        return guide;
    }
}
