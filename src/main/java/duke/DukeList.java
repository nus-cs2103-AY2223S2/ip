package duke;

import task.*;
import exception.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;

public class DukeList implements Serializable {
    private ArrayList<Task> list = new ArrayList<>();
    private Ui ui;

    public DukeList(Ui ui) {
        this.ui = ui;
    }

    private String pluralTask (int size) {
        if (size == 1) {
            return " task.";
        } else {
            return " tasks.";
        }
    }

    public void add(String type, String s) {
        Task task;
        if (type.equals("todo")) {
            task = new Todo(s);

        } else if (type.equals("deadline")) {
            String[] arr = s.split(" /by ", 2);
            LocalDate localDate = LocalDate.parse(arr[1]);
            task = new Deadline(arr[0], localDate);

        } else {
            String[] arr = s.split(" /from | /to", 3);
            task = new Event(arr[0], arr[1], arr[2]);
        }
        ui.addStatement("Sure, Imma add that real quick \n" + task);
        list.add(task);
        ui.addStatement("Now you've got " + list.size() + pluralTask(list.size()));
    }

    public void add(Task task) {
        list.add(task);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void findSubString(String subString) {
        DukeList foundList = new DukeList(ui);
        for (Task task : this.list) {
            if (task.hasSubstring(subString)) {
                foundList.add(task);
            }
        }
        if (foundList.isEmpty()) {
            this.ui.addStatement("Sorry man, couldn't find anything with [" + subString + "]");
        } else {
            this.ui.addStatement("So I've found these: \n" + foundList);
        }
    }


    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public void findAndMark(String text, boolean mark) {
        for (Task t : list) {
            if (t.isCorrectTask(text)) {
                t.markOut(mark, ui);
                return;
            }
        }
        ui.addStatement("Sorry, can't find the task!");
    }

    public void delete (int i) throws TaskOutOfRangeException {
        if (i > this.list.size() || i < 0) {
            throw new TaskOutOfRangeException("Yo, I can't find the task at " + i);
        } else {
            Task removedTask = this.list.remove(i - 1);
            ui.addStatement("Got it, this task is gonez: \n" +
                    removedTask + "\n" +
                    "Now you've got " + list.size() + pluralTask(list.size()));
        }
    }

    @Override
    public String toString() {
        String out = "";
        int num = 1;
        for (Task t: list) {
            out += num + ". " + t.toString() + "\n";
            num ++;
        }
        return out;
    }
}

