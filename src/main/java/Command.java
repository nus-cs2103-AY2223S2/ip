public interface Command {
    public void execute(TaskList list);

    public boolean isExit();
}
