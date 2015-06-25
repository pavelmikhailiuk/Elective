package by.epam.elective.dao;

import by.epam.elective.entity.Archive;
import by.epam.elective.exception.TechnicalException;

import java.util.ArrayList;

public abstract class AbstractArchiveDAO extends AbstractDAO {

    public abstract ArrayList<Archive> findArchiveByCourseId(int courseId) throws TechnicalException;

    public abstract ArrayList<Archive> findArchiveByUserId(int userId) throws TechnicalException;

    public abstract boolean addArchive(int userId, int courseId) throws TechnicalException;

    public abstract boolean removeArchive(int userId, int courseId) throws TechnicalException;

    public abstract boolean setMarkByUserId(int mark, int userId, int courseId) throws TechnicalException;
}
