package meggy;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import meggy.exception.Function;
import meggy.exception.MeggyException;
import meggy.exception.MeggyNoArgException;
import meggy.task.UserTask;

/** The chatbot. After initialization, interact by calling {@code getResponse} method. */
public class Meggy {
    /** What to do when the user-typed the command is unknown. Currently: notify user command is unknown. */
    public final static Function<String, String> unknownCmdBehavior = s -> {
        throw new MeggyException(Resource.errUnknownCmd(Parser.get1stArg(s)));
    };
    /**
     * What to do when reaching different commands.
     * <p>
     * Keys: non-null strings.
     * <p>
     * Values: Non-null function that accepts unparsed string arguments and return chatbot response strings. Must be the
     * function that modifies task list of the command requires it.
     */
    public final Map<String, Function<String, String>> cmdToJob;
    /** Commmands that changes the task list */
    public final Set<String> listChangingCmd = Set.of(Resource.CMD_MARK, Resource.CMD_UNMK, Resource.CMD_TODO,
            Resource.CMD_DDL, Resource.CMD_EVENT, Resource.CMD_DEL);
    /** List of tasks. Allows dupes. */
    private final TaskList tasks;
    /** Location to save cross-session data. */
    private final Storage storage;
    /** Switch that enables/disables write to storage file. */
    private boolean fileWrite = true;
    /** Channel to send extra message. */
    private Consumer<String> notifMsgSender = System.out::println;

    /** Creates a chatbot agent instance. */
    public Meggy() {
        tasks = new TaskList();
        cmdToJob = Map.of(
                Resource.CMD_EXIT, s -> Resource.FAREWELL,
                Resource.CMD_LIST, s -> Resource.NOTIF_LIST + tasks,
                Resource.CMD_MARK, s -> markTaskStatus(s, true),
                Resource.CMD_UNMK, s -> markTaskStatus(s, false),
                Resource.CMD_TODO, s -> addTask(s, Util.TODO_NEW),
                Resource.CMD_DDL, s -> addTask(s, Util.DDL_NEW),
                Resource.CMD_EVENT, s -> addTask(s, Util.EVENT_NEW),
                Resource.CMD_DEL, this::deleteTask,
                Resource.CMD_FIND, this::find
        );
        storage = new Storage(new File(Util.DATA_FILE_PATH));
    }

    /** Save task list to storage file. Redirects {@link MeggyException} to {@code notifMsgSender} */
    private void saveToFile() {
        if (!fileWrite) {
            return;
        }
        try {
            storage.save(tasks);
        } catch (MeggyException e) {
            notifMsgSender.accept(e.getMessage());
        }
    }

    /**
     * Updates the status of the user task specified by index.
     *
     * @param args      Non-null. Index (start with 1) string of task to be updated or invalid input.
     * @param newStatus The task's updated status.
     * @return Response to 'mark/unmark' command.
     */
    private String markTaskStatus(String args, boolean newStatus) throws MeggyException {
        assert args != null;
        final int idx;
        try {
            idx = Parser.parseIdx(args);
            tasks.boundsCheck(idx);
        } catch (MeggyException e) {
            notifMsgSender.accept(Util.usageIdxCmd(newStatus ? Resource.CMD_MARK : Resource.CMD_UNMK));
            throw e;
        }
        final UserTask task = tasks.get(idx);
        task.setDone(newStatus);
        saveToFile();
        return (newStatus ? Resource.NOTIF_MARK : Resource.NOTIF_UNMK) + Resource.TASK_STRING_INDENT + task + '\n';
    }

    /**
     * Adds task to the bottom of {@code tasks} list if an equivalent task is not already in the list. Otherwise,
     * nothing is changed. O(n) time complexity.
     *
     * @param args    Non-null. Unparsed task description string.
     * @param taskNew Non-null. Constructor of task to accept {@code args}.
     * @return Response to "todo/ddl/event" command.
     */
    private String addTask(String args, Function<String, UserTask> taskNew) throws MeggyException {
        assert args != null;
        assert taskNew != null;
        final UserTask newTask = taskNew.apply(args);
        for (UserTask task : tasks) {
            if (task.equals(newTask)) {
                throw new MeggyException(Resource.ERR_DUPE_TASK + Resource.TASK_STRING_INDENT + task);
            }
        }
        tasks.add(newTask);
        saveToFile();
        return Resource.NOTIF_ADD + reportChangedTaskAndList(newTask);
    }

    /**
     * Deletes task from {@code tasks} list. Currently O(n) runtime.
     *
     * @param arg Non-null. Index (start with 1) string of task to be updated.
     * @return Response to 'delete' command.
     */
    private String deleteTask(String arg) throws MeggyException {
        assert arg != null;
        final int idx;
        try {
            idx = Parser.parseIdx(arg);
            tasks.boundsCheck(idx);
        } catch (MeggyException e) {
            notifMsgSender.accept(Util.usageIdxCmd(Resource.CMD_DEL));
            throw e;
        }
        final UserTask task = tasks.remove(idx);
        saveToFile();
        return Resource.NOTIF_DEL + reportChangedTaskAndList(task);
    }

    /**
     * Formats the string of the recently modified task and {@code tasks} list.
     *
     * @param task Non-null. The recently modified task.
     */
    private String reportChangedTaskAndList(UserTask task) {
        assert task != null;
        return Resource.TASK_STRING_INDENT + task + '\n' + Resource.nTaskFmt(tasks.size());
    }

    /**
     * Lists all the tasks with description containing the keyword.
     *
     * @param substring Non-null. The keyword to look for.
     * @return The printable string of the listed tasks.
     * @throws MeggyNoArgException If user search keyword is blank.
     */
    private String find(String substring) throws MeggyNoArgException {
        assert substring != null;
        if (substring.isEmpty()) {
            throw new MeggyNoArgException();
        }
        final TaskList ans = new TaskList();
        for (UserTask task : tasks) {
            if (task.desc.contains(substring)) {
                ans.add(task);
            }
        }
        return Resource.NOTIF_FIND + ans;
    }

    /**
     * Parses and executes user's input line. Changes task list accordingly.
     *
     * @param line Non-null. User's raw input line.
     * @return Complete response of this chatbot. Either the response of a valid query or error message.
     * @throws MeggyException If syntax error occurred during parsing.
     */
    public String parseAndGetResponse(String line) throws MeggyException {
        assert line != null;
        final Parser.JobAndArg<String> jobAndArg = Parser.parseJobAndArg(cmdToJob, line);
        final Function<String, String> job = jobAndArg.job == null ? unknownCmdBehavior : jobAndArg.job;
        return job.apply(jobAndArg.args);
    }

    /**
     * Sets new {@code notifMsgSender}, use this channel to send greeting message, and add tasks from file to list.
     */
    public void bindGui(Consumer<String> notifMsgSender) {
        assert notifMsgSender != null;
        this.notifMsgSender = notifMsgSender;
        notifMsgSender.accept(Resource.GREETINGS);

        fileWrite = false;
        try {
            storage.load(this::parseAndGetResponse);
        } catch (MeggyException e) {
            notifMsgSender.accept(e.getMessage());
        }
        fileWrite = true;
    }
}
