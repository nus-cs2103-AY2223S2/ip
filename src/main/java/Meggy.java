import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * The chatbot. Supports customized {@link InputStream} and {@link OutputStream}.
 */
public class Meggy implements Runnable {
    /**
     * What to do when reaching different commands. Keys: non-null strings. Values: Non-null function that accepts
     * unparsed string arguments and return chatbot response strings.
     */
    public final Map<String, MeggyException.Function<String, String>> cmdToJob;
    /**
     * What to do when the user-typed the command is unknown. Currently: notify user command is unknown.
     */
    public final MeggyException.Function<String, String> unknownCmdBehavior = s -> {
        throw new MeggyException(Resource.errUnknownCmd(Util.get1stArg(s)));
    };
    /**
     * Customizable input.
     */
    private final Scanner in;
    /**
     * Customizable output.
     */
    private final PrintStream out;
    /**
     * List of tasks. Allow dupes.
     */
    private final ArrayList<UserTask> tasks;

    /**
     * @param in  Non-null. Customizable input.
     * @param out Non-null. Customizable output.
     */
    public Meggy(InputStream in, OutputStream out) {
        if (in == null)
            throw new NullPointerException("InputStream is null");
        if (out == null)
            throw new NullPointerException("OutputStream is null");
        this.in = new Scanner(in);
        this.out = out instanceof PrintStream ? (PrintStream) out : new PrintStream(out);
        tasks = new ArrayList<>();
        cmdToJob = Map.of(
                Resource.cmdExit, s -> Resource.farewell,
                Resource.cmdList, s -> listTasks(),
                Resource.cmdMk, s -> markTaskStatus(s, true),
                Resource.cmdUnmk, s -> markTaskStatus(s, false),
                Resource.cmdTodo, s -> addTask(s, Util.todoNew),
                Resource.cmdDdl, s -> addTask(s, Util.ddlNew),
                Resource.cmdEvent, s -> addTask(s, Util.eventNew),
                Resource.cmdDel, this::deleteTask
        );
    }

    /**
     * 'List' command: prints all tasks in the {@code tasks} list.
     *
     * @return Response to 'list' command.
     */
    private String listTasks() {
        final StringBuilder ans = new StringBuilder(Resource.notifList);
        int i = 0;
        for (UserTask task : tasks)
            ans.append(Resource.idxFmt(i++)).append(task).append('\n');
        return ans.toString();
    }

    /**
     * Parses the index integer from the first word in args string.
     *
     * @param args Non-null. Trimmed arguments string.
     * @return Parsed idx (starts with 0)
     * @throws MeggyNoArgException If args string is empty.
     * @throws MeggyNFException    If args string's first word is not a signed 32-bit {@link Integer}.
     * @throws MeggyIOBException   If the parsed index is out of bounds with respect to the tasks list.
     */
    private int parseIdx(String args) throws MeggyException {
        final String arg = Util.get1stArg(args);
        if ("".equals(arg))
            throw new MeggyNoArgException();
        final int idx;
        try {
            idx = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new MeggyNFException(arg);
        }
        final int nTask = tasks.size();
        if (idx < 0 || idx >= nTask)
            throw new MeggyIOBException(idx, nTask);
        return idx;
    }

    /**
     * Updates the status of the user task specified by index.
     *
     * @param args      Non-null. Index (start with 1) string of task to be updated.
     * @param newStatus The task's updated status.
     * @return Response to 'mark/unmark' command.
     */
    private String markTaskStatus(String args, boolean newStatus) {
        final int idx;
        try {
            idx = parseIdx(args);
        } catch (MeggyException e) {
            return e.getMessage() + Util.usageIdxCmd(newStatus ? Resource.cmdMk : Resource.cmdUnmk);
        }
        final UserTask task = tasks.get(idx);
        task.status = newStatus;
        return (newStatus ? Resource.notifMk : Resource.notifUnmk) + Resource.taskIndent + task + '\n';
    }

    /**
     * Adds task to the bottom of {@code tasks} list.
     *
     * @param args    Non-null. Unparsed task description string.
     * @param newTask Non-null. Constructor of task to accept {@code args}.
     * @return Response to "todo/ddl/event" command.
     */
    private String addTask(String args, MeggyException.Function<String, UserTask> newTask) throws MeggyException {
        final UserTask task = newTask.apply(args);
        tasks.add(task);
        return Resource.notifAdd + reportChangedTaskAndList(task);
    }

    /**
     * Deletes task from {@code tasks} list. O(n) runtime.
     *
     * @param arg Non-null. Index (start with 1) string of task to be updated.
     * @return Response to 'delete' command.
     */
    private String deleteTask(String arg) {
        final int idx;
        try {
            idx = parseIdx(arg);
        } catch (MeggyException e) {
            return e.getMessage() + Util.usageIdxCmd(Resource.cmdDel);
        }
        final UserTask task = tasks.remove(idx);
        return Resource.notifDel + reportChangedTaskAndList(task);
    }

    /**
     * Formatted string about the recently modified task and {@code tasks} list.
     *
     * @param task Non-null. The recently modified task.
     */
    private String reportChangedTaskAndList(UserTask task) {
        return Resource.taskIndent + task + '\n' + Resource.nTaskFmt(tasks.size());
    }

    /**
     * Interacts with user using designated IO.
     */
    @Override
    public void run() {
        // Front page
        out.print(Resource.msgHd);
        out.println(Resource.logo);
        out.print(Resource.greetings);
        out.print(Resource.msgTl);
        while (in.hasNextLine()) { // reads input and responds in each iteration
            //Parse command and args
            final String line = in.nextLine().trim();
            final int spaceIdx = line.indexOf(' ');
            final String cmd = (spaceIdx < 0 ? line : line.substring(0, spaceIdx)).toLowerCase();
            MeggyException.Function<String, String> job = cmdToJob.get(cmd);
            String args;
            if (job == null) { // Unknown command
                job = unknownCmdBehavior;
                args = line;
            } else
                args = spaceIdx < 0 ? "" : line.substring(spaceIdx + 1);
            args = args.trim();
            //Execute commands and display results
            out.print(Resource.msgHd);
            String response;
            try {
                response = job.apply(args);
            } catch (MeggyException e) {
                response = e.getMessage();
            }
            out.print(response);
            out.print(Resource.msgTl);
            if (Resource.cmdExit.equals(cmd))
                return;
        }
        out.println("WARNING: REACHED END OF INPUT WITHOUT 'BYE' COMMAND");
    }

    public static void main(String[] args) {
        new Meggy(System.in, System.out).run();
    }
}
