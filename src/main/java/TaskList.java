import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(String type, String input) throws EmptyDescriptionException{
        Task task;
        if (Dudu.Command.DEADLINE.equals(type)) {
            if (input.trim().length() == 8) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            if (!input.contains(" /by ")) {
                input = input.concat(" /by null");
            }
            String[] inputStr = input.substring(9).split(" /by ");
            task = new Deadline(inputStr[0], inputStr[1]);
        } else if (Dudu.Command.TODO.equals(type)) {
            if (input.trim().length() == 4) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            task = new Todo(input.substring(5));
        } else {
            if (input.trim().length() == 5) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            String[] inputStr = input.substring(6).split(" /from ");
            String[] dateStr = inputStr[1].split(" /to ");
            task = new Event(inputStr[0],dateStr[0],dateStr[1]);
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(getTotalTask());
    }

    public String getTotalTask() {
        String secondHalf;
        if (list.size() <= 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + list.size() + secondHalf;
    }
    public Task getTask(int index) throws TaskNumRangeException {
        if (index >= list.size() || index < 0) {
            throw new TaskNumRangeException(String.valueOf(index));
        }
        return list.get(index);
    }

    public void delete(int index) {
        list.remove(index);
    }
    public void printList() {
        if (list.size() == 0) {
            System.out.println("There is no task in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                System.out.println(i + 1 + "." + currTask);
            }
        }
    }
}
