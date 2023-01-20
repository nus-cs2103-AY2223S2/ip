package presentation.controllers;

import core.singletons.Singletons;
import domain.entities.core.EventLoop;
import domain.entities.core.Executable;
import domain.entities.core.NestableExecutableObject;
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

        final ByeUsecase bye = Singletons.get(ByeUsecase.class);
        bye.register(executable);
        // final EchoUsecase echo = new EchoUsecase();
        // echo.register(executable);
        final TaskManagerUsecase manager =
                Singletons.get(TaskManagerUsecase.class);
        manager.register(executable);
        final UnknownCommandUsecase unknown =
                Singletons.get(UnknownCommandUsecase.class);
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
