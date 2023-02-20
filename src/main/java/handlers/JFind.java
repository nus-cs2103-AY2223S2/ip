package handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.TaskList;
import types.IHandler;
import types.data.Task;

/**
 * Finds a specific task.
 */
public class JFind implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("find (.*)");
    private final TaskList ts;

    /**
     * Constructs the command runner.
     * @param ts Task storage to use.
     */
    public JFind(TaskList ts) {
        this.ts = ts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String take(String s) {
        Matcher m = PATTERN.matcher(s);
        boolean isOkay = m.matches();
        assert isOkay;
        String keyword = m.group(1);
        List<Task> allTasks = ts.getTasks();
        ArrayList<Task> matchedTasks = new ArrayList<>(ts.getTaskCount() / 5);

        allTasks.stream().parallel()
                .filter(t -> t.getName().contains(keyword))
                .forEach(matchedTasks::add);

        int j = 1;
        StringBuilder sb = new StringBuilder(20 * matchedTasks.size());
        for (Task i : matchedTasks) {
            sb.append(String.format("%d. %s\n", j++, i));
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
