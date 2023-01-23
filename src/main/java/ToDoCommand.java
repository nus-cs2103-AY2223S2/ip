public class ToDoCommand extends Command {
    private String taskDescription;

    public ToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo newToDo = new ToDo(taskDescription);
        //Adds new task to list of tasks
        tasks.addTask(newToDo);
    }
}
