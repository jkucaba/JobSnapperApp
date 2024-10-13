package jk.jobsnapper.models;

public class JobOffer {
    private Long idoffer;
    private Long idUser;
    private String title;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private int peopleRequired;
    private String salary;
    private int phoneNumber;
    private String category;
    private String status;
    private String postedBy;
    private String postedDate;

    public JobOffer(Long idoffer, Long idUser, String title, String description, String location,
                    String startDate, String endDate, int peopleRequired, String salary,
                    int phone, String jobCategory, String jobStatus, String jobPostedBy,
                    String jobPostedDate) {
        this.idoffer = idoffer;
        this.idUser = idUser;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.peopleRequired = peopleRequired;
        this.salary = salary;
        this.phoneNumber = phone;
        this.category = jobCategory;
        this.status = jobStatus;
        this.postedBy = jobPostedBy;
        this.postedDate = jobPostedDate;
    }

    public Long getIdoffer() {
        return idoffer;
    }

    public void setIdoffer(Long idoffer) {
        this.idoffer = idoffer;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phone) {
        this.phoneNumber = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "idoffer=" + idoffer +
                ", idUser=" + idUser +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", peopleRequired=" + peopleRequired +
                ", salary='" + salary + '\'' +
                ", phone=" + phoneNumber +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", postedBy='" + postedBy + '\'' +
                ", postedDate='" + postedDate + '\'' +
                '}';
    }
}
