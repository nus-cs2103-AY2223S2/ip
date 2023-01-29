package duke;

import duke.exception.InvalidFormatException;
import duke.task.Task;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskList {
    private final Ui ui;
    private final Storage storage;
    public static LinkedList<Task> tasks;

    public TaskList (Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        tasks = new LinkedList<>();
    }

    public void init(Parser p) {
        Scanner sc;
        try {
            sc = new Scanner(storage.log);
            while (sc.hasNext()) {
                String row = sc.nextLine();
                tasks.add(Task.factory(
                        row.charAt(1),
                        row.charAt(4),
                        row.substring(7),
                        p
                ));
            }
        } catch (IOException e) {
            System.out.println("Error: unable to access " + storage.log.getAbsoluteFile());
        } catch (InvalidFormatException e) {
            System.out.println("Error in logfile, please check or delete it");
        }
    }

    public String[] toStringList() {
        String[] output = new String[tasks.size()];

        int n = 1 + (int) Math.floor(Math.log10(tasks.size()));
        int i = 0;
        for (Task task : tasks) {
            output[i] = String.format("%" + n + "d", i + 1).replace(' ', '0')
                    + ". "
                    + task.toString();
            i++;
        }

        return output;
    }

    public void printStatus(String text, Task t) {
        storage.update(tasks);
        String[] output = {text, t.toString(), String.format("Now you have %d task(s) in the list.", tasks.size())};
        ui.print(output);
    }

    public void add(Task t) {
        tasks.add(t);
        printStatus("Got it. I've added this task:", t);
    }

    public void setDone(int index, boolean done) {
        Task t = tasks.get(index - 1);
        t.setDone(done);
        if (done) {
            printStatus("Nice, I've marked this task as done:", t);
        } else {
            printStatus("Ok, I've marked this task as not done:", t);
        }
    }

    public void delete(int index) {
        Task t = tasks.remove(index - 1);
        printStatus("Noted. I've removed this task:", t);
    }
}
