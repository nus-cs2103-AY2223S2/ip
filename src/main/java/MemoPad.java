public class MemoPad {
    private int pointer;
    private int size;
    private Task[] tasks;

    public MemoPad(int size) {
        this.pointer = 0;
        this.size = size;
        this.tasks = new Task[size];
    }

    public boolean isFull() {
        /**
         * @returns whether the pad is full or not.
         */
        return this.pointer == this.size-1;
    }

    public boolean isEmpty() {
        /**
         * @returns whether the pad is empty or not.
         */
        return this.pointer == 0;
    }

    public void addToList(char taskType, String content) {
        /**
         * Adds to the list if there is space.
         * @param item the string to add to the list.
         */
        if (this.isFull()) {
            System.out.println("No more space in list. Item not added.");
            return;
        }

        Task task = Task.create(taskType, content);
        if (task == null) {
            System.out.println("Task creation unsuccessful.");
            return;
        }
        this.tasks[this.pointer] = task;
        this.pointer++;
        System.out.println("Added task:");
        System.out.println(task);

        System.out.println(String.format("Now have %d items.", this.pointer));
    }

    public void printItem(int id, boolean withNumber) {
        /**
         * Prints the item at this id.
         * @param id the index of the item to be printed.
         * @param withNumber whether to add the numbering.
         */
        String numbering = withNumber ? (id+1) + ". " : "";
        System.out.println(numbering + this.tasks[id]);
    }

    public void listItems() {
        /**
         * Lists the items in the list, including whether it was marked or not.
         */
        if (this.isEmpty()) {
            System.out.println("List empty!");
            return;
        }

        System.out.println("Here's your list:");
        for (int id = 0; id < this.size; id++) {
            if (this.tasks[id] == null) {
                break;
            }
            this.printItem(id, true);
        }
    }

    public void markItem(String response, boolean toMark) {
        /**
         * Marks the specified item.
         * @param response tries to parse this response.
         * @param toMark whether to mark it or unmark it.
         */
        String[] splitted = response.split(" ", 2);
        if (splitted.length <= 1) {
            System.out.println("You did not include a number after the keyword. Try again.");
            return;
        }

        String unparsedId = splitted[1];
        try {
            int id = Integer.parseInt(unparsedId)-1;
            if (id < this.pointer) {
                this.tasks[id].mark(toMark);
            } else {
                System.out.println("Item does not exist. Try again with maximum value " + this.pointer + ".");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input for marking an item. Try again.");
        }
    }
}
