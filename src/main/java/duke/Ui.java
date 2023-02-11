package duke;

public class Ui {
    public static final String noKeywordMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String goodbyeMessage = "Good Riddance!!!";
    public static final String emptyDetailsForToDoMessage = "EXCUSE ME!!!, 'ToDo' details cannot be empty";
    public static final String emptyDetailsForDeadlineMessage = "EXCUSE ME!!!, " +
            "'Deadline' details cannot be empty";
    public static final String emptyDetailsForEventMessage = "EXCUSE ME!!!, 'Event' details cannot be empty";
    public static final String eventTaskFormat = "EXCUSE ME!!!, please follow the format" +
            "\nevent <details> /from dd/mm/yyyy /to dd/mm/yyyy";
    public static final String supportedDateFormat = "EXCUSE ME!!!, " +
            "please use the correct date format\n dd/mm/yyyy";

    public static String getDeleteMessageWithAttitude(String taskDetails) {
        return "\nNoted. I've purged this task:\n  " + taskDetails;
    }

    public static String getMarkMessageWithAttitude(String taskDetails) {
        return "\nOK, I've marked this task as done:\n  " + taskDetails + "\n";
    }

    public static String getUnMarkMessageWithAttitude(String taskDetails) {
        return "\nOK, I've marked this task as undone:\n  " + taskDetails + "\n";
    }

    public static String getAddTaskConfirmationWithAttitude(String taskDetails) {
        return "\nOkay... I've added this task:\n  " + taskDetails;
    }

    public static String getAllTaskFoundMessageWithAttitude(String allTaskFound) {
        if (allTaskFound.isBlank()) {
            return "DOES NOT EXIST";
        }
        return "\nHere you go:\n" + allTaskFound;
    }
}