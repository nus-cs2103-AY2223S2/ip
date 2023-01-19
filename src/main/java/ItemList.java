import java.util.ArrayList;
import java.util.Iterator;

public class ItemList {
    private ArrayList<Item> list;

    public ItemList() {
        this.list = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.list.add(item);
    }

    public void readList() {
        //this.list.forEach(x -> System.out.println(list.indexOf(x) + x.toString()));
        Iterator<Item> it = this.list.iterator();
        it.forEachRemaining(x -> System.out.println((list.indexOf(x) + 1) + ". " + x.toString()));
    }
}
