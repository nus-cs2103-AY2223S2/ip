package task;

import exception.EmptyDescException;
import task.Deadline;
import task.Event;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;

public class TaskList {
    protected ArrayList<Task> list;
    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void printList() {
        for (int i = 0; i < this.list.size(); i++) System.out.println((i+1) + "." + this.list.get(i));
    }

    public int listSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void markStatus(String index) throws IOException {
        list.get(Integer.parseInt(index) - 1).markStatus(true);
        this.updateDrive();
    }

    public void unMarkStatus(String index) throws IOException {
        list.get(Integer.parseInt(index) - 1).markStatus(false);
        this.updateDrive();
    }


    public Task addTasks(String[] inputArr, String taskType) throws EmptyDescException, IOException {
        String desc = "variable not initialised";
        Task task = null; // Variable might not be initialised
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
                this.list.add(todo);
                this.updateDrive();
                task = todo;
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
                this.list.add(deadline);
                this.updateDrive();
                task = deadline;
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
                this.list.add(event);
                this.updateDrive();
                task = event;
                break;
        }
        return task;
    }

    public Task deleteTasks(int inputIndex) throws IOException {
        Task task = this.list.get(inputIndex - 1);
        this.list.remove(inputIndex - 1);
        this.updateDrive();
        return task;
    }

//    @SuppressWarnings("uncheckedExceptions");
    //TODO: refactor function
    public void updateDrive() throws IOException {
        Task task;
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter("./data/duke.txt");
        for (int i = 0; i < this.list.size(); i++) {
            task = this.list.get(i);
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
