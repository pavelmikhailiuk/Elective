package by.epam.elective.service;

import by.epam.elective.dao.AbstractArchiveDAO;
import by.epam.elective.dao.ArchiveDAO;
import by.epam.elective.entity.Archive;
import by.epam.elective.exception.TechnicalException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class ArchiveService {
    private final static Logger LOGGER = Logger.getLogger(ArchiveService.class);
    private AbstractArchiveDAO archiveDAO = new ArchiveDAO();

    public ArrayList<Archive> findArchiveByCourseId(int courseId) {
        ArrayList<Archive> archives = null;
        try {
            archives = archiveDAO.findArchiveByCourseId(courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return archives;
    }

    public Archive findArchiveByCourseIdUserId(int courseId, int userId) {
        ArrayList<Archive> archives = null;
        Archive userArchive = null;
        try {
            archives = archiveDAO.findArchiveByCourseId(courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        if (archives != null && archives.size() != 0) {
            Iterator<Archive> archiveIterator = archives.iterator();
            while (archiveIterator.hasNext()) {
                Archive archive = archiveIterator.next();
                if (archive.getUserId() == userId) {
                    userArchive = archive;
                }
            }
        }
        return userArchive;
    }

    public boolean addArchive(int userId, int courseId) {
        boolean isDone = false;
        try {
            isDone = archiveDAO.addArchive(userId, courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }

    public boolean removeArchive(int userId, int courseId) {
        boolean isDone = false;
        try {
            isDone = archiveDAO.removeArchive(userId, courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }

    public boolean setMarkByUserId(int mark, int userId, int courseId) {
        boolean isDone = false;
        try {
            isDone = archiveDAO.setMarkByUserId(mark, userId, courseId);
        } catch (TechnicalException e) {
            LOGGER.error(e);
        }
        return isDone;
    }
}
