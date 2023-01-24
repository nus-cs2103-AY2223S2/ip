public enum TaskCategory {
    TODO("T"), 
    EVENT("E"), 
    DEADLINE("D");

    public static TaskCategory fromString(String s) {
        for (TaskCategory tc: TaskCategory.values()) {
            if (tc.icon.equals(s)) {
                return tc;
            }
        }
        return null;
    }

    private final String icon;
    private TaskCategory(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
