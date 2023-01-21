package aqua.logic;

import aqua.logic.command.Command;
import aqua.manager.AppManager;


public class CommandLineInput {
    private final Command command;
    private final ArgumentMap args;


    public CommandLineInput(Command command, ArgumentMap args) {
        this.command = command;
        this.args = args;
    }


    public ExecutionDispatcher getDispatcher(AppManager manager) {
        return command.getDispatcher(args, manager);
    }


    @Override
    public String toString() {
        return String.format("CommandLineInput(cmd=%s args=%s)",
            command.toString(),
            args.toString()
        );
    }
}
