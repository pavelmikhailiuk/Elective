package by.epam.elective.navigation;

import by.epam.elective.navigation.commands.*;

public enum Commands {
    CHANGE_LANGUAGE {
        @Override
        public Command getCommand() {
            return new ChangeLanguageCommand();
        }
    }, LOGIN {
        @Override
        public Command getCommand() {
            return new LoginCommand();
        }
    }, LOGOUT {
        @Override
        public Command getCommand() {
            return new LogoutCommand();
        }
    }, NEW_USER_PAGE {
        @Override
        public Command getCommand() {
            return new NewUserCommand();
        }
    }, REGISTER_NEW_USER {
        @Override
        public Command getCommand() {
            return new AddUserCommand();
        }
    }, ADD_COURSE {
        @Override
        public Command getCommand() {
            return new AddCourseCommand();
        }
    }, LIST_COURSES {
        @Override
        public Command getCommand() {
            return new ListCoursesCommand();
        }
    }, JOIN_COURSE {
        @Override
        public Command getCommand() {
            return new JoinCourseCommand();
        }
    }, LIST_STUDENTS {
        @Override
        public Command getCommand() {
            return new ListStudentsCommand();
        }
    }, DELETE_COURSE {
        @Override
        public Command getCommand() {
            return new DeleteCourseCommand();
        }
    }, ESCAPE_COURSE {
        @Override
        public Command getCommand() {
            return new EscapeCourseCommand();
        }
    }, END_COURSE {
        @Override
        public Command getCommand() {
            return new EndCourseCommand();
        }
    }, SET_MARKS {
        @Override
        public Command getCommand() {
            return new SetMarksCommand();
        }
    }, USER {
        @Override
        public Command getCommand() {
            return new UserCommand();
        }
    }, LIST_TEACHERS {
        @Override
        public Command getCommand() {
            return new ListTeachersCommand();
        }
    }, NEW_COURSE {
        @Override
        public Command getCommand() {
            return new NewCourseCommand();
        }
    }, CHANGE_COURSE {
        @Override
        public Command getCommand() {
            return new ChangeCourseCommand();
        }
    }, SET_COURSE {
        @Override
        public Command getCommand() {
            return new SetCourseCommand();
        }
    };

    public abstract Command getCommand();
}
