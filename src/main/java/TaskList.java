import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int numOfTasks;
    protected String filePath;
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numOfTasks = 0;
        this.filePath = "./data/duke.txt";
        // initialisation check;

    }

    public void printList() {
        for (int i = 0; i < this.numOfTasks; i++) System.out.println((i+1) + "." + this.taskList.get(i));
    }

    public void markStatus(String index) {
        taskList.get(Integer.parseInt(index) - 1).markStatus(true);
    }

    public void unMarkStatus(String index) {
        taskList.get(Integer.parseInt(index) - 1).markStatus(false);
    }

    public void loadTasks() {}


    public void addTasks(String[] inputArr, String taskType) throws EmptyDescException, IOException {
        String desc = "variable not initialised";
        StringBuilder sb = new StringBuilder();
        if (inputArr.length == 1) throw new EmptyDescException(taskType, "test");
        switch (taskType) {
            case "todo":
                // task is from index 1
                for (int i = 1; i < inputArr.length; i++) {
                    sb.append(inputArr[i]);
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Todo todo = new Todo(sb.toString());
                this.taskList.add(todo); this.numOfTasks++;
                this.updateDrive();
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
            case "deadline":
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Deadline deadline = new Deadline(desc, sb.toString());
                this.taskList.add(deadline); this.numOfTasks++;
                this.updateDrive();
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
            case "event":
                String from = "local variable not initialised";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/from")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    } else if (inputArr[i].equals("/to")) {
                        from = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Event event = new Event(desc, from, sb.toString());
                this.taskList.add(event); this.numOfTasks++;
                this.updateDrive();
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
        }
    }

    public void deleteTasks(int inputIndex) {
        Task task = this.taskList.get(inputIndex-1);
        this.taskList.remove(inputIndex-1);
        this.numOfTasks--;
        System.out.println("Removing your task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.numOfTasks + " in the list!");
    }
//    @SuppressWarnings("uncheckedExceptions");
    public void updateDrive() throws IOException {
        Task task;
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < this.numOfTasks; i++) {
            task = this.taskList.get(i);
            String taskName = task.getName();
//            System.out.println(taskName);
            sb.append(taskName); sb.append(" | ");
            sb.append(task.getStatusIcon()); sb.append(" | ");
            sb.append(task.getDescription());
            if (taskName.equals("D")) {
                Deadline d = (Deadline) task;
                sb.append(" | "); sb.append(d.getBy());
            } else if (taskName.equals("E")) {
                Event ev = (Event) task;
                sb.append(" | "); sb.append(ev.getFrom()); sb.append(" | "); sb.append(ev.getTo());
            }
            sb.append("\n");
        }
        fw.write(sb.toString().trim());
        fw.close();
    }

}
