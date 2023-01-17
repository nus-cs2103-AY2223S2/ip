class DukeList {
    // Fixed-size array of at most 100 tasks
    String[] tasks = new String[100];
    int size = 0;
    public void addTask(String s) {
        tasks[size] = s;
        size++;
        System.out.println("added: " + s);
    }

    public void list() {
        if (size == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + tasks[i - 1]);
        }
    }

}
