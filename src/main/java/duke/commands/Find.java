package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

public class Find extends Command {
    private String cmdLine;

    public Find(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (lst.size() == 0) throw new DukeException("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
            if (cmdLine.length() <= 5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to search for any task or not?");
            String keyword = cmdLine.substring(5);
            TaskList findResult = new TaskList();
            for (int i = 1; i <= lst.size(); ++i) {
                if (lst.get(i - 1).toString().contains(keyword)) {
                    findResult.add(lst.get(i - 1));
                }
            }

            if (findResult.size() == 0) {
                System.out.println("Roarrrrrrrrrrrrrrrrrr! I did not find any task related to what you said!");
            } else {
                System.out.println("Roarrrrrrrrrrrrrrrrrr! Are you searching for these tasks?");
                for (int i = 1; i <= findResult.size(); ++i) {
                    System.out.println(i + "." + findResult.get(i-1).toString());
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
