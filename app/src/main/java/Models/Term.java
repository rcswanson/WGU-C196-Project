package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Term {

    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
            courses.add(course);
    }
    public List<Course> getCourses() {
            return courses;
    }

    public static final String TABLE_NAME = "term";
    public static final String COLUMN_ID = "term_id";
    public static final String COLUMN_NAME = "term_name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_START_DATE + " TEXT,"
                    + COLUMN_END_DATE + " TEXT"
                    + ")";

    private static int id;
    private String name;
    private Calendar startDate;
    private Calendar endDate;

    public Term() {}

    public Term(int id, String name, Calendar startDate, Calendar endDate) {
        Term.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        Term.id = id;
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

    public void setStartDate(String startDateString) {
        try {
            startDate = Calendar.getInstance();
            startDate.setTime(Objects.requireNonNull(new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(startDateString)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDateString) {
        try {
            endDate = Calendar.getInstance();
            endDate.setTime(Objects.requireNonNull(new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(endDateString)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

