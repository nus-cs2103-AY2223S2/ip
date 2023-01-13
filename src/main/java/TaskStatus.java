public enum TaskStatus {
    DONE("X"),
    NOT_DONE(" ");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "[" + this.status + "]";
    }
}

