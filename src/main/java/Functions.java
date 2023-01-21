import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
public class Functions {
    TaskList ls;

    public Functions(TaskList ls) {
        this.ls = ls;
    }

    public void list(String inp) {
        String[] s = inp.split(" ");

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.count(); i++) {
            System.out.print(i + 1 + ".");
            ls.getTask(i).printStatus();
        }
    }

    public boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public void mark(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("I don't know which task to mark...");
        }
        int index = Integer.parseInt(s[1]) - 1;
        if (index > ls.count()) { //this not working
            throw new DukeException("Task not found...");
        }
        Task t = ls.getTask(index);
        t.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        t.printStatus();
    }

    public void unmark(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("I don't know which task to mark...");
        }
        int index = Integer.parseInt(s[1]) - 1;
        if (index > ls.count()) { //this not working
            throw new DukeException("Task not found...");
        }
        Task t = ls.getTask(index);
        t.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        t.printStatus();
    }

    public void delete(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("I don't know which task to delete...");
        }
        int index = Integer.parseInt(s[1]) - 1;
        if (index > ls.count()) {
            throw new DukeException("Task not found...");
        }
        System.out.println("Noted. I've removed this task:");
        ls.getTask(index).printStatus();
        ls.removeTask(index);
        ls.printCount();
    }

    public void todo(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        ToDos td = new ToDos(false, taskDes);
        ls.addTask(td);
    }

    public void deadline(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        Deadlines dl = new Deadlines(false, taskDes);
        ls.addTask(dl);
    }

    public void events(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        Events ev = new Events(false, taskDes);
        ls.addTask(ev);
    }
}
