package by.epam.elective.dao;

import by.epam.elective.entity.Course;
import by.epam.elective.exception.TechnicalException;

import java.util.ArrayList;

public abstract class AbstractCourseDAO extends AbstractDAO {

    public abstract boolean addCourse(Course course) throws TechnicalException;

    public abstract ArrayList<Course> findCoursesByStatusAndUserId(int status, int userId, boolean joined) throws TechnicalException;

    public abstract ArrayList<Course> findCoursesByStatus(int status) throws TechnicalException;

    public abstract Course findCourseById(int courseId) throws TechnicalException;

    public abstract boolean changeCourse(Course course) throws TechnicalException;
}
