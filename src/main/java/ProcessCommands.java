public class ProcessCommands {
    protected TaskList list;

    public ProcessCommands(TaskList list) {
        this.list = list;
    }

    public void processAdd(Task t) {
        this.list.addTask(t);
    }

    public void processDelete(int index) {
        Task t = this.list.getTask(index);
        this.list.deleteTask(index);
        System.out.println(String.format("ok, this task has been removed:\n %s", t.toString()));
        System.out.println(String.format("Now you have %d tasks in the list", list.getSize()));
        System.out.println("---------------------------------------");
    }

    public void processList() {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            System.out.println(String.format("%d.%s", i + 1, t.toString()));
        }
        System.out.println("---------------------------------------");
    }

    public void processEvent(String desc, String from, String to) {
        Event event = new Event(desc, from, to);
        list.addTask(event);
        System.out.println(String.format("Sure!, I've added the following events:\n %s", event.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        System.out.println("---------------------------------------");
    }

    public void processDeadline(String desc, String by) {
        Deadline deadline = new Deadline(desc, by);
        list.addTask(deadline);
        System.out.println(String.format("Received, I've added the following deadlines:\n %s", deadline.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        System.out.println("---------------------------------------");
    }

    public void processTodo(String desc) {
        Todo task = new Todo(desc);
        list.addTask(task);
        System.out.println(String.format("alright, I've added the following task:\n %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        System.out.println("---------------------------------------");
    }

    public void processMark(int index) {
        Task task = list.getTask(index);
        task.setDone();
        System.out.println(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        System.out.println("---------------------------------------");
    }

    public void processUnmark(int index) {
        Task task = list.getTask(index);
        task.setUndone();
        System.out.println(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        System.out.println("---------------------------------------");
    }

}
