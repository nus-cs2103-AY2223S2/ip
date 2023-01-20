import core.injections.Injections;
import core.utils.TokenUtilities;
import domain.usecases.ByeUsecase;
import domain.usecases.EchoUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;
import presentation.controllers.DukeEventLoop;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        configureInjections();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final Scanner scanner = new Scanner(System.in);
        final DukeEventLoop eventLoop = DukeEventLoop.createEventLoop(scanner);
        eventLoop.run();
        scanner.close();
    }

    /**
     * This would register the singletons that we would be using later on.
     */
    private static void configureInjections() {
        Injections.registerLazySingleton(ByeUsecase.class, ByeUsecase::new);
        Injections.registerLazySingleton(EchoUsecase.class, EchoUsecase::new);
        Injections.registerLazySingleton(TaskManagerUsecase.class,
                TaskManagerUsecase::new);
        Injections.registerLazySingleton(UnknownCommandUsecase.class,
                UnknownCommandUsecase::new);
        Injections.registerLazySingleton(TokenUtilities.class,
                TokenUtilities::new);
    }
}
