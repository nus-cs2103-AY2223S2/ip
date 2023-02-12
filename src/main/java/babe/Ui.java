package babe;

import java.util.ArrayList;

/**
 * The <code>Ui</code> class deals with interactions with the user.
 * It contains all functions used to generate the GUI and the messages seen in Babe.
 */
class Ui {

    /**
     * Draws a horizontal line.
     */
    private static void drawLineHeader() {
        System.out.print("(ɔ˘ ³˘)ɔ: ");
    }

    protected static String notifyCreateSaveFile() {
        String output = "We can't find a save file for Babe, so we just went ahead and created one for you <3!";
        printOutputMessage(output);
        return output;
    }

    /**
     * Prints a welcome message for users of Babe.
     *
     * @return A String that is the welcome message.
     */
    protected static String welcomeUser() {
        String output = "HELLO! Greetings from Babe <3 How may I help you?";
        printOutputMessage(output);
        return output;
    }

    protected static String notifyAddTask(String taskString, int count) {
        String output = "";
        output += "Got it, babe. Added this for you:\n";
        output += taskString;
        output += String.format("\nNow you have %d task in the list.\n", count);
        printOutputMessage(output);
        return output;
    }

    /**
     * Prints list of Tasks stored in this Babe.
     * Prints a numbered list of Items stored in memory.
     */
    protected static String printList(TaskList taskList) {
        String output = "";
        if (taskList.isEmpty()) {
            output += "Nothing added yet. Add something babygorl.\n";
        } else {
            output += "This is your list so far:\n";
            output += taskList.toString();
        }
        printOutputMessage(output);
        return output;
    }

    private static void printOutputMessage(String output) {
        Ui.drawLineHeader();
        System.out.println(output);
    }

    protected static String sayBye() {
        String output = "Bye, babyboo. Can't wait to meet you again!";
        printOutputMessage(output);
        return output;
    }

    protected static String notifyDelete(ArrayList<String> taskStrings, int count) {
        String output = "";
        output += "Love that work ethics of yours! Here's what I removed from the list:\n";
        for (int i = 0; i < taskStrings.size(); i++) {
            output += taskStrings.get(i) + "\n";
        }
        output += String.format("\nNow you have %d task(s) left!\n", count);
        printOutputMessage(output);
        return output;
    }

    protected static String notifyException(Exception e) {
        String output = e.getMessage();
        printOutputMessage(output);
        return output;
    }

    protected static String notifyMark(String taskString) {
        String output = "Okay, babygorl. I've marked this as Done:\n";
        output += taskString;
        printOutputMessage(output);
        return output;
    }

    protected static String notifyUnmark(String taskString) {
        String output = "We have un-Done this for you:\n";
        output += taskString;
        printOutputMessage(output);
        return output;
    }

    protected static String notifyFindResults(ArrayList<String> tasks) {
        String output = "";
        if (tasks.size() == 0) {
            output += "Oh nuuu we can't find the task you are looking for. "
                    + "Perhaps you haven't added the item yet?\n"
                    + "Try 'list' command to check!";

        } else {
            output += "Here's what we could find:";
            for (int i = 0; i < tasks.size(); i++) {
                output += String.format("\n%d. ", i + 1);
                output += tasks.get(i);
            }
        }
        printOutputMessage(output);
        return output;
    }

    protected static String notifyError(AssertionError e2) {
        String output = e2.getMessage();
        printOutputMessage(output);
        return output;
    }

}
