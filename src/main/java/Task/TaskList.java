package Task;

import DukeException.NotFoundException;
import Struct.Triple;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Triple<Character, Boolean, String>> triples) {
        this.tasks = new ArrayList<>();
        for (Triple<Character, Boolean, String> triple : triples) {
            Task task = Task.create(triple.getFirst(), triple.getThird());
            if (task != null) {
                task.mark(triple.getSecond());
                this.tasks.add(task);
            }
        }
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

    public void addTask(Task task) {
        /**
         * Adds to the list if there is space.
         * @param item the string to add to the list.
         */
        this.tasks.add(task);
        System.out.println("OK I put in for you: " + task);
        this.printSize();
    }

    public void deleteTask(int index) {
        /**
         * Deletes the specified item.
         * @param response tries to parse this response.
         */
        this.isInList(index);
        System.out.println("OK I take out for you: " + this.tasks.get(index));
        this.tasks.remove(index);
        this.printSize();
    }

    public void printTask(int id, boolean withNumber) {
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
            this.printTask(id, true);
        }
    }

    public void markTask(int index, boolean isToMark) {
        /**
         * Marks the specified item.
         * @param response tries to parse this response.
         * @param toMark whether to mark it or unmark it.
         */
        this.isInList(index);
        Task task = this.tasks.get(index);
        task.mark(isToMark);
        System.out.println(String.format("OK %smark for you already: ", isToMark ? "" : "un") + task);
    }

    public String tasksStorageString(String delimiter) {
        String finalString = "";
        for (Task task : this.tasks) {
            finalString += task.toStorageString();
            finalString += delimiter;
        }
        return finalString;
    }
}
