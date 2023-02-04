package duke.functions;

import duke.task.Task;

public class FindTask extends Functions {

    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public FindTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define the find function for the find button
     *
     * @param query Keyword to search
     */
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
