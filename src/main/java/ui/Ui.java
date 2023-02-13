package ui;

/**
 * The Ui that processes user input and replies the user.
 */
public class Ui {
    /**
     * Greets the user when Duke is started.
     *
     * @return Greeting sentences.
     */
    public String greeting() {
        return "Hi~ I'm duke.Duke>_<\nWhat can I do for you?";

    }

    /**
     * Puts the input string between 2 separating lines.
     *
     * @param str The input string.
     * @return New string with separating lines.
     */
    public String separate(String str) {
        String seperatingLine = "---------------------------------------------------------------";
        return seperatingLine + "\n" + str + "\n" + seperatingLine;
    }

    /**
     * Says goodbye to the user.
     *
     * @return Message of goodbye.
     */
    public String ending() {
        return "Bye~ Hope to see you again soon:)";
    }

}
