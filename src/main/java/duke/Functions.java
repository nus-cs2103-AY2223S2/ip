package duke;

import java.util.Arrays;

public class Functions {
    /**
     * Represents the available functions of the Duke program
     */
    TaskList tl;
    Storage st;

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

    /**
     * Method to define function of list command
     *
     * @throws DukeException
     */
    public void list() throws DukeException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tl.count(); i++) {
            System.out.print(i + 1 + ".");
            tl.getTask(i).printStatus();
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

    /**
     * Method to define function of mark command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
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

    /**
     * Method to define function of unmark command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
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

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
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

    /**
     * Method to define functino of todo command. Creates todo task
     *
     * @param inp Description of todo task
     * @throws DukeException
     */
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

    /**
     * Method to define functino of deadline command. Create deadline task
     *
     * @param inp Description of deadline task. Define deadline after "/by".
     *            Example: deadline task1 /by 12/12/2023 12:12
     * @throws DukeException
     */
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

    /**
     * Method to define function of event command. Create event task
     *
     * @param inp Descript of event task. Define event with "/from ... /to ...".
     *            Example: deadline task1 /from 12/12/2023 12:12 /to 12/12/2023 23:59
     * @throws DukeException
     */
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
