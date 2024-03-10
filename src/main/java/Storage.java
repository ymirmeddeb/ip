import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private Path path;

    public Storage(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        this.filePath = filePath;
    }

    private void ensureFileExists() {
        try {
            File file = path.toFile();
            System.out.println("Attempting to use file path: " + filePath);
            // Ensure directories exist or create them
            boolean dirsCreated = file.getParentFile().mkdirs();
            if (!dirsCreated && !file.getParentFile().exists()) {
                throw new IOException("Failed to create parent directories for " + filePath);
            }
            // Now it's safe to attempt creating the file
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("Failed to create the file " + filePath);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while ensuring the data file exists: " + e.getMessage());
        }
        System.out.println("Attempting to use file path: " + filePath);
    }


    public List<Task> load() throws IOException {
        File file = new File(filePath);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            boolean dirsCreated = file.getParentFile().mkdirs();
            if (!dirsCreated) {
                System.err.println("Warning: Failed to create parent directories for " + filePath);
            }
        }
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                System.err.println("Warning: Failed to create the file " + filePath);
            }
            // Since the file is just created, it's empty. Return an empty list.
            return new ArrayList<>();
        }
        List<Task> loadedTasks = new ArrayList<>();
        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            Task task = null;
            switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    String desc = parts[2];
                    LocalDateTime by = LocalDateTime.parse(parts[3]); // Parse the datetime part directly to LocalDateTime
                    task = new Deadline(desc, by); // Pass the LocalDateTime object directly
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

