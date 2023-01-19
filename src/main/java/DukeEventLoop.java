import event_loop.EventLoop;
import event_loop.Executable;
import event_loop.NestableExecutableObject;
import features.echo.Echo;
import features.bye.Bye;
import features.todos_manager.TodosManager;

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
        final TodosManager manager = new TodosManager();
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
