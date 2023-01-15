public class MemoPad {
    int pointer;
    int size;
    String[] items;

    public MemoPad(int size) {
        this.pointer = 0;
        this.size = size;
        this.items = new String[size];
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

    public void addToList(String item) {
        if (this.isFull()) {
            System.out.println("No more space in list. Item not added.");
            return;
        }

        this.items[this.pointer] = item;
        this.pointer++;
        System.out.println("Added: " + item);
    }

    public void listItems() {
        if (this.isEmpty()) {
            System.out.println("List empty!");
            return;
        }

        System.out.println("Here's your list:");
        int currNum = 1;
        for (String item : this.items) {
            if (item == null) {
                break;
            }
            System.out.println(currNum + ". " + item);
            currNum += 1;
        }
    }
}
