package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static String printList() {
        String temp = "";
        for(int i = 0; i < list.size() - 1; i++) {
            temp = temp + (i+1) + ". " + list.get(i).toString() + "\n";
      }
       return temp;
    }

    public static int getLength() {
        return list.size();
    }

    //Takes ith number task as argument
    public static String mark(int num) {
        list.get(num - 1).toggleTrue();
        return "Nice! I've marked this task as done:\n" + list.get(num - 1).toString();
    }

    public static String unmark(int num) {
        list.get(num - 1).toggleFalse();
        return "OK, I've marked this task as not done yet:\n" + list.get(num - 1).toString();
    }

    public static String delete(int num) {
        String temp = "Noted. I've removed this task:\n" + "  " + list.get(num-1).toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
        list.remove(num-1);
        return temp;

    }

    public static String find(String keyword) {
        int index = 0;
        String tempstr = "";
        for (Task t : list) {
            String[] sample = t.toString().split(" ");
            List<String> temp = new ArrayList<>(Arrays.asList(sample));

            if (temp.contains(keyword)) {
                index++;
                tempstr = tempstr + (index + "." + t.toString());
            }
        }
        if (index == 0) {
            tempstr = ("Could not find the given word");
        }
        return tempstr;
    }

    public static String add_to_list(String str) throws InvalidCommandException, NoDescriptionException {

        if ((str.split(" ", 2).length == 1)) {
            throw new NoDescriptionException();
        }
        else if ((str.split(" ", 2)[0]).equals("todo")) {

            list.add(new Todo(str));
        }
        else if ((str.split(" ", 2)[0]).equals("deadline")) {
            list.add(new Deadline(str));
        }
        else if ((str.split(" ", 2)[0]).equals("event")) {
            list.add(new Event(str));
        }
        else {
            throw new InvalidCommandException();
        }
        int size = list.size();
        return "Got it. I've added this task:\n"
        + "  " + list.get(size-1).toString() + "\n" + "Now you have " + size + " tasks in the list";
    }


}

