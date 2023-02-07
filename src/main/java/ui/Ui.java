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
        String str = "Hi~ I'm duke.Duke>_<\nWhat can I do for you?";
        return str;
    }

    /**
     * Puts the input string between 2 separating lines.
     *
     * @param str The input string.
     * @return New string with separating lines.
     */
    public String separate(String str) {
        String sep_line = "---------------------------------------------------------------";
        return sep_line + "\n" + str + "\n" + sep_line;
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
