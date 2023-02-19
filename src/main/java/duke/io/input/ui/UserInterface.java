package duke.io.input.ui;

import java.util.Arrays;
import java.util.List;

import duke.util.Task;
import duke.util.TaskList;

/**
 * A class used to print output to users
 * Worked with both the CLI and GUI versions of Duke
 */

public class UserInterface {

    /**
     * Print Duke's logo for CLI version
     */

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("_".repeat(22));
        System.out.println("Hello from\n" + logo);
        System.out.println("_".repeat(22));
        System.out.println("");
    }

    /**
     * Print Duke's logo for GUI version
     */

    public static String printGuiLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return printWithBracket(logo);
    }

    /**
     * Print warning for incorrect input
     * when the user just opened Duke
     */

    public static void printInputWarning() {
        System.out.println(UserInterface.printWithBracket("FOCUS, HUMAN. "
                + "YOU ARE TO ENTER INPUT WITH FULL CAPS."));
    }

    /**
     * Print warning for incorrect input
     * when the user input is invalid and an exception is raised
     */

    public static void printInputWarning(Exception exception) {
        System.out.println(exception.toString());
    }

    /**
     * Print specified warning for incorrect input
     *
     * @param warning the warning to be printed
     */

    public static void printInputWarning(String warning) {
        System.out.println(warning);
    }


    /**
     * Print a {@code String} that is framed in a stylized box.
     * The length of the box is based on the length of the input string.
     * To be used with CLI version of Duke
     *
     * @param input the {@code String} to be printed
     * @return a stylized version of the {@code String}, framed in a box
     */

    public static String printWithBracket(String input) {
        String toPrintOut = "";
        if (input.contains("\n")) {
            String[] inputSplitArray = input.split("\n");
            List<String> inputSplitList = Arrays.asList(inputSplitArray);
            int max = 0;
            for (int i = 0; i < inputSplitList.size(); i++) {
                if (inputSplitList.get(i).length() > max) {
                    max = inputSplitList.get(i).length();
                }
            }
            toPrintOut += "_".repeat(max) + "\n";
            toPrintOut += input + "\n";
            toPrintOut += "_".repeat(max);
        } else {
            int size = input.length();
            toPrintOut += "_".repeat(size) + "\n";
            toPrintOut += input + "\n";
            toPrintOut += "_".repeat(size);
        }
        return toPrintOut;
    }

    /**
     * Greet the user when he opens Duke.
     * Works with both CLI and GUI versions of Duke
     *
     * @param firsTimeUsingDuke whether the user used Duke for the first time.
     *                          Determined by the current number of tasks
     * @return Duke's greeting
     */

    public static String welcomeUser(boolean firsTimeUsingDuke) {
        if (firsTimeUsingDuke) {
            return "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?";
        } else {
            return "WELCOME BACK... WHAT'S YOUR PLAN TODAY...";
        }
    }

    /**
     * Output the notice to user when a {@code task} is marked/ unmarked
     * Works with both CLI and GUI versions of Duke
     *
     * @param command the user's previous command, showing the task he wants to mark
     * @param markedTask the {@code Task} that is marked/ unmaked
     *
     * @return Duke's response
     */

    public static String printMarkedUnmarkedTask(String command, Task markedTask) {
        String toPrintOut = "";
        if (command.equals("MARK")) {
            toPrintOut += "MARKED. ONE STEP CLOSER..." + '\n';
            toPrintOut += markedTask.toString() + '\n';
        } else {
            toPrintOut += "HAVING OTHER PLANS I SEE..." + '\n';
        }
        return toPrintOut;
    }

    /**
     * Output the notice to user when a {@code task} is deleted
     * Works with both CLI and GUI versions of Duke
     *
     * @param numOfTasksRemaining the number of remaining tasks after deletion
     * @param deletedTask the {@code Task} that is deleted
     *
     * @return Duke's response
     */

    public static String printDeletedTask(int numOfTasksRemaining, Task deletedTask) {
        String toPrintOut = "KABOOM. GONE. REDUCED TO ATOMS. HOW EXCITING!" + '\n';
        toPrintOut += '\n' + "TASK " + deletedTask.toString() + " NO LONGER EXISTED" + '\n';
        if (numOfTasksRemaining > 1) {
            toPrintOut += numOfTasksRemaining + " TASKS LEFT. BETTER HURRY." + '\n';
        } else {
            toPrintOut += "ONLY " + numOfTasksRemaining
                    + " TASK LEFT. BORING DAYS AHEAD." + '\n';
        }
        return toPrintOut;
    }

    /**
     * Output the list of tasks that contains a specified keyword
     *
     * @param foundListFromKeyword the list of tasks that contains a specified keyword
     *
     * @return Duke's response
     */

    public static String printSearchedTask(TaskList foundListFromKeyword) {
        String toPrintOut = "YOU'RE STARTING TO FORGET..." + '\n';
        int numberOfTasksFound = foundListFromKeyword.getSize();
        if (numberOfTasksFound == 0) {
            toPrintOut += "THIS WAS NEVER IN YOUR PLANS";
        } else {
            toPrintOut += '\n' + "LET ME REMIND YOU OF WHAT YOU STARTED" + '\n';
            for (int i = 0; i < numberOfTasksFound; i++) {
                toPrintOut += foundListFromKeyword.getTask(i).toString() + '\n';
            }
        }
        return toPrintOut;
    }

    /**
     * Output the notice to user when a {@code task} is added
     * Works with both CLI and GUI versions of Duke
     *
     * @param userCommand the user's previous command, showing the task he wants to add
     * @param numberOfTasks the current number of tasks after addition
     *
     * @return Duke's response
     */

    public static String printAddedTask(String userCommand, int numberOfTasks) {
        if (!userCommand.equals("")) {
            String toPrintOut = "SO YOU WANT TO ADD " + '"' + userCommand + '"' + ". VERY WELL..." + '\n';
            toPrintOut += '\n' + "ADDED: " + userCommand + '\n';
            if (numberOfTasks > 1) {
                toPrintOut += numberOfTasks + " TASKS. BETTER HURRY." + '\n';
            } else {
                toPrintOut += "ONLY " + numberOfTasks + " TASK. BORING DAYS AHEAD." + '\n';
            }
            toPrintOut += '\n' + "WHAT ELSE?";
            return toPrintOut;
        } else {
            return "";
        }
    }
}
