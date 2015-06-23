package by.epam.elective.entity;

public class Archive extends AbstractEntity {
    private int id;
    private int userId;
    private int courseId;
    private int mark;

    public Archive(int userId, int courseId, int mark) {
        this.userId = userId;
        this.courseId = courseId;
        this.mark = mark;
    }

    public Archive() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", mark=" + mark +
                '}';
    }
}
