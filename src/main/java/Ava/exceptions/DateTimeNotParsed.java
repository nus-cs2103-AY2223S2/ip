package Ava.exceptions;


public class DateTimeNotParsed extends AvaException {
    private final String ERROR_PROMPT = "I don't recognize the date and time , follow this format 'd/mm/yy HHMM' " +
            "For Eg. '/by 10/09/2002 1500'-> 10th September 2002 3PM " ;
    private String input;

    public DateTimeNotParsed(String input) {
        this.input = input;
    }

    @Override
    public String getMessage(){
        return super.SORRY +  " " + ERROR_PROMPT;
    }
}
