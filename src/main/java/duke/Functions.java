package duke;
import java.util.Arrays;

public class Functions {
    TaskList tl;
    Storage st;

    public Functions(TaskList ls, Storage st) {
        this.tl = ls;
        this.st = st;
    }

    public void list(String inp) throws DukeException{
        String[] s = inp.split(" ");

        /*
        if (s.length>1) {
            String[] temp = Arrays.copyOfRange(s, 1, s.length);
            String datetime = String.join(" ", temp);
            String[] x = datetime.split(" /find ");
            //check date format
            try {
                LocalDateTime query = LocalDateTime.parse(x[0]);
                //search and only print those that match date
                for (int i = 0; i < ls.count(); i++) {
                    Task ts = ls.getTask(i);
                    if (ts instanceof TimedTask) {
                        if (query.isEqual(((TimedTask) ts).end) || query.isEqual(((TimedTask) ts).start)) {
                            ts.printStatus();
                        }
                    }
                }
                //report end of search
                System.out.println("Search done!");
            } catch (DateTimeParseException e) {
                System.out.println("Date Time format is dd/mm/yyyy hh:mm");
            }


        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < ls.count(); i++) {
                System.out.print(i + 1 + ".");
                ls.getTask(i).printStatus();
            }
        }
        */

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.count(); i++) {
            System.out.print(i + 1 + ".");
            tl.getTask(i).printStatus();

        }
    }

    public boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public void mark(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new markException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        Task t = tl.getTask(index);
        t.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        t.printStatus();
        this.st.save(tl);
    }

    public void unmark(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new markException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        Task t = tl.getTask(index);
        t.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        t.printStatus();
        this.st.save(tl);
    }

    public void delete(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new deleteException();
        }
        int index = Integer.parseInt(s[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        tl.getTask(index).printStatus();
        tl.removeTask(index);
        tl.printCount();
        this.st.save(tl);
    }

    public void todo(String inp) throws DukeException{
        String[] s = inp.split(" ");
        if (s.length<2) {
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
        if (s.length<2) {
            throw new deadlineException();
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        Deadlines dl = new Deadlines(false, taskDes);
        tl.addTask(dl);
        this.st.save(tl);
    }

    public void events(String inp) throws DukeException {
        String[] s = inp.split(" ");
        if (s.length<2) {
            throw new eventException();
        }
        String[] temp = Arrays.copyOfRange(s, 1, s.length);
        String taskDes = String.join(" ", temp);
        Events ev = new Events(false, taskDes);
        tl.addTask(ev);
        this.st.save(tl);
    }
}
