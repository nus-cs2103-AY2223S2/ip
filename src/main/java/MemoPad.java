import java.util.ArrayList;

public class MemoPad {
    private ArrayList<Task> tasks;

    public MemoPad() {
        this.tasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        /**
         * @returns whether the pad is empty or not.
         */
        return this.tasks.isEmpty();
    }

    public void printSize() {
        System.out.println(String.format("Now have %d items.", this.tasks.size()));
    }

    public void addItem(char taskType, String content) {
        /**
         * Adds to the list if there is space.
         * @param item the string to add to the list.
         */
        Task task = Task.create(taskType, content);
        if (task == null) {
            System.out.println("Task creation unsuccessful.");
            return;
        }
        this.tasks.add(task);
        System.out.println("Added task:");
        System.out.println(task);
        this.printSize();
    }

    public void deleteItem(String response) {
        /**
         * Marks the specified item.
         * @param unparsedId tries to parse this response.
         */
        String[] splitted = response.split(" ", 2);
        if (splitted.length <= 1) {
            throw new InputFormatException("Item Marking", "You did not include a number after the keyword. Try again.", null);
        }

        String unparsedId = splitted[1];
        try {
            int id = Integer.parseInt(unparsedId.strip())-1;
            if (id < this.tasks.size()) {
                System.out.println("Deleted task:");
                System.out.println(this.tasks.get(id));
                this.tasks.remove(id);
                this.printSize();
            } else {
                throw new NotFoundException("List", String.format("The maximum possible index is %d.", this.tasks.size()), null);
            }
        } catch (NumberFormatException ex) {
            throw new InputFormatException("Item Deletion", "You did not include a number after the keyword. Try again.", null);
        }
    }

    public void printItem(int id, boolean withNumber) {
        /**
         * Prints the item at this id.
         * @param id the index of the item to be printed.
         * @param withNumber whether to add the numbering.
         */
        String numbering = withNumber ? (id+1) + ". " : "";
        System.out.println(numbering + this.tasks.get(id));
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
        for (int id = 0; id < this.tasks.size(); id++) {
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
            throw new InputFormatException("Item Marking", "You did not include a number after the keyword. Try again.", null);
        }

        String unparsedId = splitted[1];
        try {
            int id = Integer.parseInt(unparsedId)-1;
            if (id < this.tasks.size()) {
                this.tasks.get(id).mark(toMark);
            } else {
                throw new NotFoundException("List", String.format("The maximum possible index is %d.", this.tasks.size()), null);
            }
        } catch (NumberFormatException ex) {
            throw new InputFormatException("Item Marking", "You did not include a number after the keyword. Try again.", null);
        }
    }
}
