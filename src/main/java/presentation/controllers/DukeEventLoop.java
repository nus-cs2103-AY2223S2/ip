package presentation.controllers;

import domain.models.core.EventLoop;
import domain.models.core.Executable;
import domain.models.core.NestableExecutableObject;
import domain.usecases.ByeUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;

import java.util.Scanner;

/**
 * The event loop for managing Duke.
 */
public class DukeEventLoop extends EventLoop {
    private DukeEventLoop(Scanner scanner, Executable rootExecutable) {
        super(rootExecutable);
        this.scanner = scanner;
    }

    public static DukeEventLoop createEventLoop(Scanner scanner) {
        final NestableExecutableObject executable =
                new NestableExecutableObject();

        final ByeUsecase bye = new ByeUsecase();
        bye.register(executable);
        // final EchoUsecase echo = new EchoUsecase();
        // echo.register(executable);
        final TaskManagerUsecase manager = new TaskManagerUsecase();
        manager.register(executable);
        final UnknownCommandUsecase unknown = new UnknownCommandUsecase();
        unknown.register(executable);
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
