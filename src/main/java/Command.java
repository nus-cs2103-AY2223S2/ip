public class Command {

    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Task(""));
        store.save(list);
    }

    public boolean isExit() {
        return false;
    }
}
