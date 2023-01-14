public class Message {
    private String content;

    Message(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }

    public String getContent() {
        return this.content;
    }
}
