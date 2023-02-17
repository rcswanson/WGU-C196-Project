package Models;

import java.util.Calendar;

public class Course {
    public static final String TABLE_NAME = "course";
    public static final String COLUMN_ID = "course_id";
    public static final String COLUMN_NAME = "course_name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_INST_NAME = "instructor";
    public static final String COLUMN_INST_EMAIL = "email";
    public static final String COLUMN_INST_PHONE = "phone";
    public static final String COLUMN_TERM_ID = "term_id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_START_DATE + " TEXT,"
                    + COLUMN_END_DATE + " TEXT,"
                    + COLUMN_STATUS + " TEXT,"
                    + COLUMN_INST_NAME + " TEXT,"
                    + COLUMN_INST_EMAIL + " TEXT,"
                    + COLUMN_INST_PHONE + " TEXT,"
                    + COLUMN_TERM_ID + " INTEGER,"
                    + " FOREIGN KEY (" + COLUMN_TERM_ID + ") REFERENCES " + Term.TABLE_NAME + "(" + Term.COLUMN_ID + ")"
                    + ")";

    private int id;
    private String name;
    private Calendar startDate;
    private Calendar endDate;
    private String status;
    private String instName;
    private String instEmail;
    private String instPhone;
    private int termId;

    public Course() {}

    public Course(int id, String name, Calendar startDate, Calendar endDate, String status, String instName, String instEmail, String instPhone, int termId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instName = instName;
        this.instEmail = instEmail;
        this.instPhone = instPhone;
        this.termId = termId;
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

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() { return endDate; }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getInstName() { return instName; }

    public void setInstName(String instName) { this.instName = instName; }

    public String getInstEmail() { return instEmail; }

    public void setInstEmail(String instEmail) { this.instEmail = instEmail; }

    public String getInstPhone() { return instPhone; }

    public void setInstPhone(String instPhone) { this.instPhone = instPhone; }
    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}
