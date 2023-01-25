import java.util.ArrayList;

public class TaskBook {
    private static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";

    private static ArrayList<Task> listOfTasks;

    public TaskBook() {
        this.listOfTasks = new ArrayList<Task>();
    }
    public TaskBook(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void listTasks() {
        System.out.println(HOR_BAR + "\nHere's what I have for you:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i).toString());
        }
        System.out.println(HOR_BAR);
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }

    public void addTask(TaskType taskType, String input) throws IncorrectDateException {
        String desc = input.split(" ")[1];
        Task t;

        if (taskType == TaskType.TODO) {
            t = new Todo(desc);

        } else if (taskType == TaskType.DEADLINE) {
            String by = input.split("/by ")[1];
            t = new Deadline(desc, by);

        } else { // EVENT
            String from = input.split("/from ")[1].split("/to")[0];
            String to = input.split("/to ")[1];

            t = new Event(desc, from, to);
        }

        listOfTasks.add(t);

        System.out.println(HOR_BAR + "\nDone and ready to go! I've added this task for ya:");
        System.out.println(t.toString());
        printNumberOfTasks();
        System.out.println(HOR_BAR);
    }

    public void deleteTask(int index){
        System.out.println(Duke.HOR_BAR + "\nHere you go! I've deleted this task for ya:");
        System.out.println(listOfTasks.get(index - 1).toString());
        listOfTasks.remove(index - 1);
        printNumberOfTasks();
        System.out.println( Duke.HOR_BAR);
    }

    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(HOR_BAR + "\nAlright~ I'll set the task as done! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა");
        t.markAsDone();
        System.out.println(HOR_BAR);
    }

    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        System.out.println(HOR_BAR + "\nOkay! I'll set the task as not done. ૮₍ ˃ ⤙ ˂ ₎ა");
        t.markAsNotDone();
        System.out.println(HOR_BAR);
    }

    public boolean indexWithinRange(int index) throws DukeException {
        if (index > 0 && index <= listOfTasks.size()) {
            return true;
        } else {
            throw new IndexOutOfRangeException();
        }
    }

    public void printNumberOfTasks() {
        System.out.println("You now have " + listOfTasks.size() + " tasks in the list ૮꒰ˊᗜˋ* ꒱ა");
    }
}