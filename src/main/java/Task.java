class Task {
    private final int number;
    private final String name;
    private final String status;

    public Task(int number, String name, boolean status) {
        this.number = number;
        this.name = name;
        if (status) {
            this.status = "[ ]";
        } else {
            this.status = "[X]";
        }
    }

    public String toString() {
        return number + "." + status + " " + name;
    }

    public Task mark() {
        return new Task(number, name, true);
    }

    public Task unmark() {
        return new Task(number, name, false);
    }
}
