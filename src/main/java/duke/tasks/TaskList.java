package duke.tasks;

import duke.exceptions.InvalidTaskDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    ArrayList<Task> tasks;
    int size;



    public TaskList(int size) {
        this.size = size;
        this.tasks = new ArrayList<>(size);
    }

    //getter for arraylist

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }




    //getter for current size
    public int getCurrentSize() {
        return this.tasks.size();
    }

    //generic method to add any duke.tasks
    public void append(Task task) {
        this.tasks.add(task);
    }


    //add task
    public void addTask(String input) throws InvalidTaskDescriptionException {
        String[] newInput = input.split("todo ");
        if (newInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        tasks.add(new Todo(newInput[1]));
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(this.getCurrentSize()-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", this.getCurrentSize());
    }

    //add event
    public void addEvent(String input) throws InvalidTaskDescriptionException{
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        tasks.add(this.getCurrentSize(), new Event(newInput[0], newInput[1], newInput[2]));
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(this.getCurrentSize()-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", this.getCurrentSize());
    }

    //add deadline
    public void addDeadline(String input) throws InvalidTaskDescriptionException{
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        System.out.println(Arrays.toString(newInput));
        System.out.println(newInput[1]);
        tasks.add(this.getCurrentSize(), new Deadline(newInput[0], newInput[1]));
        System.out.println("Gotchu fam");
        System.out.printf("I've added\n" + tasks.get(this.getCurrentSize()-1).toString() +
                "\nto all the shit u need to do\n");
        System.out.printf("shag bro now u got %d tasks\n", this.getCurrentSize());
    }


    //delete
    public void deleteTask(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        //duke.tasks.Task temp = duke.tasks.get(taskPointer);
        try {
            Task temp = tasks.get(taskPointer);
            tasks.remove(taskPointer);
            System.out.println("Ok bro I've removed this : ");
            System.out.printf(temp.toString() +
                    "\nfrom all the shit u need to do\n");
            System.out.println("Hope you have a better life now");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("brother look at how long ur list is first then delete lah");
        }
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
        if (this.getCurrentSize() == 0) {
            System.out.println("You got nothing to do brother its time to get a life");
        }
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (this.tasks.get(i) == null) {
                break;
            }
            System.out.printf("%d." + tasks.get(i).toString() + "\n", i+1);
        }

    }



}
