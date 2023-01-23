import java.util.ArrayList;

public class CommandManager {

    public static void run(String input, String[] command, ArrayList<Task> storedTask, int taskCounter) {


       if (command[0].equals("list")) {
           listTasks(storedTask);
        } else if (command[0].equals("mark")) {
            mark(command, storedTask);
        } else if (command[0].equals("unmark")) {
            unmark(command, storedTask);
        } else if (command[0].equals("todo")) {
           addTodo(input, storedTask,taskCounter);
        } else if (command[0].equals("deadline")) {
           addDeadline(input, storedTask, taskCounter);
        } else if (command[0].equals("event")) {
           addEvent(input, storedTask, taskCounter);
        } else if (command[0].equals("delete")) {
            deleteItem(command, storedTask, taskCounter);
        } else {
           System.out.println("Invalid command wake up brother");
       }
    }

    public static void listTasks(ArrayList<Task> storedTask) {
        if (storedTask.size() == 0) {
            System.out.println("You got nothing to do brother its time to get a life");
        }
        for (int i = 0; i < storedTask.size(); i++) {
            if (storedTask.get(i) == null) {
                break;
            }
            System.out.printf("%d." + storedTask.get(i).toString() + "\n", i+1);
        }
        return;
    }

    public static void mark(String[] command, ArrayList<Task> storedTask) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            storedTask.get(taskPointer).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wake up and choose a better task to mark");
            //continue;
        }
    }

    public static void unmark(String[] command, ArrayList<Task> storedTask) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            storedTask.get(taskPointer).undoTask();
        } catch (IndexOutOfBoundsException e ) {
            System.out.println("Wake up and choose a better task to unmark brother");
            //continue;
        }
        return;
    }

    public static void addTodo(String input, ArrayList<Task> storedTask, int taskCounter) {
        String[] newInput = input.split("todo ");
        storedTask.add(taskCounter, new Todo(newInput[1]));
        taskCounter++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + storedTask.get(taskCounter-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", taskCounter);
        return;
    }

    public static void addDeadline(String input, ArrayList<Task> storedTask, int taskCounter) {
        String[] newInput = input.split("/");
        storedTask.add(taskCounter, new Deadline(newInput[0], newInput[1]));
        taskCounter++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + storedTask.get(taskCounter-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", taskCounter);
        return;
    }

    public static void addEvent(String input, ArrayList<Task> storedTask, int taskCounter) {
        String[] newInput = input.split("/");
        storedTask.add(taskCounter, new Event(newInput[0], newInput[1], newInput[2]));
        taskCounter++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + storedTask.get(taskCounter-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", taskCounter);
        return;
    }

    public static void deleteItem(String[] command, ArrayList<Task> storedTask, int taskCounter) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        Task temp = storedTask.get(taskPointer);
        try {
            storedTask.remove(taskPointer);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wake up and choose a better task to mark");
        }
        taskCounter--;
        System.out.println("Ok bro I've removed this : ");
        System.out.printf(temp.toString() +
                "\nfrom all the shit u need to do\n");
        System.out.println("Hope you have a better life now");
        return;
    }


}
