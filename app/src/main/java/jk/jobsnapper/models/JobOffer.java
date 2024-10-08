package jk.jobsnapper.models;

public class JobOffer {
    private Long idoffer;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private int peopleRequired;
    private Double salary;
    private String jobType;
    private String jobCategory;
    private String jobStatus;
    private String jobPostedBy;
    private String jobPostedDate;

    public JobOffer(Long idoffer, String title, String description, String location,
                    String startDate, String endDate, int peopleRequired, Double salary,
                    String jobType, String jobCategory, String jobStatus, String jobPostedBy,
                    String jobPostedDate) {
        this.idoffer = idoffer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.peopleRequired = peopleRequired;
        this.salary = salary;
        this.jobType = jobType;
        this.jobCategory = jobCategory;
        this.jobStatus = jobStatus;
        this.jobPostedBy = jobPostedBy;
        this.jobPostedDate = jobPostedDate;
    }

    public Long getIdoffer() {
        return idoffer;
    }

    public void setIdoffer(Long idoffer) {
        this.idoffer = idoffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPeopleRequired() {
        return peopleRequired;
    }

    public void setPeopleRequired(int peopleRequired) {
        this.peopleRequired = peopleRequired;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobPostedBy() {
        return jobPostedBy;
    }

    public void setJobPostedBy(String jobPostedBy) {
        this.jobPostedBy = jobPostedBy;
    }

    public String getJobPostedDate() {
        return jobPostedDate;
    }

    public void setJobPostedDate(String jobPostedDate) {
        this.jobPostedDate = jobPostedDate;
    }
}
