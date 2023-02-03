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

    /** Queue of {@code ExecutionService} waiting to be executed. */
    private final ArrayDeque<ParserService<CommandLineInput>> executionQueue = new ArrayDeque<>();

    /** The currently running {@code ExecutionService} */
    private Service<?> runningService = null;


    /**
     * Constructs an AppManager from the given parameter.
     *
     * @param logicManager - the LogicManager to use.
     * @param uiManager - the UiManager to use.
     */
    public AppManager(LogicManager logicManager, IoManager uiManager) {
        this.logicManager = logicManager;
        this.ioManager = uiManager;
    }


    /**
     * Performs the starting processes.
     * <p>
     * Greets and loads previous task data.
     */
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


    /**
     * Processes and executes the user's input and queues the service created.
     */
    public void processInput() {
        queue(ioManager.readLine());
    }


    /**
     * Queues the given service for execution.
     *
     * @param service - the service to queue.
     */
    private synchronized void queue(String input) {
        executionQueue.add(new ParserService<>(logicManager.getInputParser(), input));
        executeNext();
    }


    /**
     * Attempts to execute the next service in the queue. If there already is a
     * running service or if the queue is empty, nothing will be done.
     */
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


    /**
     * Starts the given service.
     *
     * @param service - the service to start.
     */
    private void startExecution(ExecutionService service) {
        runningService = service;
        service.setOnSucceeded(s -> handleExecutionSuccess(service));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    /**
     * Handles the event when the task of the given ExecutionService is
     * successfully executed.
     *
     * <p>Result message of the service is displayed and the follow up service
     * is started.
     *
     * @param service - the service whose task succeeded.
     */
    private void handleExecutionSuccess(ExecutionService service) {
        service.followUpDispatcher().ifPresentOrElse(
                this::startExecution,
                this::completeService);
    }


    /**
     * Handles the event when the task of the given ExecutionService failed to
     * execute completely.
     *
     * <p>Information about the exception that occured is displayed.
     *
     * @param service - the service whose task failed.
     */
    private void handleExecutionFailure(Service<?> service) {
        ioManager.replyException(service.getException());
        completeService();
    }


    /**
     * Clears the currently running service for the next service in queue.
     */
    private void completeService() {
        runningService = null;
        executeNext();
    }
}
