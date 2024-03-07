import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create the directory structure if it doesn't exist
            file.createNewFile(); // Create the file if it doesn't exist
        }
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task = null;
            switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
            }
            if (task != null) {
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
        }
        return loadedTasks;
    }

    public void save(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            String line = "";
            if (task instanceof ToDo) {
                line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                line = "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                line = "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
            }
            lines.add(line);
        }
        FileWriter writer = new FileWriter(filePath);
        for (String line : lines) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }
}

