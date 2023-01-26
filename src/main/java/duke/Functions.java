package duke;

import java.util.Arrays;

public class Functions {
    TaskList tl;
    Storage st;

    public Functions(TaskList tl, Storage st) {
        this.tl = tl;
        this.st = st;
    }

    public void list() throws DukeException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.count(); i++) {
            System.out.print(i + 1 + ".");
            tl.getTask(i).printStatus();
        }
    }

    public void find(String s) {
        String query = s.split(" ")[1];
        boolean flag = false;
        int i = 1;
        for (Task t : tl.iterable()) {
            if (t.getDes().contains(query)) {
                flag = true;
                if (flag) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.print(i + ".");
                t.printStatus();
                i++;
            }
        }
        if (!flag) {
            System.out.println("No matching tasks are found in your list");
        } else {
            System.out.println("Search done!");
        }
    }

    public boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public void mark(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new markException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        Task t = tl.getTask(index);
        t.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        t.printStatus();
        this.st.save(tl);
    }

    public void unmark(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new markException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        Task t = tl.getTask(index);
        t.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        t.printStatus();
        this.st.save(tl);
    }

    public void delete(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new deleteException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        tl.getTask(index).printStatus();
        tl.removeTask(index);
        tl.printCount();
        this.st.save(tl);
    }

    public void todo(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new todoException();
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        ToDos td = new ToDos(false, taskDes);
        tl.addTask(td);
        this.st.save(tl);
    }

    public void deadline(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new deadlineException();
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        System.out.println(taskDes);
        Deadlines dl = new Deadlines(false, taskDes);
        tl.addTask(dl);
        this.st.save(tl);
    }

    public void events(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length < 2) {
            throw new eventException();
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        Events ev = new Events(false, taskDes);
        tl.addTask(ev);
        this.st.save(tl);
    }
}
