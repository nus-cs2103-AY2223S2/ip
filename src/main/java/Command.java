public class Command {
    private Ui ui;
    public Command() {
        this.ui = new Ui();
    }
    public void listTasksCommand (TaskList store) throws DukeException {
        try {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < store.getSize(); i ++) {
                ui.sendTaskDetails(i + 1, store.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
