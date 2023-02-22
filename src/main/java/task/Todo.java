package task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import exception.TaskParseException;

/**
 * Implementation of a <code>Task</code> with no particular chronological position.
 */
public class Todo extends Task {
    /**
     * Creates a todo with the specified textual objective.
     *
     * @param objective description of this todo's objective.
     */
    public Todo(String objective) {
        super(objective);
    }

    /**
     * Parses the supplied <code>String[]</code> command-line arguments to create a todo.
     *
     * @param args arguments containing the todo to be parsed.
     * @return a todo represented by <code>args</code>.
     * @throws TaskParseException if <code>args</code> does not represent a valid todo.
     */
    public static Todo parseArgs(String[] args) throws TaskParseException {
        String objective = "";
        for (int i = 0; i < args.length; i++) {
            objective += (objective.isEmpty() ? "" : " ") + args[i];
        }
        if (objective.isEmpty()) {
            throw new TaskParseException("This todo is missing its body text!");
        }
        return new Todo(objective);
    }

    /**
     * Parses the supplied <code>String[]</code> save data to create a todo.
     *
     * @param data data containing the todo to be parsed.
     * @return a todo represented by <code>data</code>.
     * @throws TaskParseException if <code>data</code> does not represent a valid todo.
     */
    public static Todo parseLoad(String[] data) throws TaskParseException {
        try {
            String[] header = data[0].split(" ");
            if (!header[0].equals("T")) {
                throw new TaskParseException("Invalid todo data format");
            }
            boolean isDone = Boolean.parseBoolean(header[1]);
            int objLines = Integer.parseInt(header[2]);
            String objective = "";
            int seek = 1;
            for (int i = 0; i < objLines; i++) {
                objective += (i > 0 ? "\n" : "") + data[seek++];
            }
            Todo todo = new Todo(objective);
            todo.isDone = isDone;
            return todo;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new TaskParseException("Todo data is malformed:\n" + ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] save() {
        ArrayList<String> repres = new ArrayList<>();
        String cur;
        cur = "T " + isDone + " " + (objective.codePoints().filter(c -> c == '\n').count() + 1);
        repres.add(cur);
        Collections.addAll(repres, objective.split("\n"));
        return repres.toArray(new String[repres.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBeforeDate(LocalDateTime date) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAfterDate(LocalDateTime date) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
