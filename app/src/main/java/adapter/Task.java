package adapter;

import java.io.Serializable;

public class Task implements Serializable {
    private String taskName;
    private String description;
    private boolean taskCheck;

    public Task(String taskName, String description, boolean taskCheck) {
        this.taskName = taskName;
        this.description = description;
        this.taskCheck = taskCheck;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTaskCheck() {
        return taskCheck;
    }

    public void setTaskCheck(boolean taskCheck) {
        this.taskCheck = taskCheck;
    }
}
