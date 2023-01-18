import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private class Task {
        private String content;
        private boolean done;

        Task(String content) {
            this.content = content;
            this.done = false;
        }

        public void mark() {
            this.done = true;
        }

        public void unmark() {
            this.done = false;
        }

        @Override
        public String toString() {
            String checkBox = this.done ? "[X] " : "[ ] ";
            return checkBox +  this.content;
        }
    }

    private List<Task> tasks;

    TaskList() {
        this.tasks = new LinkedList<>();
    }

    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String result = "";
        Integer curr = 1;

        for (Task t: tasks) {
            result += curr.toString() + ". " + t + '\n';
            curr++;
        }

        return result;
    }
}
