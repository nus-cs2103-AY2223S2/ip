import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

class Storage {
	private File file;

	public Storage(File _file) {
		file = _file;
	}

	public List<Task> read() throws IOException {
		return Files.readAllLines(file.toPath())
		        .stream()
		        .map(line -> Task.unmarshal(line))
		        .collect(Collectors.toList());
	}

	public void write(List<Task> tasks) {
		String data = tasks.stream()
		        .map(task -> task.marshal())
		        .collect(Collectors.joining("\n"));
		try {
			Files.write(file.toPath(), data.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Failed to save tasks to file", e);
		}
	}
}