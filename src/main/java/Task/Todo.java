package Task;

import Duke.Ui;

import java.io.File;

public class Todo extends Task {

    private static StringBuilder strBuild = new StringBuilder();

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
            String s = Ui.readCommand();
            Todo t = new Todo(s, false);
            strBuild.setLength(0);
            TaskList.addToList(t);
            Ui.printDefault(t);
        } else {
            for (int i = 1; i < split.length; i++) {
                strBuild.append(split[i]);
                if (i + 1 != split.length) {
                    strBuild.append(" ");
                }
            }
            Todo t = new Todo(strBuild.toString(), false);
            strBuild.setLength(0);
            TaskList.addToList(t);
            Ui.printDefault(t);
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
