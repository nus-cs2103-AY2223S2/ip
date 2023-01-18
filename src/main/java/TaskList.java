import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        /**
         * @returns whether the pad is empty or not.
         */
        return this.tasks.isEmpty();
    }

    private void isInList(int id) {
        /**
         * Checks if the item with that id is in the list.
         * @param id the id of the item in question.
         */
        if (id >= this.tasks.size()) {
            throw new NotFoundException("List",
                    String.format("Haiya we only got %d things lah.", this.tasks.size()), null);
        }
    }

    public void printSize() {
        /**
         * Prints size of the list.
         */
        System.out.println(String.format("Now have %d items.", this.tasks.size()));
    }

    public void addItem(String taskType, String content) {
        /**
         * Adds to the list if there is space.
         * @param item the string to add to the list.
         */
        Task task = Task.create(taskType, content);
        if (task == null) {
            System.out.println("FAILURE.");
            return;
        }
        this.tasks.add(task);
        System.out.println("OK I put in for you: " + task);
        this.printSize();
    }

    public void deleteItem(String response) {
        /**
         * Deletes the specified item.
         * @param response tries to parse this response.
         */
        String[] splitted = Parser.handleMissingField(response, " ", "number", "Item Deletion");

        String rawId = splitted[1];
        int id = Parser.parseInt(rawId, "Item Deletion");
        this.isInList(id);

        System.out.println("OK I take out for you: " + this.tasks.get(id));
        this.tasks.remove(id);
        this.printSize();
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

    public void markItem(String response, boolean isToMark) {
        /**
         * Marks the specified item.
         * @param response tries to parse this response.
         * @param toMark whether to mark it or unmark it.
         */
        String[] splitted = Parser.handleMissingField(response, " ", "number", "Item Marking");
        String rawId = splitted[1];
        int id = Parser.parseInt(rawId, "Item Marking");
        this.isInList(id);
        this.tasks.get(id).mark(isToMark);
    }
}
