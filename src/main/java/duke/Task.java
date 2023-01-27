package duke;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    static String divider = "    ────────────── ⋆⋅☆⋅⋆ ───────────────";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + this.description;
    }

    public static void displayList(ArrayList<Task> array) {
        System.out.println(divider);
        for(int j=0; j<array.size(); j++) {
            System.out.println("      "+(j+1) +"."+ array.get(j).toString());
        }
        System.out.println(divider);
    }

    public static void unmarkTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsUnDone();
        System.out.println(divider);
        System.out.println("      OK, I've marked this task as not done yet:");
        System.out.println("      "+ array.get((Integer.parseInt(splitInput[1])-1)).toString());
        System.out.println(divider);
    }

    public static void markTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsDone();
        System.out.println(divider);
        System.out.println("      Nice! I've marked this task as done:");
        System.out.println("      "+ array.get((Integer.parseInt(splitInput[1])-1)).toString());
        System.out.println(divider);
    }

    public static void deleteTask(ArrayList<Task> array, String[] splitInput) {
        System.out.println(divider);
        System.out.println("      Noted. I've removed this task:");
        System.out.println("      "+ array.get((Integer.parseInt(splitInput[1])-1)).toString());
        array.remove((Integer.parseInt(splitInput[1])-1));
        System.out.println("      Now you have " + array.size() + " tasks in the list.");
        System.out.println(divider);
    }
}