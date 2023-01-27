import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class Deadline extends Task{

    private static StringBuilder strBuild = new StringBuilder();
    private static Scanner sc = new Scanner(System.in);

    private final LocalDateTime end;
    public Deadline(String name, String end, boolean done) {
        super(name, done);
        this.end = LocalDateTime.parse(end,
                Task.DATE_TIME_FORMATTER);
    }

    public static void createDeadline(String[] split) {
        boolean isName = true;
        String n = " ", e = " ";
        for (int i = 1; i < split.length; i++) {
            if (isName) {
                if (!split[i + 1].equalsIgnoreCase("/by")) {
                    strBuild.append(split[i]);
                    strBuild.append(" ");
                } else {
                    strBuild.append(split[i]);
                    n = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                    i++;
                }
            } else {
                strBuild.append(split[i]);
                if (i + 1 != split.length) {
                    strBuild.append(" ");
                }
            }
        }
        e = strBuild.toString();
        strBuild.setLength(0);
        System.out.println(e);
        Deadline d = new Deadline(n, e, false);
        Task.addToList(d);
        Task.printDefault(d);
    }

    @Override
    public String write(File file) {
        return this.toWrite();
    }

    @Override
    public String toString() {
        return "   [D]" + super.toString() + " |by: "
                + end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT)) + "|\n";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + end.format(Task.DATE_TIME_FORMATTER) + "\n";
    }
}
