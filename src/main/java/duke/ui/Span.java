package duke.ui;

/**
 * A class to format responses by Duke chatbot.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Span {
    /**
     * Static method to format any string response to be shown to the user.
     * @param s The response to be shown to the user.
     * @return String formatted for better display.
     */
    public static String format(String s) {
        String[] sp = s.split("\n");
        s = String.join("\n\t", sp);
        return String.format("\t___________________________________________________________________________\n"
                + "\t%s\n"
                + "\t___________________________________________________________________________", s);
    }
}
