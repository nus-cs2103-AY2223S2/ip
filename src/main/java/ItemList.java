import java.util.ArrayList;
import java.util.Iterator;

public class ItemList {
    private ArrayList<Item> list;

    public ItemList() {
        this.list = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.list.add(item);
        System.out.println("DukeyList just added: " + item.toString());
    }

    public void readList() {
        //this.list.forEach(x -> System.out.println(list.indexOf(x) + x.toString()));
        Iterator<Item> it = this.list.iterator();
        it.forEachRemaining(x -> System.out.println((list.indexOf(x) + 1) + ". " + x.toString()));
    }

    public void mark(int itemNumber) {
        Item itemToMark = this.list.get(itemNumber);
        itemToMark.markAsDone();
        System.out.println("DukeyList: Item number " + (itemNumber + 1) + " has been mark as done!");
        System.out.println((itemNumber + 1) + ". " + itemToMark);
    }

    public void unmark(int itemNumber) {
        Item itemToUnmark = this.list.get(itemNumber);
        itemToUnmark.unmark();
        System.out.println("DukeyList: Item number " + (1 + itemNumber) + " has been unmarked!");
        System.out.println((itemNumber + 1) + ". " + itemToUnmark);
    }
}
