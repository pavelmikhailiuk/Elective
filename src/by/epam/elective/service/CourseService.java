package by.epam.elective.service;

import by.epam.elective.dao.AbstractCourseDAO;
import by.epam.elective.dao.CourseDAO;
import by.epam.elective.entity.Course;
import by.epam.elective.exception.LogicalException;
import by.epam.elective.exception.TechnicalException;
import by.epam.elective.resource.ConfigurationManager;
import by.epam.elective.validator.FormValidator;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseService {
    private final static Logger LOGGER = Logger.getLogger(CourseService.class);
    public static final int COURSE_END_STATUS = 2;
    public static final int COURSE_OPENED_STATUS = 0;
    public static final int COURSE_CLOSED_STATUS = 1;
    public static final int ADMIN_ROLE = 1;
    public static final int TEACHER_ROLE = 2;
    public static final int STUDENT_ROLE = 3;
    public static final String DATE_PARSE_PATTERN = "yyyy-MM-dd";
    public static final int COURSE_OPENED_AND_CLOSED_STATUS = 10;
    private AbstractCourseDAO courseDAO = new CourseDAO();

    public boolean addCourse(String courseName, int status, String startDate, String endDate, int teacherId) throws LogicalException {
        boolean isDone = false;
        FormValidator validator = new FormValidator();
        if ((validator.validate("startDate", startDate)) && (validator.validate("endDate", endDate))) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PARSE_PATTERN);
                Date parsedStartDate = simpleDateFormat.parse(startDate);
                Date parsedEndDate = simpleDateFormat.parse(endDate);
                Course newCourse = new Course(courseName, status, parsedStartDate, parsedEndDate);
                isDone = courseDAO.addCourse(newCourse);
                ArrayList<Course> courses = courseDAO.findCoursesByStatus(COURSE_OPENED_STATUS);
                int courseId = 0;
                for (Course course : courses) {
                    if (course.getName().equals(courseName)) {
                        courseId = course.getId();
                    }
                }
                ArchiveService archiveService = new ArchiveService();
                archiveService.addArchive(teacherId, courseId);
            } catch (TechnicalException | ParseException e) {
                LOGGER.error(e);
            }
        } else {
            throw new LogicalException(ConfigurationManager.getProperty("validation.error"));
        }
        return isDone;
    }

    public ArrayList<Course> findCoursesByStatus(int status, int role, int userId) {
        ArrayList<Course> courses = null;
        try {
            switch (role) {
                case ADMIN_ROLE:
                    courses = adminAction(status);
                    break;
                case TEACHER_ROLE:
                    courses = teacherAction(status, userId);
                    break;
                case STUDENT_ROLE:
                    courses = studentAction(status, userId);
                    break;
            }
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return courses;
    }

    public boolean changeCourse(Course course) {
        boolean isDone = false;
        try {
            isDone = courseDAO.changeCourse(course);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }

    public boolean deleteCourse(int courseId) {
        boolean isDone = false;
        try {
            isDone = courseDAO.deleteCourse(courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }

    public Course findCourseById(int courseId) {
        Course course = null;
        try {
            course = courseDAO.findCourseById(courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return course;
    }

    private ArrayList<Course> adminAction(int status) throws TechnicalException {
        ArrayList<Course> courses = null;
        if (status == COURSE_END_STATUS) {
            courses = courseDAO.findCoursesByStatus(status);
        } else {
            courses = courseDAO.findCoursesByStatus(COURSE_OPENED_STATUS);
            courses.addAll(courseDAO.findCoursesByStatus(COURSE_CLOSED_STATUS));
        }
        return courses;
    }

    private ArrayList<Course> teacherAction(int status, int userId) throws TechnicalException {
        ArrayList<Course> courses = null;
        boolean isJoined = true;
        if (status == COURSE_END_STATUS) {
            courses = courseDAO.findCoursesByStatusAndUserId(status, userId, isJoined);
        } else {
            courses = courseDAO.findCoursesByStatusAndUserId(COURSE_OPENED_STATUS, userId, isJoined);
            courses.addAll(courseDAO.findCoursesByStatusAndUserId(COURSE_CLOSED_STATUS, userId, isJoined));
        }
        return courses;
    }

    private ArrayList<Course> studentAction(int status, int userId) throws TechnicalException {
        ArrayList<Course> courses = null;
        boolean isJoined = true;
        if (status == COURSE_END_STATUS) {
            courses = courseDAO.findCoursesByStatusAndUserId(status, userId, isJoined);
        } else if (status == COURSE_OPENED_AND_CLOSED_STATUS) {
            courses = courseDAO.findCoursesByStatusAndUserId(COURSE_OPENED_STATUS, userId, isJoined);
            courses.addAll(courseDAO.findCoursesByStatusAndUserId(COURSE_CLOSED_STATUS, userId, isJoined));
        } else if (status == COURSE_OPENED_STATUS) {
            isJoined = false;
            courses = courseDAO.findCoursesByStatusAndUserId(status, userId, isJoined);
        }
        return courses;
    }
}
