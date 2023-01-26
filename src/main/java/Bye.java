public class Bye extends Commands {
    public Bye(String str) {
    }
    @Override
    public void execute(TaskList list) {
        Duke.offBot = true;
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
}
