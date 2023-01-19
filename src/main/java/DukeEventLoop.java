import event_loop.EventLoop;
import event_loop.Executable;
import event_loop.NestableExecutableObject;
import features.bye.Bye;
import features.event_manager.TaskManager;
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
        final Bye bye = new Bye();
        bye.register(executable);
        // final Echo echo = new Echo();
        // echo.register(executable);
        final TaskManager manager = new TaskManager();
        manager.register(executable);
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
