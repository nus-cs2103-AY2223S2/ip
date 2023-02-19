package duke;

import task.Task;

/**
 * Class that consist of all Duke user interaction with the user.
 */
public class Ui {

    public static String getNoKeywordMessage() {
        return "No idea what you are talking about";
    }

    public static String getGoodbyeMessage() {
        return "Good Riddance!!!";
    }

    public static String getEmptyDetailsForToDoMessage() {
        return "EXCUSE ME!!!, 'ToDo' details cannot be empty";
    }

    public static String getEmptyDetailsForDeadlineMessage() {
        return "EXCUSE ME!!!, 'Deadline' details cannot be empty"
                + "\ndeadline <details> /by dd/mm/yyyy";
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
        return "\nOK, I've marked this task as undone:\n  " + unmarkedTask + "\n";
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
        return "EXCUSE ME!!!, 'Deadline' details cannot be empty"
                + "\nevent <details> /from dd/mm/yyyy /to dd/mm/yyyy";
    }

    public static String getInvalidPriorityMessage() {
        return "Please follow the relevant format priority index h/m/l";
    }

    public static String setToHighPriorityMessage(Task setTask) {
        return setTask.getName() + " has been set to high priority";
    }

    public static String setToMidPriorityMessage(Task setTask) {
        return setTask.getName() + " has been set to mid priority";
    }

    public static String setToLowPriorityMessage(Task setTask) {
        return setTask.getName() + " has been set to low priority";
    }

    public static String getEmptyStringResponse() {
        return "I've got nothing.";
    }
}
