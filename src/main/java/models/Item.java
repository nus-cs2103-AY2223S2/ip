package models;

public class Item {
    private final String content;
    private boolean isDone;

    public Item(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        var icon = isDone ? "X" : " ";
        return String.format("[%s] %s", icon, content);
    }
}
