public class SamInvalidDateException extends SamException {
  public SamInvalidDateException() {
    super("Please write dates as 'd/M/yyyy'!");
  }
}