import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Functions {
    TaskList ls;

    public Functions(TaskList ls) {
        this.ls = ls;
    }

    public void list(String inp) {
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
        for (int i = 0; i < ls.count(); i++) {
            System.out.print(i + 1 + ".");
            ls.getTask(i).printStatus();
        }
    }

    public boolean bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public void save() {
        String pathname = "task.txt";
        File f = new File(pathname);
        //create file if not created already
        try {
            if (f.createNewFile()) {}
        } catch (IOException e) {
            System.out.println(e);
        }

        //overwrite all in tasklist into file
        try{
            FileWriter fw = new FileWriter(pathname);
            for (int i = 0; i < ls.count(); i++) {
                fw.write(ls.getTask(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
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
        this.save();
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
        this.save();
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
        this.save();
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
        this.save();
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
        this.save();
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
        this.save();
    }
}
