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
        String seperateLine = "---------------------------------------------------------------";
        return seperateLine + "\n" + str + "\n" + seperateLine;
    }

    /**
     * Says goodbye to the user.
     *
     * @return Message of goodbye.
     */
    public String ending() {
        String str = "Bye~ Hope to see you again soon:)";
        return separate(str);
    }

}
