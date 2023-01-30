import java.util.ArrayList;
import java.util.Iterator;

public class ItemList {
    private ArrayList<Item> list;

    public ItemList() {
        this.list = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.list.add(item);
        System.out.println(item.messageWhenAdded() + " " + item.toString());
        printSize();
    }

    public void readList() {
        //this.list.forEach(x -> System.out.println(list.indexOf(x) + x.toString()));
        Iterator<Item> it = this.list.iterator();
        it.forEachRemaining(x -> System.out.println((list.indexOf(x) + 1) + ". " + x.toString()));
    }

    public void mark(int itemNumber) {
        Item itemToMark = this.list.get(itemNumber);
        itemToMark.markAsDone();
        System.out.println("DukeyList: Item number " + (itemNumber + 1) + " has been marked as done!");
        System.out.println((itemNumber + 1) + ". " + itemToMark);
    }

    public void unmark(int itemNumber) {
        Item itemToUnmark = this.list.get(itemNumber);
        itemToUnmark.unmark();
        System.out.println("DukeyList: Item number " + (1 + itemNumber) + " has been unmarked!");
        System.out.println((itemNumber + 1) + ". " + itemToUnmark);
    }

    public void delete(int itemNumber) {
        Item itemToRemove = list.get(itemNumber);
        System.out.println("DukeyList: The following item has been removed!");
        System.out.println(itemToRemove.toString());
        list.remove(itemNumber);
        this.printSize();
    }

    public int getSize() {
        return list.size();
    }

    public void printSize() {
        if (getSize() == 1){
            System.out.println("DukeyList now has 1 task.");
        } else {
            System.out.println("DukeyList now has " + getSize() + " tasks.");
        }

    }

    public static void printInstruction() {
        System.out.println("DukeyList: Welcome to DukeyList!! The instructions are as follows:");
        System.out.println("To list: 'list'");
        System.out.println("To exit: 'bye'");
        System.out.println("To add a todo: 'todo <taskname>'");
        System.out.println("To add a deadline: 'deadline <taskname> / <deadline>'");
        System.out.println("To add an event: 'event <taskname> / <start time> / <end time> '");
        System.out.println("To mark a task as done: 'mark <task number>'");
        System.out.println("To unmark a task: 'unmark <task number>'");
    }
}
