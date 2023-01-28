package Duke;

public class Bye extends Commands {
    public Bye(String str) {
    }
    @Override
    public void execute(TaskList list) {
        Duke.offBot = true;
        System.out.println("Duke.Bye. Hope to see you again soon!");
        return;
    }
}
