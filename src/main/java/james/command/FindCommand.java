package james.command;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        ui.printFoundTasks(taskList.findTasks(keyword));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindCommand) {
            return keyword.equals(((FindCommand) obj).keyword);
        }
        return false;
    }
}

