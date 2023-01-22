package aqua.manager;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;

public class ReplyFormatManager {
    private static final String GREETING = 
            "Konaqua~~ Perfect idol gamer nekomimi super maid Minato Aqua desu!";
    private static final String SYNTAX_ERROR_FORMAT =
            "Hanya??\n" +
            "I do not understand because:\n" +
            "  %s\n" +
            "Gomennasai!!";
    private static final String EXECUTION_EXCEPTION_FORMAT = 
            "Hanya??\n" +
            "I was doing what you told me to do half way but messed up because:\n" +
            "  %s\n" +
            "Gomennasai!!";
    private static final String LOAD_EXCEPTION_FORMAT = 
            "Hanya??\n" +
            "I was looking through my notes and could not remember your previous tasks because:\n" +
            "  %s\n" +
            "Gomennasai!! But you did not touch it right?";
    private static final String DEATH_EXCEPTION_FORMAT =
            "UWAWAWA!!\n" +
            "I messed up big time...\n" +
            "  %s";


    public String getExceptionReply(Throwable ex) {
        try {
            throw ex;
        } catch (IllegalSyntaxException syntaxEx) {
            return String.format(SYNTAX_ERROR_FORMAT, ex.getMessage());
        } catch (ProcedureExecutionException cmdExeEx) {
            return String.format(EXECUTION_EXCEPTION_FORMAT, ex.getMessage());
        } catch (LoadException loadEx) {
            return String.format(LOAD_EXCEPTION_FORMAT, ex.getMessage());
        } catch (Throwable deathEx) {
            deathEx.printStackTrace();
            return String.format(DEATH_EXCEPTION_FORMAT, ex.toString());
        }
    }


    public String getGreeting() {
        return GREETING;
    }
}
