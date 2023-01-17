import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> items;

    public TaskList() {
        items = new ArrayList<>();
    }

    public void add(Task task, Ui ui) {
        items.add(task);
        ui.addTask(task, items.size());
    }

    public void addEvent(String[] curr, Ui ui) throws DukeException {
        try {
            String descr = curr[0].substring(6).trim();
            String[] newCurr = curr[1].split("/to");
            String from = newCurr[0].trim();
            String to = newCurr[1].trim();
            add(new Event(descr, from, to), ui);
        } catch (Exception e) {
            throw new DukeException("You need to fill in an event with format `event {title} /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm`");
        }
    }

    public void addDeadline(String[] curr, Ui ui) throws DukeException {
        try {
            String descr = curr[0].substring(9).trim();
            String by = curr[1].trim();
            add(new Deadline(descr, by), ui);
        } catch (Exception e) {
            throw new DukeException("You need to fill in a deadline with format `deadline {title} /by dd/MM/yyyy HHmm`");
        }
    }

    public void addToDo(String[] curr, Ui ui) throws DukeException {
        String todo = curr[0].substring(5).trim();
        if (todo.isBlank()) {
            throw new DukeException("You need to add a todo with format `todo {title}`");
        } else { add(new ToDo(todo), ui); }
    }

    public void deleteTask(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (idx >= items.size() || idx < 0) {
                throw new DukeException();
            } else {
                ui.deleteMessage(items.get(idx), items.size());
                items.remove(idx);
            }
        } catch (Exception e) {
            throw new DukeException("Please give a valid index between 1 and " + items.size());
        }
    }

    public void mark(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (items.get(idx).isMarked()) {
                throw new AlreadyMarkedException();
            } else {
                items.get(idx).mark();
                ui.markMessage(items.get(idx));
            }
        } catch (AlreadyMarkedException e) {
            throw new AlreadyMarkedException("Already Marked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    public void unmark(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (!items.get(idx).isMarked()) {
                throw new AlreadyUnmarkedException();
            } else {
                items.get(idx).unmark();
                ui.unmarkMessage(items.get(idx));
            }
        } catch (AlreadyUnmarkedException e) {
            throw new AlreadyUnmarkedException("Already unmarked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    public void initAdd(Task task) {
        items.add(task);
    }

    public String itemsToData() {
        String data = "";
        for (int i = 0; i < items.size(); i++) {
            data += items.get(i).toData();
            data += "\n";
        }
        return data;
    }

    public void end(Ui ui) {
        ui.end();
        System.exit(0);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < items.size(); i++) {
            Task item = items.get(i);
            res += "\t " + (i + 1) + ". " + item + "\n";
        }
        return res;
    }
}
