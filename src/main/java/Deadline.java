import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDateTime.parse(by);
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = LocalDateTime.parse(newBy, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }
}