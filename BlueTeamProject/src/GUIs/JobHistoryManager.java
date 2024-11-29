package GUIs;


import User.EmployeeJob;
import java.util.ArrayList;
import java.util.List;

public class JobHistoryManager {
    private List<EmployeeJob> jobHistory;

    public JobHistoryManager() {
        jobHistory = new ArrayList<>();
    }

    // Add a job to the history
    public void addJob(EmployeeJob job) {
        jobHistory.add(job);
    }

    // Get all jobs
    public List<EmployeeJob> getJobs() {
        return jobHistory;
    }
}
