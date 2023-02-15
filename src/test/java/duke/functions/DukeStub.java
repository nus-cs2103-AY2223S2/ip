package duke.functions;

import duke.Duke;

public class DukeStub extends Duke {
    public DukeStub() {
        super("./ip-data/ip-data.txt");
    }

    @Override
    public String shutDown() {
        return "The Duke has saved your To Do List!\n" +
                "You may safely exit now, feel free to call the Duke again whenever you need.";
    }
}
