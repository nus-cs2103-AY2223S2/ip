class Task {
    private String task;
    Task(String task) {
        this.task = task;
    }

    /**
     * Returns the string of the task
     */
    @Override
    public String toString() {
        return task;
    }
}
