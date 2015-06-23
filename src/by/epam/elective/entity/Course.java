package by.epam.elective.entity;

import java.util.Date;

public class Course extends AbstractEntity {
    private int id;
    private String name;
    private int statusId;
    private Date startDate;
    private Date endDate;

    public Course(String name, int statusId, Date startDate, Date endDate) {
        this.name = name;
        this.statusId = statusId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", statusId=" + statusId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
