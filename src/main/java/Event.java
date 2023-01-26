import java.io.File;
import java.util.Scanner;

public class Event extends Task{
    private static StringBuilder strBuild = new StringBuilder();
    private static Scanner sc = new Scanner(System.in);
    private final String start;
    private final String end;

    public Event(String name, String start, String end, boolean done) {
        super(name, done);
        this.start = start;
        this.end = end;
    }

    public static void createEvent(String[] split) {

        boolean isName = true;
        boolean isStart = false;
        boolean isEnd = false;
        String n = " ", s = " ", e = " ";
        for (int i = 1; i < split.length; i++) {
            if (isName) {
                if (!split[i].equalsIgnoreCase("/from")) {
                    strBuild.append(split[i]);
                } else {
                    isStart = true;
                    n = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                }
            } else if (isStart) {
                if (!split[i].equalsIgnoreCase("/to")) {
                    strBuild.append(split[i]);
                } else {
                    isEnd = true;
                    s = strBuild.toString();
                    strBuild.setLength(0);
                    isStart = false;
                }
            } else if (isEnd) {
                strBuild.append(split[i]);
            }
            if (i + 1 != split.length) {
                strBuild.append(" ");
            }
        }
        e = strBuild.toString();
        strBuild.setLength(0);
        Event event = new Event(n, s, e, false);
        Task.addToList(event);
        Task.printDefault(event);
    }

    @Override
    public String write(File file) {
        return this.toWrite();
    }

    @Override
    public String toString() {
        return "   [E]" + super.toString() + " |from: " + start + " to: " + end + "|\n";

    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + start + " | " + end + "\n";
    }
}