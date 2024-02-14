public class Deadline extends Task{
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}