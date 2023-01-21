import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

class Storage {
	private File file;

	public Storage(File _file) {
		file = _file;
	}

	public TaskList read() throws IOException {
		return new TaskList(
		        Files.readAllLines(file.toPath())
		                .stream()
		                .map(line -> Task.unmarshal(line))
		                .collect(Collectors.toList()));
	}

	public void write(TaskList tasks) {
		try {
			Files.write(file.toPath(), tasks.marshal().getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to save tasks to file", e);
		}
	}
}
