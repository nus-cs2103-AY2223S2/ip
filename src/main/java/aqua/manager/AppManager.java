package aqua.manager;

import aqua.exception.LoadException;
import aqua.logic.CommandLineInput;
import aqua.logic.ExecutionService;
import aqua.logic.command.ListCommand;


/**
 * The executor of ExecutionService.
 */
public class AppManager {
    private final LogicManager manager;
    private final IoManager ioManager;


    public AppManager(LogicManager manager, IoManager uiManager) {
        this.manager = manager;
        this.ioManager = uiManager;
    }


    public void start() {
        ioManager.greet();
        try {
            manager.load();
            ioManager.replyLoadSuccess();
            initiateService(new ListCommand().getService(null, manager));
        } catch (LoadException loadException) {
            ioManager.replyException(loadException);
        }
    }


    public void processInput() {
        // get input string
        String input = ioManager.readLine();

        // form command input
        CommandLineInput commandInput;
        try {
            commandInput = manager.getInputParser().parse(input);
        } catch (Throwable ex) {
            ioManager.replyException(ex);
            return;
        }

        // start service
        initiateService(commandInput.getService(manager));
    }


    private void initiateService(ExecutionService service) {
        service.setOnSucceeded(s -> handleExecutionSuccess(service));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    private void handleExecutionSuccess(ExecutionService service) {
        ioManager.reply(service.getValue());
        service.followUpDispatcher().ifPresent(this::initiateService);
    }


    private void handleExecutionFailure(ExecutionService service) {
        ioManager.replyException(service.getException());
    }
}
