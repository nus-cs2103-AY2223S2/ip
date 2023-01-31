package duke.functions;

import duke.task.Task;

public class FindTask extends Functions {

    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public FindTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    public void find(String query) {
        boolean flag = false;
        boolean printed = false;
        int i = 1;
        String h = "";
        String task = "";

        for (Task t : tl.iterable()) {
            if (t.getDes().contains(query)) {
                flag = true;
                if (flag && !printed) {
                    h = "Here are the matching tasks in your list:\n";
                    printed = true;
                }
                task += i + ".";
                task += t.printStatus();
                i++;
            }
        }
        if (!flag) {
            String s = "No matching tasks are found in your list\n";
            outputLayout.getChildren().add(getDialogLabel(s));
        } else {
            String t = "Search done!\n";
            outputLayout.getChildren().add(getDialogLabel(h + task + t));
        }
    }
}
