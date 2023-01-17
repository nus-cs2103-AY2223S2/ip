public enum TaskType {
    TODO("todo"), EVENT("event"), DEADLINE("deadline");
    private final String type;
    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
