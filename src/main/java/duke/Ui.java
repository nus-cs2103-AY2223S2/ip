package duke;

import task.Task;

/**
 * Class that consist of all Duke user interaction with the user.
 */
public class Ui {

    public static String getNoKeywordMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public static String getGoodbyeMessage() {
        return "Good Riddance!!!";
    }

    public static String getEmptyDetailsForToDoMessage() {
        return "EXCUSE ME!!!, 'ToDo' details cannot be empty";
    }

    public static String getEmptyDetailsForDeadlineMessage() {
        return "EXCUSE ME!!!, 'Deadline' details cannot be empty";
    }

    public static String getEventTaskFormat() {
        return "EXCUSE ME!!!, please follow the format"
                + "\nevent <details> /from dd/mm/yyyy /to dd/mm/yyyy";
    }

    public static String getSupportedDateFormat() {
        return "EXCUSE ME!!!, "
                + "please use the correct date format\n dd/mm/yyyy";
    }

    public static String getIntegerOutOfBoundsMessage() {
        return "Are you blind? That's not an option!!";
    }

    public static String getFileCorruptedMessage() {
        return "Too bad, file was corrupted";
    }

    public static String getDeleteMessageWithAttitudeOf(Task taskRemoved) {
        return "\nNoted. I've purged this task:\n  " + taskRemoved.getTaskDetails();
    }

    public static String getMarkMessageWithAttitude(Task markedTask) {
        return "\nOK, I've marked this task as done:\n  " + markedTask + "\n";
    }

    public static String getUnMarkMessageWithAttitude(Task unmarkedTask) {
        return "\nOK, I've marked this task as undone:\n  " + unmarkedTask.getName() + "\n";
    }

    public static String getAddTaskConfirmationWithAttitudeOf(Task taskAdded) {
        return "\nOkay... I've added this task:\n  " + taskAdded.getTaskDetails();
    }

    public static String getAllTaskFoundMessageWithAttitude(String allTaskFound) {
        if (allTaskFound.isBlank()) {
            return "DOES NOT EXIST";
        }
        return "\nHere you go:\n" + allTaskFound;
    }

    public static String getEmptyDetailsForEventMessage() {
        return "EXCUSE ME!!!, 'Deadline' details cannot be empty";
    }
}
