public class StorageList {
    private String[] list;
    private int index;

    public StorageList() {
        this.list = new String[100];
        this.index = 0;

    }

    public void addSentence(String sentence) {
        list[index] = sentence;
        index++;
    }

    public void printList() {
        int x = 0;
        while (list[x] != null) {
            System.out.println((x + 1) + ":" + list[x]);
            x++;
        }
    }
}
