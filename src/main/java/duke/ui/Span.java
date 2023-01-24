package duke.ui;

public class Span {
    public static String format(String s) {
        String[] sp = s.split("\n");
        s = String.join("\n\t", sp);
        return String.format("\t___________________________________________________________________________\n" +
                "\t%s\n" +
                "\t___________________________________________________________________________", s);
    }
}