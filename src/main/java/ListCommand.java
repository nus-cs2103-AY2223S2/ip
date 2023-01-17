public class ListCommand implements Command{
    public ListCommand() {
    }

    public void execute(TaskList list) {
        list.list();
    }
    
    public boolean isExit() {
        return false;
    }
}
