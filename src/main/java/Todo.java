import java.io.File;
import java.util.Scanner;

public class Todo extends Task{

    private static StringBuilder strBuild = new StringBuilder();
    private static Scanner sc = new Scanner(System.in);

    public Todo(String name, boolean done) {
        super(name, done);
    }
    @Override
    public String write(File file) {
        return this.toWrite();
    }

    public static void createTodo(String[] split) {
        if (split.length == 1) {
            System.out.println("What do u need to do ah? u never write.");
            String s = sc.nextLine();
            Todo t = new Todo(s, false);
            strBuild.setLength(0);
            Task.addToList(t);
            Task.printDefault(t);
        } else {
            for (int i = 1; i < split.length; i++) {
                strBuild.append(split[i]);
                if (i + 1 != split.length) {
                    strBuild.append(" ");
                }
            }
            Todo t = new Todo(strBuild.toString(), false);
            strBuild.setLength(0);
            Task.addToList(t);
            Task.printDefault(t);
        }
    }
    @Override
    public String toString() {
        return "   [T]" + super.toString() + "\n";
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + " | \n";
    }
}
