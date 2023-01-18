import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * The chatbot. Supports customized input stream and output stream
 */
public class Meggy implements Runnable {
    /**
     * What to do when reaching different commands. Keys: non-null strings. Values: Non-null function that accepts
     * unparsed string arguments and return string answers.
     */
    public final Map<String, Function<String, String>> cmdJob;
    /**
     * What to do when the command is unknown. Currently: add to list
     */
    public final Function<String, String> unknownCmdBehavior;
    private final Scanner in;
    private final PrintStream out;
    /**
     * List of string items. Allow dupes.
     */
    private final ArrayList<UserTask> items;

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
        items = new ArrayList<>();
        cmdJob = Map.of(
                Resource.cmdExit, s -> Resource.farewell,
                Resource.cmdList, s -> listItems(),
                Resource.cmdMk, s -> markTaskStatus(s, true),
                Resource.cmdUnmk, s -> markTaskStatus(s, false)
        );
        unknownCmdBehavior = s -> {
            items.add(new UserTask(s));
            return Resource.notifAdd + s;
        };
    }

    public static void main(String[] args) {
        new Meggy(System.in, System.out).run();
    }

    private final String listItems() {
        final StringBuilder ans = new StringBuilder(Resource.notifList);
        int i = 0;
        for (UserTask item : items)
            ans.append(Resource.idxFmt(i++)).append(item).append('\n');
        return ans.toString();
    }

    /**
     * @param arg Index (start with 1) of task to be updated.
     */
    private final String markTaskStatus(String arg, boolean newStatus) {
        final int idx = Integer.parseInt(arg) - 1;
        final UserTask item = items.get(idx);
        item.status = newStatus;
        return (newStatus ? Resource.notifMk : Resource.notifUnmk) + "   " + item;
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
            final String line = in.nextLine().trim();
            final int spaceIdx = line.indexOf(' ');
            final String cmd = (spaceIdx < 0 ? line : line.substring(0, spaceIdx)).toLowerCase();
            Function<String, String> job = cmdJob.get(cmd);
            final String args;
            if (job == null) { // Unknown command
                job = unknownCmdBehavior;
                args = line;
            } else
                args = spaceIdx < 0 ? "" : line.substring(spaceIdx + 1);
            out.print(Resource.msgHd);
            out.println(job.apply(args));
            out.print(Resource.msgTl);
            if (Resource.cmdExit.equals(cmd))
                return;
        }
        out.println("REACHED END OF INPUT WITHOUT 'BYE' COMMAND");
    }
}
