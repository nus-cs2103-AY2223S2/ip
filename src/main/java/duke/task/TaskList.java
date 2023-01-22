package duke.task;
import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<String> listOfCommands = new ArrayList<>();
    protected ArrayList<Task> listDataBase = new ArrayList<>();

    public TaskList(ArrayList<String> commands) throws DukeException {
            this.listOfCommands = commands;
    }

    public TaskList() {
    }

    public void printList() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < listDataBase.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(listDataBase.get(i).toString());
        }
    }
    public void unmark(int taskNumber) {
        Task taskName = listDataBase.get(taskNumber - 1);
        taskName.unMark();
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(taskName.toString());
    }
    public void mark(int taskNumber) {
        Task taskName = listDataBase.get(taskNumber - 1);
        taskName.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName.toString());
    }
    public void todo(String[] s) throws DukeException {
        String taskDescription = "";
        if (s.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        for (int j = 1; j < s.length; j++) {
            taskDescription += s[j];
            taskDescription += " ";
        }
        ToDos taskName = new ToDos(taskDescription);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskName.toString());
        listDataBase.add(taskName);
        System.out.println("Now you have " + listDataBase.size() + " tasks in the list.");
    }

    public void deadline(String[] s) throws DukeException {
        String taskDescription = "";
        boolean isTime = false;
        String time = "";
        if (s.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        for (int j = 1; j < s.length; j++) {
            if (s[j].equals("/by")) {
                isTime = true;
            } else if (isTime) {
                if (j + 1 == s.length) {
                    time += s[j];
                } else {
                    time += s[j];
                    time += " ";
                }
            } else {
                taskDescription += s[j];
                taskDescription += " ";
            }
        }
        Deadlines taskName = new Deadlines(taskDescription, time);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskName.toString());
        listDataBase.add(taskName);
        System.out.println("Now you have " + listDataBase.size()+ " tasks in the list.");
    }
    public void event(String[] s) throws DukeException {
        String taskDescription = "";
        boolean isStartTime = false;
        boolean isEndTime = false;
        String startTime = "";
        String endTime = "";
        if (s.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        for (int j = 1; j < s.length; j++) {
            if (s[j].equals("/from")) {
                isStartTime = true;
            } else if (isStartTime && !s[j].equals("/to") && !isEndTime) {
                startTime += s[j];
                startTime += " ";
            } else if (s[j].equals("/to")) {
                isEndTime = true;
            } else if (isEndTime) {
                if (j + 1 == s.length) {
                    endTime += s[j];
                } else {
                    endTime += s[j];
                    endTime += " ";
                }
            } else {
                taskDescription += s[j];
                taskDescription += " ";
            }
        }
        Events taskName = new Events(taskDescription, startTime, endTime);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskName.toString());
        listDataBase.add(taskName);
        System.out.println("Now you have " + listDataBase.size() + " tasks in the list.");
    }

    public void delete(int taskNumberDelete) throws DukeException {
        Task currentTask = listDataBase.get(taskNumberDelete - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.toString());
        listDataBase.remove(taskNumberDelete - 1);
        System.out.println("Now you have " + listDataBase.size() + " tasks in the list");
    }
    public ArrayList<Task> getDataBase() {
        return this.listDataBase;
    }
    public String printCommands(int currIteration) {
        if (currIteration < listOfCommands.size()) {
            return listOfCommands.get(currIteration);
        }
        return null;
    }
}
