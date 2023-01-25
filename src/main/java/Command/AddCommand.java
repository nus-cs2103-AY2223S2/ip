package Command;

import java.time.format.DateTimeParseException;

import Duke.Duke;
import Duke.DukeList;

public class AddCommand extends Command {
    private String type;
    private String string;
    private DukeList dukeList;

    public AddCommand (String type, String string, DukeList dukeList) {
        this.type = type;
        this.string = string;
        this.dukeList = dukeList;
    }

    @Override
    public void execute() {
        try {
            dukeList.add(this.type, this.string);
        } catch (DateTimeParseException e) {
            System.out.println("Hey, I can't see what date that is man.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
