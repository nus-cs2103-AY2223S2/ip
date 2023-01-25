import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class AppLauncher {
	public static void main(String[] args) {
		Application.launch(Shao.class, args);
	}
}