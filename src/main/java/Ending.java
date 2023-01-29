class Ending extends Event {
    TaskList taskList;
    public Ending() {
        super(true);
        this.taskList = new TaskList();
    }

    public Ending(TaskList taskList) {
        super(true);
        this.taskList = taskList;
    }
    public Event toNext() {
        return this;
    }

    TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        toPrintOut += "VERY WELL. THE WORLD IS SAFE FROM YOUR PLAN. FOR NOW" + '\n';
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}