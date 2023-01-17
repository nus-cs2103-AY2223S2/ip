public class List {
    private Task[] content = new Task[100];
    private int counter = 0;

    public void add(String input) {
        if (input.isEmpty()) {
            System.out.println("Please enter a command.");
        } else {
            Task item = new Task(input);
            content[counter] = item;
            System.out.println(input + " added to list!");
            counter++;
        }
    }

    public void display() {
        String allElements = "";
        for (int i = 0; i < 101; i++) {
            if (content[i] != null) {
                allElements = allElements + (i + 1) + ". " + content[i].status() + content[i].getDescription() + "\n";
            } else {
                break;
            }
        }
        if (allElements == "") {
            System.out.println("No items in list!");
//
        } else {
            System.out.println("Here are the tasks in your list: ");
            System.out.println(allElements);
        }
    }

    public void markAsDone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        if (content[index] != null) {
            System.out.println("Nice! I've marked this task as done: ");
            content[index].setDone();
            System.out.println(index + 1 + ". " + content[index].status() + content[index].getDescription());
        } else {
            System.out.println("No such task.");
        }
    }

    public void markAsUndone(String input) {
        String num = input.split(" ")[1];
        int index = Integer.parseInt(num) - 1;
        if (content[index] == null) {
            System.out.println("No such task.");
        } else if (content[index] != null && content[index].isDone) {
            System.out.println("Nice! I've marked this task as undone: ");
            content[index].setUndone();
            System.out.println(index + 1 + ". " + content[index].status() + content[index].getDescription());
        } else if(!content[index].isDone) {
            System.out.println("This task is already marked undone. ");
        } else {
            System.out.println("No such task.");
        }
    }
}
