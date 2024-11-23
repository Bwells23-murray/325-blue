package GUIs;

import Job.Job;
import java.util.ArrayList;
import java.util.List;

public class JobHistoryManager {
    private List<Job> jobHistory;

    public JobHistoryManager() {
        jobHistory = new ArrayList<>();
    }

    // Add a job to the history
    public void addJob(Job job) {
        jobHistory.add(job);
    }

    // Get all jobs
    public List<Job> getJobs() {
        return jobHistory;
    }
}
