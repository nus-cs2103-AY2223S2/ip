package command;

public class InvalidCommand extends Command{

    public InvalidCommand() {

    }

    @Override
    public void execute() {
        System.out.println("This is an invalid input lol.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
