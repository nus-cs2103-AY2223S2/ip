package ui;

public class Ui {
    public String greeting() {
        String str = "Hi~ I'm duke.Duke>_<\nWhat can I do for you?";
        return str;
    }

    public String separate(String str) {
        String sep_line = "---------------------------------------------------------------";
        return sep_line + "\n" + str + "\n" + sep_line;
    }

    public String ending() {
        String str = "Bye~ Hope to see you again soon:)";
        return separate(str);
    }

}
