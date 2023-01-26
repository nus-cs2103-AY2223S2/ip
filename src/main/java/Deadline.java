import java.io.File;
import java.util.Scanner;

public class Deadline extends Task{

    private static StringBuilder strBuild = new StringBuilder();
    private static Scanner sc = new Scanner(System.in);

    private final String end;
    public Deadline(String name, String end, boolean done) {
        super(name, done);
        this.end = end;
    }

    public static void createDeadline(String[] split) {
        boolean isName = true;
        String n = " ", e = " ";
        for (int i = 1; i < split.length; i++) {
            if (isName) {
                if (!split[i].equalsIgnoreCase("/by")) {
                    strBuild.append(split[i]);
                } else {
                    n = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                }
            } else {
                strBuild.append(split[i]);
            }
            if (i + 1 != split.length) {
                strBuild.append(" ");
            }
        }
        e = strBuild.toString();
        strBuild.setLength(0);
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
        return "   [D]" + super.toString() + " |by: " + end + "|\n";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + end + "\n";
    }
}
