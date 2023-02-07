package duke.command;

public class ByeCommand extends Command{
    
    @Override
    public String toString() {
        return String.format("ByeCommand");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }

    @Override
    public String execute() {
        return ui.printByeMessage();
    }
}
