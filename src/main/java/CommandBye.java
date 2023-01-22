public class CommandBye extends Command {
    @Override
    public void handle() {
        Duke.ui.print("Goodbye, then!");
        Duke.loopEnd = true;
    }
}
