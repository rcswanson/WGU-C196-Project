package Models;

public class Assessment {
    public static final String TABLE_NAME = "assessment";
    public static final String COLUMN_ID = "assessment_id";
    public static final String COLUMN_NAME = "assessment_name";
    public static final String COLUMN_TYPE = "assessment_type";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_COURSE_ID = "course_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TYPE + " TEXT,"
                    + COLUMN_DUE_DATE + " TEXT,"
                    + COLUMN_COURSE_ID + " INTEGER,"
                    + " FOREIGN KEY (" + COLUMN_COURSE_ID + ") REFERENCES " + Course.TABLE_NAME + "(" + Course.COLUMN_ID + ")"
                    + ")";

    private int id;
    private String name;
    private String type;
    private String dueDate;
    private int courseId;

    public Assessment() {}

    public Assessment(int id, String name, String type, String dueDate, int courseId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dueDate = dueDate;
        this.courseId = courseId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
