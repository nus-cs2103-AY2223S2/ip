public class Missing extends DukeException {
    public Missing(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "☹ NOM NOM NOM!! Where is your date/time??";
    }
}
