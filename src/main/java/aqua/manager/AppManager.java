package aqua.manager;

import java.util.ArrayDeque;

import aqua.exception.LoadException;
import aqua.logic.CommandLineInput;
import aqua.logic.ExecutionService;
import aqua.logic.command.ListCommand;
import aqua.logic.parser.ParserService;
import javafx.concurrent.Service;


/** Manager of the application's processes. */
public class AppManager {
    private final LogicManager logicManager;
    private final IoManager ioManager;

    private final ArrayDeque<ParserService<CommandLineInput>> executionQueue = new ArrayDeque<>();

    private Service<?> runningService = null;


    /**
     * Constructs an {@code AppManager} from the given parameter.
     *
     * @param logicManager - the {@code LogicManager} to use.
     * @param ioManager - the {@code IoManager} to use.
     */
    public AppManager(LogicManager logicManager, IoManager ioManager) {
        this.logicManager = logicManager;
        this.ioManager = ioManager;
    }


    /** Performs the starting processes. */
    public void start() {
        ioManager.greet();
        try {
            logicManager.load();
            ioManager.replyLoadSuccess();
            startExecution(new ListCommand().getService(null, logicManager));
        } catch (LoadException loadException) {
            ioManager.replyException(loadException);
        }
    }


    /** Queues the user's input for execution. */
    public synchronized void processInput() {
        String userInput = ioManager.readLine();
        executionQueue.add(new ParserService<>(logicManager.getInputParser(), userInput));
        executeNext();
    }


    private synchronized void executeNext() {
        if (runningService != null || executionQueue.isEmpty()) {
            return;
        }
        startParser(executionQueue.poll());
    }


    private void startParser(ParserService<CommandLineInput> service) {
        service.setOnSucceeded(s -> startExecution(service.getValue().getService(logicManager, ioManager)));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    private void startExecution(ExecutionService service) {
        runningService = service;
        service.setOnSucceeded(s -> handleExecutionSuccess(service));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    private void handleExecutionSuccess(ExecutionService service) {
        service.getNextService().ifPresentOrElse(
                this::startExecution,
                this::completeService);
    }


    private void handleExecutionFailure(Service<?> service) {
        ioManager.replyException(service.getException());
        completeService();
    }


    private void completeService() {
        runningService = null;
        executeNext();
    }
}
