public class TaskList {
    private Task[] list = new Task[100];
    private int numTask = 0;

    TaskList() {

    }

    @Override
    public String toString() {
        String listString = "";
        for (int i = 0; i < numTask; i++) {
            Task task = list[i];
            listString += String.format("%d.%s\n", i+1, task.toString());
        }
        return listString;
    }

    public Task addTask(String content) throws InvalidTodoException, InvalidDeadlineException, InvalidEventException {
        String[] contentSplit = content.split(" ",2);
        String taskType = contentSplit[0];

        Task taskToAdd;

        if (taskType.equals("todo")) {

            if (contentSplit.length == 1) {
                throw new InvalidTodoException();
            }

            content = contentSplit[1];
            taskToAdd = new Todo(content);
        } else if (taskType.equals("deadline")) {
            if (contentSplit.length == 1) {
                throw new InvalidDeadlineException("The description of a deadline task cannot be empty\n");
            }
            content = contentSplit[1];

            String[] dateSplit = content.split(" /by ");

            if (dateSplit.length != 2) {
                throw new InvalidDeadlineException("The description or date of a deadline task cannot be empty\n");
            }

            content = dateSplit[0];
            String dueDate = dateSplit[1];
            taskToAdd = new Deadline(content,dueDate);
        } else {
            if (contentSplit.length == 1) {
                throw new InvalidEventException("The description of an event task cannot be empty\n");
            }

            content = contentSplit[1];
            String[] dateSplit = content.split(" /from ");

            if (dateSplit.length != 2) {
                throw new InvalidEventException("The description or date of an event task cannot be empty\n");
            }

            content = dateSplit[0];
            String fromToDate = dateSplit[1];
            taskToAdd = new Event(content, fromToDate);
        }
        this.list[numTask] = taskToAdd;
        this.numTask++;

        return taskToAdd;
    }

    public Task getTask(int taskNum) {
        return list[taskNum-1];
    }

    public void markTask(String action, int taskNum) {
        Task task = this.getTask(taskNum);
        task.setDoneStatus(action.equals("mark") ? true : false);
    }

    public int getTaskCount() {
        return this.numTask;
    }
}
