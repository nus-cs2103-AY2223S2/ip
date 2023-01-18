public class Task {
    public static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    public String description;
    public static int totalNumberOfTask = 0;

    public static int getTotalNumberOfTask() {
        return totalNumberOfTask;
    }

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
