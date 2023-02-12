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

    public String printList() {
        String result = "Here are the tasks in your list\n ";
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < listDataBase.size(); i++) {
            result += i + 1 + ".";
            //System.out.print(i + 1 + ".");
            result += listDataBase.get(i).toString() + "\n";
            //System.out.println(listDataBase.get(i).toString());
        }
        return result;
    }

    public String unmark(int taskNumber) {
        assert(taskNumber > 0);
        Task taskName = listDataBase.get(taskNumber - 1);
        taskName.unMark();
        String result = "OK, I've marked this task as not done yet \n";
        //System.out.println("OK, I've marked this task as not done yet");
        result += taskName.toString();
        return result;
        //System.out.println(taskName.toString());
    }

    public String mark(int taskNumber) {
        assert(taskNumber > 0);
        Task taskName = listDataBase.get(taskNumber - 1);
        taskName.markAsDone();
        String result = "Nice! I've marked this task as done:\n";
        //System.out.println("Nice! I've marked this task as done:");
        result += taskName.toString();
        return result;
        //System.out.println(taskName.toString());
    }

    public String todo(String[] s) throws DukeException {
        String taskDescription = "";
        if (s.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        assert (s.length >= 2);
        for (int j = 1; j < s.length; j++) {
            taskDescription += s[j];
            taskDescription += " ";
        }
        ToDos taskName = new ToDos(taskDescription);
        String result = "Got it. I've added this task:\n";
        //System.out.println("Got it. I've added this task:");
        result += taskName.toString() + "\n";
        //System.out.println(taskName.toString());
        listDataBase.add(taskName);
        result += "Now you have " + listDataBase.size() + " tasks in the list.";
        //System.out.println("Now you have " + listDataBase.size() + " tasks in the list.");
        return result;
    }

    public String deadline(String[] s) throws DukeException {
        String taskDescription = "";
        boolean isTime = false;
        String time = "";
        if (s.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        assert (s.length >= 2);
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
        String result = "Got it. I've added this task:\n";
        //System.out.println("Got it. I've added this task:");
        result += taskName.toString() + "\n";
        //System.out.println(taskName.toString());
        listDataBase.add(taskName);
        result += "Now you have " + listDataBase.size() + " tasks in the list.";
        //System.out.println("Now you have " + listDataBase.size() + " tasks in the list.");
        return result;
    }

    public String event(String[] s) throws DukeException {
        String taskDescription = "";
        boolean isStartTime = false;
        boolean isEndTime = false;
        String startTime = "";
        String endTime = "";
        if (s.length < 2) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
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
        String result = "Got it. I've added this task:\n";
        result += taskName + "\n";
        listDataBase.add(taskName);
        result += "Now you have " + listDataBase.size() + " tasks in the list.";
        return result;
    }

    public String delete(int taskNumberDelete) {
        assert(taskNumberDelete > 0);
        Task currentTask = listDataBase.get(taskNumberDelete - 1);
        String result = "Noted. I've removed this task:\n";
        result += currentTask.toString() + "\n";
        listDataBase.remove(taskNumberDelete - 1);
        result += "Now you have " + listDataBase.size() + " tasks in the list";
        return result;
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
    public String find(String word) {
        assert(word.length() > 0);
        int count = 1;
        String result = "Here are the matching tasks in your list:\n";
        //System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < listDataBase.size(); i++) {
            Task task = listDataBase.get(i);
            if (task.find(word)) {
                result += count;
                result += '.';
                result += task.toString();
                //System.out.print(count);
                //System.out.print('.');
                //System.out.println(task.toString());
                count++;
            }
        }
        return result;
    }
}
