package duke.functions;

import java.util.Collections;

public class ListTask {

    /**
     * Method to define function of list command
     */
    static public void list(Functions fn) {
        String h = "Here are the tasks in your list:\n";
        String l = "";
        for (int i = 0; i < fn.tl.count(); i++) {
            String s = i + 1 + "." + fn.tl.getTask(i).printStatus();
            l += s;
        }
        fn.getOutputLayout().getChildren().clear();
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + l));
    }

    static public void deadlineSort(Functions fn) {
        fn.getOutputLayout().getChildren().clear();
        Collections.sort(fn.tl.iterable(), new TaskListSort.SortByDeadline());
    }

    static public void defaultSort(Functions fn) {
        fn.getOutputLayout().getChildren().clear();
        Collections.sort(fn.tl.iterable(), new TaskListSort.SortByDefault());
    }
}
