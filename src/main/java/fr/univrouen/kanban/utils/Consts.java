package fr.univrouen.kanban.utils;

public final class Consts {

    public static final String EMAIL_USED = "{ \"type\": \"error\", \"msg\": \"email already used!\" }";

    public static final String USER_SAVED = "{ \"type\": \"success\", \"msg\": \"user saved successfully.\" }";
    public static final String USER_DELETED = "{ \"type\": \"success\", \"msg\": \"user deleted successfully.\" }";
    public static final String USER_NOT_FOUND = "{ \"type\": \"error\", \"msg\": \"user not found.\" }";

    public static final String TASK_SAVED = "{ \"type\": \"success\", \"msg\": \"task saved successfully.\" }";
    public static final String TASK_DELETED = "{ \"type\": \"success\", \"msg\": \"task deleted successfully.\" }";
    public static final String TASK_NOT_FOUND = "{ \"type\": \"error\", \"msg\": \"task not found.\" }";

    public static final String LIST_SAVED = "{ \"type\": \"success\", \"msg\": \"list saved successfully.\" }";
    public static final String LIST_DELETED = "{ \"type\": \"success\", \"msg\": \"list deleted successfully.\" }";
    public static final String LIST_NOT_FOUND = "{ \"type\": \"error\", \"msg\": \"list not found.\" }";

    public static final String KANBAN_SAVED = "{ \"type\": \"success\", \"msg\": \"kanban saved successfully.\" }";
    public static final String KANBAN_DELETED = "{ \"type\": \"success\", \"msg\": \"kanban deleted successfully.\" }";
    public static final String KANBAN_NOT_FOUND = "{ \"type\": \"error\", \"msg\": \"kanban not found.\" }";
    public static final String KANBAN_VISIBILITY_PUBLIC = "PB";
    public static final String KANBAN_VISIBILITY_PRIVATE = "PR";



    private Consts() {
        throw new AssertionError();
    }
}
