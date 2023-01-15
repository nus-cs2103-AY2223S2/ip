public class Store {
    private String[] database;
    private int total;

    public Store() {
        this.database = new String[100];
        this.total = 0;
    }

    public void store(String s) {
        this.database[this.total] = s;
        this.total++;
    }

    public String list() {
        StringBuilder ret = new StringBuilder("Here is all the items stored~\n");

        for (int i = 0; i < this.total; i++) {
            ret.append(i + 1);
            ret.append(". " + database[i]);
            ret.append("\n");
        }

        return ret.toString();
    }
}
