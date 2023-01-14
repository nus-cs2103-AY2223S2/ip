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

    public Task addTask(String content) {
        String[] contentSplit = content.split(" ",2);
        String taskType = contentSplit[0];
        content = contentSplit[1];
        Task taskToAdd;

        if (taskType.equals("todo")) {
            taskToAdd = new Todo(content);
        } else if (taskType.equals("deadline")) {
            String[] dateSplit = content.split("/by");
            content = dateSplit[0];
            String dueDate = dateSplit[1];
            taskToAdd = new Deadline(content,dueDate);
        } else {
            String[] dateSplit = content.split("/from");
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
}
