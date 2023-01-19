public class Commands {
    TaskList taskList;

    public Commands(TaskList taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) throws DukeException {
        if (task.getTask().equals("")) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        System.out.println("\tGot it. I've added this task:");
        taskList.addTask(task);
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    public void markTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("Please specify the task number to mark.");
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.getTask(index);
        task.markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }

    public void unmarkTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("Please specify the task number to unmark.");
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
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

    public void addToDoTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = input.split(" ", 2)[1];
        ToDo toDo = new ToDo(description);
        addTask(toDo);
    }

    public void addDeadlineTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (!input.contains(" /by ")) {
            throw new DukeException("\tPlease use the format: deadline <description> /by <time>");
        }
        String[] splitInput = input.split(" ", 2)[1].split(" /by ");
        String description = splitInput[0];
        String by = splitInput[1];
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public void addEventTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new DukeException("\tPlease use the format: event <description> /from <time> /to <time>");
        }
        String[] splitInput = input.split(" ", 2)[1].split(" /from ");
        String description = splitInput[0];
        String[] splitTime = splitInput[1].split(" /to ");
        if (splitTime.length != 2) {
            throw new DukeException("\tPlease use the format: event <description> /from <time> /to <time>");
        }
        String from = splitTime[0];
        String to = splitTime[1];
        Event event = new Event(description, from, to);
        addTask(event);
    }

    public void deleteTask(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new DukeException("Please specify the task number to delete.");
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.getTask(index);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
        taskList.deleteTask(index);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }
}
