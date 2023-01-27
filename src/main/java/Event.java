import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class Event extends Task{
    private static StringBuilder strBuild = new StringBuilder();
    private static Scanner sc = new Scanner(System.in);
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String name, String start, String end, boolean done) {
        super(name, done);
        this.start = LocalDateTime.parse(start,
                Task.DATE_TIME_FORMATTER);
        this.end = LocalDateTime.parse(end,
                Task.DATE_TIME_FORMATTER);
    }

    public static void createEvent(String[] split) {

        boolean isName = true;
        boolean isStart = false;
        boolean isEnd = false;
        boolean needSpace = true;
        String n = " ", s = " ", e = " ";
        for (int i = 1; i < split.length; i++) {
            if (isName) {
                if (!split[i + 1].equalsIgnoreCase("/from")) {
                    strBuild.append(split[i]);
                    strBuild.append(" ");
                } else {
                    isStart = true;
                    strBuild.append(split[i]);
                    n = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                    i++;
                }
            } else if (isStart) {
                if (!split[i + 1].equalsIgnoreCase("/to")) {
                    strBuild.append(split[i]);
                    strBuild.append(" ");
                } else {
                    isEnd = true;
                    strBuild.append(split[i]);
                    s = strBuild.toString();
                    strBuild.setLength(0);
                    isStart = false;
                    i++;
                }
            } else if (isEnd) {
                strBuild.append(split[i]);
                if (i + 1 != split.length) {
                    strBuild.append(" ");
                }
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
        return "   [E]" + super.toString() + " |from: "
                + start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + " to: "
                + end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + "|\n";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | "
                + start.format(Task.DATE_TIME_FORMATTER) + " | "
                + end.format(Task.DATE_TIME_FORMATTER) + "\n";
    }
}