package aqua.logic;

import aqua.logic.command.Command;


public class CommandLineInput {
    private final Command command;
    private final ArgumentMap argumentMap;


    public CommandLineInput(Command command, ArgumentMap argumentMap) {
        this.command = command;
        this.argumentMap = argumentMap;
    }


    @Override
    public String toString() {
        return String.format("CommandLineInput(cmd=%s args=%s)",
            command.toString(),
            argumentMap.toString()
        );
    }
}
