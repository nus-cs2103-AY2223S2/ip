package duke;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Functions {
    /**
     * Represents the available functions of the Duke program
     */
    TaskList tl;
    Storage st;
    Pane outputLayout;

    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public Functions(TaskList tl, Storage st) {
        this.tl = tl;
        this.st = st;
    }

    public void setOutputLayout(Pane outputLayout) {
        this.outputLayout = outputLayout;
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Method to define function of todo command. Creates todo task
     *
     * @param inp Description of todo task
     */
    public void todo(String inp) {
        ToDos td = new ToDos(false, inp);
        String s = tl.addTask(td);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }

    /**
     * Method to define function of deadline command. Create deadline task
     *
     * @param inp Description of deadline task. Define deadline after "/by".
     *            Example: deadline task1 /by 2023-12-12 12:12
     * @throws DukeException
     */
    public void deadline(String des, String end) {
        Deadlines dl = new Deadlines(false, des, end);
        String s = tl.addTask(dl);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }

    /**
     * Method to define function of event command. Create event task
     *
     * @param inp Description of event task. Define event with "/from ... /to ...".
     *            Example: deadline task1 /from 12/12/2023 12:12 /to 12/12/2023 23:59
     */
    public void events(String des, String start, String end) {
        Events ev = new Events(false, des, start, end);
        String s = tl.addTask(ev);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }

    /**
     * Method to define function of list command
     */
    public void list() {
        String h = "Here are the tasks in your list:\n";
        String l = "";
        for (int i = 0; i < tl.count(); i++) {
            String s = i + 1 + "." + tl.getTask(i).printStatus();
            l += s;
        }
        outputLayout.getChildren().add(getDialogLabel(h + l));
    }

    /**
     * Method to define function of mark command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
    public void mark(String inp) {
        int index = Integer.parseInt(inp) - 1;
        Task t = tl.getTask(index);
        t.setStatus(true);
        String h = "Nice! I've marked this task as done:\n";
        outputLayout.getChildren().add(getDialogLabel(h + t.printStatus()));
        this.st.save(tl);
    }

    /**
     * Method to define function of unmark command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
    public void unmark(String inp) {
        int index = Integer.parseInt(inp) - 1;
        Task t = tl.getTask(index);
        t.setStatus(false);
        String h = "OK, I've marked this task as not done yet:\n";
        outputLayout.getChildren().add(getDialogLabel(h + t.printStatus()));
        this.st.save(tl);
    }

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
    public void delete(String inp) {
        int index = Integer.parseInt(inp) - 1;
        String h = "Noted. I've removed this task:\n";
        String des = tl.getTask(index).printStatus();
        tl.removeTask(index);
        String c = tl.printCount();
        outputLayout.getChildren().add(getDialogLabel(h + des + c));
        this.st.save(tl);
    }

    /**
     * Method to allow user to search for task by description.
     *
     * @param s User input
     */
    public void find(String query) {
        boolean flag = false;
        boolean printed = false;
        int i = 1;
        String h = "";
        String task = "";

        for (Task t : tl.iterable()) {
            if (t.getDes().contains(query)) {
                flag = true;
                if (flag && !printed) {
                    h = "Here are the matching tasks in your list:\n";
                    printed = true;
                }
                task += i + ".";
                task += t.printStatus();
                i++;
            }
        }
        if (!flag) {
            String s = "No matching tasks are found in your list\n";
            outputLayout.getChildren().add(getDialogLabel(s));
        } else {
            String t = "Search done!\n";
            outputLayout.getChildren().add(getDialogLabel(h + task + t));
        }
    }

    /**
     * Method to define function of bye command
     *
     * @return boolean flag to indicate when program should stop
     */
    public boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }
}
