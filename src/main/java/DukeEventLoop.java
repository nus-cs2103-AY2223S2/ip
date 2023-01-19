import event_loop.EventLoop;
import event_loop.Executable;

import java.util.Scanner;

public class DukeEventLoop extends EventLoop {
    public DukeEventLoop(Executable rootExecutable, Scanner scanner) {
        super(rootExecutable);
        this.scanner = scanner;
    }

    /**
     * The scanner used for reading user input.
     */
    private final Scanner scanner;

    @Override
    protected String[] getTokens() {
        return scanner.nextLine().trim().split(" ");
    }
}
