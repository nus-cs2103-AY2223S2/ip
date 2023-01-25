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
        return reply.split("/",2);
    }

    /**
     * Splits the sentence for Event into 2
     * @param reply
     * @return an array that contains words that have been split
     */
    public static String[] splitForEvent(String reply) {
        return reply.split("/",2);
    }
}
