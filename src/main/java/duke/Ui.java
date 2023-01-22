package duke;
import java.util.ArrayList;

class Ui {
    public static void intro() {
        System.out.println("Hello! I am your anime waifu!"); 
        System.out.println("What can I do for you my husbando?");        
        System.out.println(" (*_*)");        
        System.out.println("|(   )|");        
        System.out.println("  |-|");        
    }
    public static void close() {
        System.out.println("Bye! Hope to see you again <3!");
    }
    public static void invalidCommand() {
        System.out.println("please make sure your command is valid!");
    }
    public static void missingArgs() {
        System.out.println("please ensure there are arguments for particular commands!");
    }
    public static void list(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("%d.%s%n",i+1,tasks.get(i));
        }
    }
    public static void mark(boolean isMark, Task task) {
        if (isMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.toString());
    }
    public static void notANumber() {
        System.out.println("please only input numbers");
    }
    public static void numberOutOfBounds() {
        System.out.println("make sure the number is in range");
    }
    public static void addTask(String type, Task task) {
        System.out.format("Got it I've added a %s%n", type);
        System.out.println(task.toString());
    }
    public static void missingOptions(String option) {
        System.out.format("please ensure u have a %s option and that %s option argument exist (order should be followed if command has multiple option)%n", option);
    }
    public static void wrongDateFormat() {
        System.out.println("please ensure yyyy-MM-dd format");
    }
    public static void delete(Task task) {
        System.out.println("I have removed this task");
        System.out.println(task);
    }
}
