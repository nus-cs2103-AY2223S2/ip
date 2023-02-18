package duke.io.input.ui;

import duke.util.Task;
import duke.util.TaskList;

import java.util.Arrays;
import java.util.List;

public class UserInterface {
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

    public static String printGuiLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return printWithBracket(logo);
    }

    public static void printInputWarning() {
        System.out.println(UserInterface.printWithBracket("FOCUS, HUMAN. "
                + "YOU ARE TO ENTER INPUT WITH FULL CAPS."));
    }

    public static void printInputWarning(Exception exception) {
        System.out.println(exception.toString());
    }

    public static void printInputWarning(String warning) {
        System.out.println(warning);
    }

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
            toPrintOut += "_".repeat(max) ;
        } else {
            int size = input.length();
            toPrintOut += "_".repeat(size) + "\n";
            toPrintOut += input + "\n";
            toPrintOut += "_".repeat(size);
        }
        return toPrintOut;
    }

    public static String welcomeUser(boolean firsTimeUsingDuke) {
        if (firsTimeUsingDuke) {
            return "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?";
        } else {
            return "WELCOME BACK... WHAT'S YOUR PLAN TODAY...";
        }
    }

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