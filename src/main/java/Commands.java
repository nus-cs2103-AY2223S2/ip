public class Commands {
    TaskList taskList;

    public Commands(TaskList taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        System.out.println("\tGot it. I've added this task:");
        taskList.addTask(task);
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    public void markTask(int index) {
        Task task = taskList.getTask(index);
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }

    public void unmarkTask(int index) {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        System.out.println("\tNice! I've marked this task as not done:");
        System.out.println("\t\t" + task.toString());
    }

    public void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("\t\t" + (i + 1) + "." + t.toString());
        }
    }

    public void addToDoTask(String input) {
        String description = input.split(" ", 2)[1];
        ToDo toDo = new ToDo(description);
        addTask(toDo);
    }

    public void addDeadlineTask(String input) {
        String[] splitInput = input.split(" ", 2)[1].split(" /by ");
        String description = splitInput[0];
        String by = splitInput[1];
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public void addEventTask(String input) {
        String[] splitInput = input.split(" ", 2)[1].split(" /from ");
        String description = splitInput[0];
        String[] splitTime = splitInput[1].split(" /to ");
        String from = splitTime[0];
        String to = splitTime[1];
        Event event = new Event(description, from, to);
        addTask(event);
    }
}
