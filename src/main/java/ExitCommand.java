public class ExitCommand extends Command {

    ExitCommand() {
        super(null, true);
    }

    @Override
    public void execute() {
        System.out.println("Bye. Have a nice day!");
    }

}
