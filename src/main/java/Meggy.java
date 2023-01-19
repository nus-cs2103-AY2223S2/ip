import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * The chatbot. Supports customized input stream and output stream
 */
public class Meggy implements Runnable {
    /**
     * What to do when reaching different commands. Keys: non-null strings. Values: Non-null function that accepts
     * unparsed string arguments and return string answers.
     */
    public final Map<String, MeggyException.Function<String, String>> cmdJob;
    /**
     * What to do when the command is unknown. Currently: feedback unknown command
     */
    public final MeggyException.Function<String, String> unknownCmdBehavior = s -> {
        throw new MeggyException(Resource.errUnknownCmd(Util.get1stArg(s)));
    };
    private final Scanner in;
    private final PrintStream out;
    /**
     * List of tasks. Allow dupes.
     */
    private final ArrayList<UserTask> tasks;

    /**
     * @param in  Non-null input stream
     * @param out Non-null output stream
     */
    public Meggy(InputStream in, OutputStream out) {
        if (in == null)
            throw new NullPointerException("InputStream is null");
        if (out == null)
            throw new NullPointerException("OutputStream is null");
        this.in = new Scanner(in);
        this.out = out instanceof PrintStream ? (PrintStream) out : new PrintStream(out);
        tasks = new ArrayList<>();
        cmdJob = Map.of(
                Resource.cmdExit, s -> Resource.farewell,
                Resource.cmdList, s -> listItems(),
                Resource.cmdMk, s -> markTaskStatus(s, true),
                Resource.cmdUnmk, s -> markTaskStatus(s, false),
                Resource.cmdTodo, s -> addTask(s, Util.todoNew),
                Resource.cmdDdl, s -> addTask(s, Util.ddlNew),
                Resource.cmdEvent, s -> addTask(s, Util.eventNew)
        );
    }

    public static void main(String[] args) {
        new Meggy(System.in, System.out).run();
    }

    private String listItems() {
        final StringBuilder ans = new StringBuilder(Resource.notifList);
        int i = 0;
        for (UserTask task : tasks)
            ans.append(Resource.idxFmt(i++)).append(task).append('\n');
        return ans.toString();
    }

    /**
     * @param arg Trimmed string
     * @return
     */
    private int parseArg(String arg) throws MeggyException {
        arg = Util.get1stArg(arg);
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
     * @param arg Index (start with 1) of task to be updated.
     */
    private String markTaskStatus(String arg, boolean newStatus) {
        final int idx;
        try {
            idx = parseArg(arg);
        } catch (MeggyException e) {
            return e.getMessage() + Util.usageIdxCmd(newStatus ? Resource.cmdMk : Resource.cmdUnmk);
        }
        final UserTask task = tasks.get(idx);
        task.status = newStatus;
        return (newStatus ? Resource.notifMk : Resource.notifUnmk) + Resource.taskIndent + task + '\n';
    }

    private String addTask(String args, MeggyException.Function<String, UserTask> newTask) throws MeggyException {
        final UserTask task = newTask.apply(args);
        tasks.add(task);
        return Resource.notifAdd + Resource.taskIndent + task + '\n' + Resource.nTaskFmt(tasks.size());
    }

    /**
     * Interact with user using designated io streams
     */
    @Override
    public void run() {
        // Front page
        out.print(Resource.msgHd);
        out.println(Resource.logo);
        out.print(Resource.greetings);
        out.print(Resource.msgTl);
        while (in.hasNextLine()) { // reads user input and reply in each iteration

            //Parse command and args
            final String line = in.nextLine().trim();
            final int spaceIdx = line.indexOf(' ');
            final String cmd = (spaceIdx < 0 ? line : line.substring(0, spaceIdx)).toLowerCase();
            MeggyException.Function<String, String> job = cmdJob.get(cmd);
            String args;
            if (job == null) { // Unknown command
                job = unknownCmdBehavior;
                args = line;
            } else
                args = spaceIdx < 0 ? "" : line.substring(spaceIdx + 1);
            args = args.trim();
            //Execute and commands and display results
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
}
