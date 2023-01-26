import java.util.ArrayList;
import java.util.Collections;

public class Todo extends Task {
    public Todo(String objective) {
        super(objective);
    }

    public static Todo parseArgs(String[] args) throws TaskParseException {
        String objective = "";
        for (int i = 0; i < args.length; i++) {
            objective += (objective.isEmpty() ? "" : " ") + args[i];
        }
        if (objective.isEmpty()) throw new TaskParseException("This todo is missing its body text!");
        return new Todo(objective);
    }

    public static Todo parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("T")) throw new TaskParseException("Invalid todo data format");
            boolean done = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            String objective = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) objective += (i > 0 ? "\n" : "") + data[seek++];
            Todo todo = new Todo(objective);
            todo.done = done;
            return todo;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new TaskParseException("Todo data is malformed:\n" + ex.getMessage());
        }
    }

    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "T " + done + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
