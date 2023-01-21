import java.util.*;
 class Task {
    public String description;
     public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void changeCompletion() {
        this.isDone = !this.isDone;
    }
    @Override
     public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String newLine = System.getProperty("line.separator");
        ArrayList<Task> toStore = new ArrayList<>(100);
        System.out.println("-------------------------------------------------------" +
                newLine + "Hello! Jak Sie Masz! I am Duke.\n What I do for you boss?");

        String inData;
        boolean exit = false;
        Scanner scan = new Scanner( System.in );
        inData = scan.nextLine();
        String[] arrofStr = inData.split(" ");

        while(!exit) {
            switch (arrofStr[0]) {
                case "bye":
                    exit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list my good sir:");
                    int counter = 0;
                    for (Task i : toStore) {
                        counter++;
                        System.out.println(counter + ". " + i.toString());
                    }
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ");
                    break;
                case "unmark":
                    int indx = Integer.parseInt(arrofStr[1]) - 1;
                    Task toMark = toStore.get(indx);
                    toMark.changeCompletion();
                    toStore.set(indx , toMark);
                    System.out.println("OK, I've marked this task as not done yet:\n" + toMark);
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ");
                    break;
                case "mark":
                    indx = Integer.parseInt(arrofStr[1]) - 1;
                    toMark = toStore.get(indx);
                    toMark.changeCompletion();
                    toStore.set(indx , toMark);
                    System.out.println("Nice! I've marked this task as done:\n" + toMark);
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ");
                    break;
                default:
                    System.out.println("added: " + inData);
                    Task toAdd = new Task(inData);
                    toStore.add(toAdd);
                    inData = scan.nextLine();
                    arrofStr = inData.split(" ");
                    break;
            }
        }







    }
}
