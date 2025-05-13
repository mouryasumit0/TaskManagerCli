import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 0;
    private int id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String title, String description){
        this.id = ++lastId;
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    public int getId() {
        return id;
    }

    public void markInProgress(){
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone(){
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTitle(String title){
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description){
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public String toJson(){
        return "{\"id\":\"" + id + "\" ,\"title\":\"" + title.strip() + "\",\"description\":\"" + description.strip() + "\" ,\"status\":\"" + status.toString() + "\",\"createdAt\":\"" + createdAt.format(formatter) + "\",\"updatedAt\":\"" + updatedAt.format(formatter) + "\"}";
    }

    public static Task fromJson(String json){
        json = json.replace("{", "").replace("}","").replace("\"", "");

        String[] json1 = json.split(",");

        String id = json1[0].split(":")[1].strip();
        String title = json1[1].split(":")[1].strip();
        String description = json1[2].split(":")[1].strip();
        String statusString = json1[3].split(":")[1].strip();
        String createdAtStr = json1[4].split("[a-z]:")[1].strip();
        String updatedAtStr = json1[5].split("[a-z]:")[1].strip();

        Status status = Status.valueOf(statusString.toUpperCase().replace(" ", "_"));
        Task task = new Task(title, description);
        task.id = Integer.parseInt(id);
        task.status = status;
        task.createdAt = LocalDateTime.parse(createdAtStr, formatter);
        task.updatedAt = LocalDateTime.parse(updatedAtStr, formatter);

        if(Integer.parseInt(id) > lastId){
            lastId = Integer.parseInt(id);
        }

        return task;
    }

    @Override
    public String toString() {
        return "id: " + id + ", title: " + title.strip() + ",description: " + description.strip() + ", status: " + status.toString() +
                ", createdAt: " + createdAt.format(formatter) + ", updatedAt: " + updatedAt.format(formatter);
    }
}
