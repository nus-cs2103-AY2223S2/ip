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
            listString += String.format("%d. %s\n",i+1, task.toString());
        }
        return listString;
    }

    public void addTask(String content) {
        this.list[numTask] = new Task(content);
        this.numTask++;
    }
}
