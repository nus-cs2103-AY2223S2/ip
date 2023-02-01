package babe;

import babe.task.Task;

/**
 * This class deals with interactions with the user
 */
class Ui {

    /**
     * Draws a horizontal line.
     */
    private static void drawLineHeader() {
        System.out.print("(ɔ˘ ³˘)ɔ: ");
    }

    protected static void notifyCreateSaveFile() {
        Ui.drawLineHeader();
        System.out.println("We can't find a save file for Babe, so we just went ahead and created one for you <3!");
        Ui.drawLineHeader();
    }

    /**
     * Welcome users of Babe.
     * Greets user and prompts for help. This method does not expect a response.
     */
    protected static void welcomeUser() {
        Ui.drawLineHeader();
        System.out.println("HELLO! Greetings from Babe <3 How may I help you?");
    }

    protected static void notifyAddTask(Task item, int count) {
        Ui.drawLineHeader();
        System.out.println("Got it, babe. Added this for you:");
        System.out.println(item.toString());
        System.out.printf("Now you have %d task in the list.\n", count);
    }

    /**
     * Prints list of Tasks stored in this Babe.
     * Prints a numbered list of Items stored in memory.
     */
    protected static void printList(TaskList taskList) {
        Ui.drawLineHeader();
        if (taskList.isEmpty()) {
            System.out.println("Nothing added yet. Add something babygorl.");
        } else {
            Ui.drawLineHeader();
            System.out.println("This is your list so far:");
            System.out.println(taskList.toString());
        }
    }

    /**
     * Bids farewell to the user.
     * Prints a line of farewell before ending the program.
     */
    protected static void sayBye() {
        Ui.drawLineHeader();
        System.out.println("Bye, babyboo. Can't wait to meet you again!");
    }


    protected static void notifyDelete(Task item, int count) {
        Ui.drawLineHeader();
        System.out.println("One task down! I removed this from your list of tasks:");
        System.out.println(item.toString());
        System.out.printf("Now you have %d task(s) left!\n", count);
    }

    protected static void notifyException(Exception e) {
        Ui.drawLineHeader();
        System.out.println(e.getMessage());
    }

    protected static void notifyStatusChanged(Task task, boolean toMark) {
        Ui.drawLineHeader();
        System.out.println(toMark ? "Okay, babygorl. I've marked this as Done:" : "We have un-Done this for you:");
        System.out.println(task.toString());
    }


}
