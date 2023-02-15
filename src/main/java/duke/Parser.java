package duke;

/**
 * This is a class that helps to split the arguments such that they can be initiated
 */
public class Parser {
    /**
     * Splits the sentence for Deadline into 2
     * @param reply
     * @return an array that contains words that have been split
     */
    public static String[] splitForDeadline(String reply) {
        String[] replies = reply.split("/",2);
        InvalidArgsHandler checked = new InvalidArgsHandler(replies);
        checked.checkForDeadline(checked.replies);
        return replies;
    }

    /**
     * Return string representation of date for Event
     * @param dateCheck
     * @return
     */
    public static String stringForEvent(String[] dateCheck) {
        dateCheck[0] = dateCheck[0].replaceAll("from ", "");
        dateCheck[1] = dateCheck[1].replaceAll("to ","");
        return dateCheck[0] + "/" + dateCheck[1];
    }

    /**
     * Splits the sentence for Event into 2
     * @param reply
     * @return an array that contains words that have been split
     */
    public static String[] splitForEvent(String reply) {
        String[] replies = reply.split("/",2);
        replies[1] = stringForEvent(replies[1].split("/"));
        InvalidArgsHandler checked = new InvalidArgsHandler(replies);
        checked.checkForEvent(checked.replies);
        return replies;
    }
}
