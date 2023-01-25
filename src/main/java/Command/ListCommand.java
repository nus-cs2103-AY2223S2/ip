package Command;

import Duke.DukeList;
import Duke.TextBorder;

public class ListCommand extends Command{
    private DukeList dukeList;

    public ListCommand(DukeList dukeList) {
        this.dukeList = dukeList;
    }

    @Override
    public void execute() {
        if (this.dukeList.isEmpty()) {
            System.out.println(new TextBorder("Yo there's nothing in the list."));
        } else {
            System.out.println(new TextBorder(this.dukeList.toString()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
