import java.io.File;
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

    public void loadTasks(String[] inputArr, String taskType) {
        switch (taskType) {
            case "T":
                Todo todo = new Todo(inputArr[2]);
                this.taskList.add(todo); this.numOfTasks++;
                todo.markStatus(inputArr[1]);
                break;
            case "D":
                Deadline deadline = new Deadline(inputArr[2], inputArr[3]);
                this.taskList.add(deadline); this.numOfTasks++;
                deadline.markStatus(inputArr[1]);
                break;
            case "E":
                Event ev = new Event(inputArr[2], inputArr[3], inputArr[4]);
                this.taskList.add(ev); this.numOfTasks++;
                ev.markStatus(inputArr[1]);
        }
    }


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
                Todo todo = new Todo(sb.toString().trim());
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
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                // TODO: Code can be shortened
                String[] dateTime = sb.toString().split(" ");
                String[] date = dateTime[0].split("/");
                sb.setLength(0);
                sb.append(date[2]); sb.append("-");
                if (date[1].length() == 1) sb.append("0"); sb.append(date[1]); sb.append("-");
                if (date[0].length() == 1) sb.append("0"); sb.append(date[0]);
                Deadline deadline = new Deadline(desc, sb.toString(), dateTime[1]);
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
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Event event = new Event(desc, from, sb.toString().trim());
                this.taskList.add(event); this.numOfTasks++;
                this.updateDrive();
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
        }
    }

    public void deleteTasks(int inputIndex) throws IOException {
        Task task = this.taskList.get(inputIndex-1);
        this.taskList.remove(inputIndex-1);
        this.numOfTasks--;
        this.updateDrive();
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
            sb.append(taskName); sb.append("|");
            if (task.getStatusIcon().equals("X")) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append("|");
            sb.append(task.getDescription());
            if (taskName.equals("D")) {
                Deadline d = (Deadline) task;
                sb.append("|"); sb.append(d.getBy());
            } else if (taskName.equals("E")) {
                Event ev = (Event) task;
                sb.append("|"); sb.append(ev.getFrom()); sb.append("|"); sb.append(ev.getTo());
            }
            sb.append("\n");
        }
        fw.write(sb.toString().trim());
        fw.close();
    }

}
