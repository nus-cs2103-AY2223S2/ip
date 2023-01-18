import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
    public final static Map<String, Function<String, String>> cmdJob = Map.of(
            Resource.exitCmd, s -> Resource.farewell
    );
    /**
     * What to do when the command is unknown.
     */
    public final static Function<String, String> unknownCmdBehavior = Function.identity();
    private final Scanner in;
    private final PrintStream out;

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
    }

    public static void main(String[] args) {
        new Meggy(System.in, System.out).run();
    }

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
            if (Resource.exitCmd.equals(cmd))
                return;
        }
        out.println("REACHED END OF INPUT WITHOUT 'BYE' COMMAND");
    }
}
