public class Ui {

    public static void println(String str) {
        System.out.println(str);
    }

    public static void printGoodbye() {
        println("Sad...Alright bye!");
    }

    public static void printList(TaskList allTasks) {
        if (allTasks.size() == 0) {
            println("You have zero tasks now!");
            return;
        }
        println("Your tasks so far!!");
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.getTask(i);
            String toPrint = String.format("%d. %s", i + 1, task.toString());
            println(toPrint);
        }
    }

    public static void printLine() {
        println("----------------------------------------------------");
    }

}
