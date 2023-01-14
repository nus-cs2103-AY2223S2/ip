public class UserMessage extends Message {
    UserMessage(String content) {
        super(content);
    }
    @Override
    public String toString() {
        return this.getContent();
    }
}
