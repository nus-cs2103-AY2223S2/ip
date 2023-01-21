package formatters;

/**
 * Formats the default responses for UwU_TaskMaster
 * @author Nicholas Lee
 */

public class Format {

    public static String getCompletionDisplay(boolean isCompleted) {
        String completionDisplay;
        if (isCompleted) {
            completionDisplay = "[X]";
        } else {
            completionDisplay = "[ ]";
        }
        return completionDisplay;
    }

    public static String formatResponse(String output) {
        return "\nUwU_TaskMaster ＵｗＵ: " + output;
    }


}
