import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    ArrayList<Task> tasks;
    int size;

    int currentSize = 0;

    public TaskList(int size) {
        this.size = size;
        this.tasks = new ArrayList<>(size);
    }

    //add task
    public void addTask(String input) throws InvalidTaskDescriptionException{
        String[] newInput = input.split("todo ");
        if (newInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        tasks.add(new Todo(newInput[1]));
        this.currentSize++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(currentSize-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", currentSize);
    }

    //add event
    public void addEvent(String input) throws InvalidTaskDescriptionException{
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        String date1 = newInput[1]  + "/" +  newInput[2]  + "/" +  newInput[3];
        String date2 = newInput[4]  + "/" +  newInput[5]  + "/" +  newInput[6];
        tasks.add(currentSize, new Event(newInput[0], date1, date2));
        this.currentSize++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(currentSize-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", currentSize);
    }

    //add deadline
    public void addDeadline(String input) throws InvalidTaskDescriptionException{
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        String date = newInput[1] + "/" +  newInput[2]  + "/"  + newInput[3];
        System.out.println(Arrays.toString(newInput));
        System.out.println(date);
        tasks.add(currentSize, new Deadline(newInput[0], date));
        this.currentSize++;
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(currentSize-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", currentSize);
    }


    //delete
    public void deleteTask(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        Task temp = tasks.get(taskPointer);
        try {
            tasks.remove(taskPointer);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wake up and choose a better task to mark");
        }
        this.currentSize--;
        System.out.println("Ok bro I've removed this : ");
        System.out.printf(temp.toString() +
                "\nfrom all the shit u need to do\n");
        System.out.println("Hope you have a better life now");
    }


    //mark

    public void mark(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            tasks.get(taskPointer).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wake up and choose a better task to mark");
        }
    }

    //unmark
    public void unmark(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            tasks.get(taskPointer).undoTask();
        } catch (IndexOutOfBoundsException e ) {
            System.out.println("Wake up and choose a better task to unmark brother");
        }
    }



    //list
    public void listTasks() {
        if (this.currentSize == 0) {
            System.out.println("You got nothing to do brother its time to get a life");
        }
        for (int i = 0; i < currentSize; i++) {
            if (this.tasks.get(i) == null) {
                break;
            }
            System.out.printf("%d." + tasks.get(i).toString() + "\n", i+1);
        }

    }



}
