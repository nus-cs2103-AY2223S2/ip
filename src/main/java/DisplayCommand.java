public class DisplayCommand extends Command{
    public DisplayCommand(String input) {
        super(input);
    }

    public void execute(TaskList storage, Ui ui) {
        ui.toUser("Here you go my brother!" );
        storage.display(ui);
        ui.toUser("Anything else I can do for you top G" );

    }
}
