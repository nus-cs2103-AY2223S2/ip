package sebastian.main;

/**
 * A class in charge of interacting with the user
 */
public class Ui {

    /**
     * A customised space
     * @return the customised space as a String
     */
    public static String space() {
        String space = " ";
        return space.repeat(2);
    }

    /**
     * The message to be sent when Sebastian first runs
     * @return greeting message together with the user manual
     */
    public String getGreeting() {
        String res = "Greetings, I'm Sebastian." + getUserGuide();
        return getFormattedString(res);
    }

    /**
     * Echo whatever the user has typed in
     * @param instruction the user input
     * @return the same user input
     */
    public String echo(String instruction) {
        return space() + instruction;
    }

    /**
     * Retrieve error occurred during the session in customised format
     * @param errorMessage the error message of the error
     */
    public String getError(String errorMessage) {
        return this.getFormattedString(errorMessage);
    }

    /**
     * Re-format a string into customised style
     * @param str the string to be formatted
     * @return the formatted string
     */
    public String getFormattedString(String str) {
        return formatString(str);
    }

    /**
     * To format a String
     * @param str the String to be formatted
     * @return the formatted String
     */
    private static String formatString(String str) {
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i == 0 && lines.length > 1) {
                sb.append(lines[i]).append("\n");
            } else if (i == lines.length - 1) {
                sb.append(lines[i]);
            } else {
                sb.append(space()).append(lines[i]).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * A user guide to be printed at the start of each session
     * @return user guide
     */
    private String getUserGuide() {
        String guide = "Here are the commands you can give me: \n"
                + "1. todo [a todo task]\n" + space() + "-- to add a todo to your task list\n"
                + "2. deadline [a deadline] /by yyyy-MM-dd HHmm\n" + space() + "-- to add a deadline to you task list\n"
                + "3. event [an event] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n" + space()
                + "-- to add an event to you task list\n"
                + "4. mark [task index]\n" + space() + "-- to mark a task as done\n"
                + "5. unmark [task index]\n" + space() + "-- to mark a task as not done\n"
                + "6. list\n" + space() + "-- to show the tasks on the task list\n"
                + "7. delete [task index]\n" + space() + "-- to delete a task from the task list\n"
                + "8. get yyyy-MM-dd\n" + space() + "-- to retrieve the tasks on a specific date\n"
                + "9. bye\n" + space() + "-- to exit the session\n"
                + "10. find [keyword]\n" + space() + "-- find tasks containing the keyword\n"
                + "11. update [task index]\n"
                + space() + "/desc [new description]\n"
                + space() + "/by [new deadline due date]\n"
                + space() + "/from [new event start time\n"
                + space() + "/to [new end time for event]\n"
                + space() + "-- to update details of your task with flags\n"
                + "How may I assist you today?";
        return guide;
    }
}
