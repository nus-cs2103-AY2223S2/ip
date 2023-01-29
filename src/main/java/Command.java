public interface Command {
    public void execute(TaskList taskList);
    public boolean isExit();
}
