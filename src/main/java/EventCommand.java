public class EventCommand extends Command {
    private String message;
    private String from;
    private String to;

    public EventCommand(String input) {
        String[] checkerslash = input.split("/");
        String[] checkerevent = checkerslash[0].split("event ");
        String[] checker4 = checkerslash[1].split("from ");
        String[] checker5 = checkerslash[2].split("to ");
        this.message = checkerevent[1];
        this.from = checker4[1];
        this.to = checker5[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Event t = new Event(message, from, to);
        tasks.addToList(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
        tasks.statement();
        return true;
    }
}