import event_loop.EventLoop;
import event_loop.Executable;
import event_loop.NestableExecutableObject;
import features.echo.Echo;
import features.bye.Bye;

import java.util.Scanner;

/**
 * The event loop for managing Duke.
 */
public class DukeEventLoop extends EventLoop {
    private DukeEventLoop(Scanner scanner, Executable rootExecutable) {
        super(rootExecutable);
        this.scanner = scanner;
    }

    static DukeEventLoop createEventLoop(Scanner scanner) {
        final NestableExecutableObject executable =
                new NestableExecutableObject();
        executable.registerIdentifiableExecutable(new Bye());
        executable.registerPostExecutable(new Echo());
        return new DukeEventLoop(scanner, executable);
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
