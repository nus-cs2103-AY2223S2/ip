package aqua.manager;

import aqua.exception.LoadException;
import aqua.logic.CommandLineInput;
import aqua.logic.ExecutionService;
import aqua.logic.command.ListCommand;


/** Manager of the application's processes. */
public class AppManager {
    private final LogicManager logicManager;
    private final IoManager ioManager;


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
            initiateService(new ListCommand().getService(null, logicManager));
        } catch (LoadException loadException) {
            ioManager.replyException(loadException);
        }
    }


    /**
     * Processes and executes the user's input.
     */
    public void processInput() {
        // get input string
        String input = ioManager.readLine();

        // form command input
        CommandLineInput commandInput;
        try {
            commandInput = logicManager.getInputParser().parse(input);
        } catch (Throwable ex) {
            ioManager.replyException(ex);
            return;
        }

        // start service
        initiateService(commandInput.getService(logicManager));
    }


    /**
     * Starts the given service.
     *
     * @param service - the service to start.
     */
    private void initiateService(ExecutionService service) {
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
        ioManager.reply(service.getValue());
        service.followUpDispatcher().ifPresent(this::initiateService);
    }


    /**
     * Handles the event when the task of the given ExecutionService failed to
     * execute completely.
     *
     * <p>Information about the exception that occured is displayed.
     *
     * @param service - the service whose task failed.
     */
    private void handleExecutionFailure(ExecutionService service) {
        ioManager.replyException(service.getException());
    }
}
