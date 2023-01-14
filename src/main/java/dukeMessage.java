public class DukeMessage extends Message{


    DukeMessage(String content) {
        super(content);
    }

    @Override
    public String toString() {
        String reply;
        if (this.getContent().equals("bye")) {
            reply = "Bye. Hope to see you again soon!";
        } else {
            reply = this.getContent();
        }
        return String.format("%s\n", reply);
    }

}
