import exception.EmptyDescException;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;

public class TaskList {
    protected ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) System.out.println((i+1) + "." + this.taskList.get(i));
    }

    public void markStatus(String index) throws IOException {
        taskList.get(Integer.parseInt(index) - 1).markStatus(true);
        this.updateDrive();
    }

    public void unMarkStatus(String index) throws IOException {
        taskList.get(Integer.parseInt(index) - 1).markStatus(false);
        this.updateDrive();
    }


    public void addTasks(String[] inputArr, String taskType) throws EmptyDescException, IOException {
        String desc = "variable not initialised";
        StringBuilder sb = new StringBuilder();
        if (inputArr.length == 1) throw new EmptyDescException(taskType, "EmptyDescException");
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
                this.taskList.add(todo);
                this.updateDrive();
                System.out.println("____________________________________________________________");
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + this.taskList.size() + " in the list!");
                System.out.println("____________________________________________________________");
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
                String[] dateTime = sb.toString().split(" ");
                Deadline deadline = new Deadline(desc, dateTime[0], dateTime[1]);
                this.taskList.add(deadline);
                this.updateDrive();
                System.out.println("____________________________________________________________");
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + this.taskList.size() + " in the list!");
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
                this.taskList.add(event);
                this.updateDrive();
                System.out.println("____________________________________________________________");
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + this.taskList.size() + " in the list!");
                System.out.println("____________________________________________________________");
                break;
        }
    }

    public void deleteTasks(int inputIndex) throws IOException {
        Task task = this.taskList.get(inputIndex - 1);
        this.taskList.remove(inputIndex - 1);
        this.updateDrive();
        System.out.println("____________________________________________________________");
        System.out.println("Removing your task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.taskList.size() + " in the list!");
        System.out.println("____________________________________________________________");
    }

//    @SuppressWarnings("uncheckedExceptions");
    //TODO: refactor function
    public void updateDrive() throws IOException {
        Task task;
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter("./data/duke.txt");
        for (int i = 0; i < this.taskList.size(); i++) {
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
                sb.append("|"); sb.append(d.getDateTime());
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
