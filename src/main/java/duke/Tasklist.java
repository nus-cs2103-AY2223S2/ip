package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> storage;

    public Tasklist() {
        storage = new ArrayList<Task>();
    }

    public Tasklist(ArrayList<Task> load) {
        storage = load;
    }

    public void updateTask(String request, int position) {
        switch(request) {
            case "mark":
                storage.get(position).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description);
                break;
            case "unmark":
                storage.get(position).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[" + storage.get(position).getStatusIcon() + "] " + storage.get(position).description);
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:\n" + storage.get(position - 1).toString() + "\nNow you have " + (storage.size() - 1) + " tasks in the list");
                storage.remove(position);
        }
    }
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
    }

    public void addingActivities(String type, String content) {
        switch (type) {
            case "todo":
                Todo todoTask = new Todo(content.substring(5), content);
                storage.add(todoTask);
                System.out.println("Got it. I've added this task:\n  " + todoTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
            case "deadline":
                int position = content.indexOf("/by ");
                Deadline deadlineTask = new Deadline(content.substring(9, position), content, content.substring(position + 4));
                storage.add(deadlineTask);
                System.out.println("Got it. I've added this task:\n  " + deadlineTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
            case "event":
                int position1 = content.indexOf("/from ");
                int position2 = content.indexOf("/to ");
                Event eventTask = new Event(content.substring(6, position1), content, content.substring(position1 + 6, position2), content.substring(position2 + 4));
                storage.add(eventTask);
                System.out.println("Got it. I've added this task:\n  " + eventTask.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                break;
        }
    }

    public void findingActivities (String content) {
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < storage.size(); i++) {
            Task taskObtained = storage.get(i);
            if (taskObtained.getDescription().contains(content)) {
                if (taskObtained instanceof Todo) {
                    System.out.println(index + "." + ((Todo)taskObtained).toString());
                    index++;
                } else if (taskObtained instanceof Deadline) {
                    System.out.println(index + "." + ((Deadline)taskObtained).toString());
                    index++;
                } else {
                    System.out.println(index + "." + ((Event)taskObtained).toString());
                    index++;
                }
            }
        }
    }

    public ArrayList<Task> getList() {
        return storage;
    }
}
