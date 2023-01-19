public class TaskList {
    private int index;
    private Task[] tasks;

    public TaskList() {
        this.index = 0;
        this.tasks = new Task[100];
    }

    public String addTask(Task task) {
        this.tasks[this.index] = task;
        String str = "Got it! This task has been added:\n";
        str += this.printTask(index);
        this.index++;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.\n";
        return str;
    }

    public String deleteTask(String deleteIndex) {
        int index = Integer.parseInt(deleteIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        String str = "Got it! This task has been removed:\n";
        str += this.printTask(index);
        this.index--;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.\n";
        for (int i = index ; i < 99 ; i++) {
            this.tasks[i] = this.tasks[i + 1];
        }
        this.tasks[99] = null;
        return str;
    }

    public String markTask(String markIndex) {
        int index = Integer.parseInt(markIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        this.tasks[index].mark();
        String str = "Great job! This task has been marked as done:\n";
        str += printTask(index);
        return str;
    }

    public String unMarkTask(String unmarkIndex) {
        int index = Integer.parseInt(unmarkIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        this.tasks[index].unMark();
        String str = "Noted! This task has been marked as undone:\n";
        str += printTask(index);
        return str;
    }

    public String printTask(int index) {
        return tasks[index].toString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < index; i++) {
            str += (i + 1) + ". " + this.printTask(i);
        }
        return str;
    }
}
