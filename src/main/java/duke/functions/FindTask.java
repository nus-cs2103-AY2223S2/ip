package duke.functions;

import duke.task.Task;

public class FindTask {
    /**
     * Method to define the find function for the find button
     *
     * @param query Keyword to search
     */
    static public void find(Functions fn, String query) {
        boolean isFound = false;
        boolean isPrinted = false;
        int i = 1;
        String h = "";
        String task = "";

        for (Task t : fn.getTl().iterable()) {
            if (t.getDes().contains(query)) {
                isFound = true;
                if (isFound && !isPrinted) {
                    h = "Here are the matching tasks in your list:\n";
                    isPrinted = true;
                }
                task += i + ".";
                task += t.printStatus();
                i++;
            }
        }
        if (!isFound) {
            fn.getOutputLayout().getChildren().clear();
            String s = "No matching tasks are found in your list\n";
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        } else {
            fn.getOutputLayout().getChildren().clear();
            String t = "Search done!\n";
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + task + t));
        }
    }
}
