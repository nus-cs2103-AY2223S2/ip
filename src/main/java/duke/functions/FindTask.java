package duke.functions;

import duke.task.Task;

public class FindTask {
    /**
     * Method to define the find function for the find button
     *
     * @param query Keyword to search
     */
    static public void find(Functions fn, String query) {
        boolean flag = false;
        boolean printed = false;
        int i = 1;
        String h = "";
        String task = "";

        for (Task t : fn.getTl().iterable()) {
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
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        } else {
            String t = "Search done!\n";
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + task + t));
        }
    }
}
