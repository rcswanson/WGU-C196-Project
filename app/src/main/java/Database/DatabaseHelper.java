package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import Models.Assessment;
import Models.Course;
import Models.Term;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "term_tracker.db";

    private static final String TERM_TABLE = "terms";
    private static final String TERM_ID = "term_id";
    private static final String TERM_NAME = "term_name";
    private static final String TERM_START = "term_start";
    private static final String TERM_END = "term_end";

    private static final String COURSES_TABLE = "courses";
    private static final String COURSE_ID = "course_id";
    private static final String COURSE_TERM_ID = "term_id";
    private static final String COURSE_NAME = "course_name";
    private static final String COURSE_START = "course_start";
    private static final String COURSE_END = "course_end";
    private static final String COURSE_STATUS = "course_status";
    private static final String COURSE_INST_NAME = "course_mentor";

    private static final String COURSE_INST_PHONE = "course_phone";

    private static final String COURSE_INST_EMAIL = "course_email";

    private static final String ASSESSMENTS_TABLE = "assessments";
    private static final String ASSESSMENT_ID = "assessment_id";
    private static final String ASSESSMENT_COURSE_ID = "course_id";

    private static final String ASSESSMENT_NAME = "assessment_name";
    private static final String ASSESSMENT_TYPE = "assessment_type";
    private static final String ASSESSMENT_DUE = "assessment_due";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTermTable = "CREATE TABLE " + TERM_TABLE + " (" +
                TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TERM_NAME + " TEXT, " +
                TERM_START + " TEXT, " +
                TERM_END + " TEXT" +
                ");";

        String createCourseTable = "CREATE TABLE " + COURSES_TABLE + " (" +
                COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COURSE_TERM_ID + " INTEGER, " +
                COURSE_NAME + " TEXT, " +
                COURSE_START + " TEXT, " +
                COURSE_END + " TEXT, " +
                COURSE_STATUS + " TEXT, " +
                COURSE_INST_NAME + " TEXT, " +
                COURSE_INST_PHONE + " TEXT, " +
                COURSE_INST_EMAIL + " TEXT, " +
                "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TERM_TABLE + "(" + TERM_ID + ")" +
                ");";

        String createAssessmentTable = "CREATE TABLE " + ASSESSMENTS_TABLE + " (" +
                ASSESSMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ASSESSMENT_COURSE_ID + " INTEGER, " +
                ASSESSMENT_NAME + " TEXT, " +
                ASSESSMENT_TYPE + " TEXT, " +
                ASSESSMENT_DUE + " TEXT, " +
                "FOREIGN KEY(" + ASSESSMENT_COURSE_ID + ") REFERENCES " + COURSES_TABLE + "(" + COURSE_ID + ")" +
                ");";

        db.execSQL(createTermTable);
        db.execSQL(createCourseTable);
        db.execSQL(createAssessmentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TERM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ASSESSMENTS_TABLE);
        onCreate(db);
    }

    public boolean insertTerm(String termTitle, Calendar startDate, Calendar endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TERM_NAME, termTitle);
        contentValues.put(TERM_START, String.valueOf(startDate.getTimeInMillis()));
        contentValues.put(TERM_END, String.valueOf(endDate.getTimeInMillis()));
        contentValues.put(TERM_ID, UUID.randomUUID().toString()); // generate a random ID

        long result = db.insert(TERM_TABLE, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }




    public long insertCourse(String name, Calendar startDate, Calendar endDate, String status, String instructorName, String instructorPhone, String instructorEmail, long termId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Course.COLUMN_NAME, name);
        values.put(Course.COLUMN_START_DATE, String.valueOf(startDate));
        values.put(Course.COLUMN_END_DATE, String.valueOf(endDate));
        values.put(Course.COLUMN_STATUS, status);
        values.put(Course.COLUMN_INST_NAME, instructorName);
        values.put(Course.COLUMN_INST_PHONE, instructorPhone);
        values.put(Course.COLUMN_INST_EMAIL, instructorEmail);
        values.put(Course.COLUMN_TERM_ID, termId);
        return db.insert(Course.TABLE_NAME, null, values);
    }



    public void insertAssessment(Assessment assessment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ASSESSMENT_NAME, assessment.getName());
        values.put(ASSESSMENT_TYPE, assessment.getType());
        values.put(ASSESSMENT_DUE, assessment.getDueDate());
        values.put(ASSESSMENT_COURSE_ID, assessment.getCourseId());
        db.insert(ASSESSMENTS_TABLE, null, values);
        db.close();
    }

//    public boolean updateTerm(Term term) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(TERM_START, term.getStartDate());
//        values.put(TERM_END, term.getEndDate());
//        values.put(TERM_NAME, term.getName());
//
//        int result = db.update(TERM_TABLE, values, TERM_ID + " = ?",
//                new String[] { String.valueOf(term.getId()) });
//        db.close();
//
//        if (result == 1) {
//            return true;
//        }
//        return false;
//    }

//    public boolean updateCourse(Course course) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COURSE_NAME, course.getName());
//        values.put(COURSE_START, course.getStartDate());
//        values.put(COURSE_END, course.getEndDate());
//        values.put(COURSE_TERM_ID, course.getTermId());
//
//        // updating row
//        int result = db.update(COURSES_TABLE, values, COURSE_ID + " = ?",
//                new String[]{ String.valueOf(course.getId()) });
//        db.close();
//
//        if (result == 1) {
//            return true;
//        }
//        return false;
//    }

//    public boolean updateAssessment(Assessment assessment) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(ASSESSMENT_NAME, assessment.getName());
//        values.put(ASSESSMENT_TYPE, assessment.getType());
//        values.put(ASSESSMENT_DUE, assessment.getDueDate());
//        values.put(ASSESSMENT_COURSE_ID, assessment.getCourseId());
//
//        // updating row
//        int result = db.update(ASSESSMENTS_TABLE, values, ASSESSMENT_ID + " = ?",
//                new String[]{ String.valueOf(assessment.getId()) });
//        db.close();
//
//        if (result == 1) {
//            return true;
//        }
//        return false;
//    }

    public int deleteTerm(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TERM_TABLE, TERM_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public int deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(COURSES_TABLE, COURSE_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public int deleteAssessment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ASSESSMENTS_TABLE, ASSESSMENT_ID + " = ?", new String[] {String.valueOf(id)});
    }

//    public Term getTerm(int termId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TERM_TABLE + " WHERE "
//                + TERM_ID + " = " + termId;
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            int termStartIndex = cursor.getColumnIndex(TERM_START);
//            int termEndIndex = cursor.getColumnIndex(TERM_END);
//
//            // convert the start and end date strings to calendar objects
//            String startDateStr = cursor.getString(termStartIndex);
//            long startDateMillis = Long.parseLong(startDateStr);
//            Calendar startDate = Calendar.getInstance();
//            startDate.setTimeInMillis(startDateMillis);
//
//            String endDateStr = cursor.getString(termEndIndex);
//            long endDateMillis = Long.parseLong(endDateStr);
//            Calendar endDate = Calendar.getInstance();
//            endDate.setTimeInMillis(endDateMillis);
//
//            // create a new Term object with the retrieved data
//            Term term = new Term(
//                    cursor.getInt(cursor.getColumnIndex(TERM_ID)),
//                    cursor.getString(cursor.getColumnIndex(TERM_NAME)),
//                    startDate,
//                    endDate
//            );
//
//            cursor.close();
//            db.close();
//
//            return term;
//        } else {
//            // handle the case where no rows were returned
//            return null;
//        }
//    }

    public Term getTermWithCourses(int termId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String termSelectQuery = "SELECT * FROM " + TERM_TABLE + " WHERE " + TERM_ID + " = " + termId;
        Cursor termCursor = db.rawQuery(termSelectQuery, null);

        Term term = null;

        if (termCursor != null && termCursor.moveToFirst()) {
            int termStartIndex = termCursor.getColumnIndex(TERM_START);
            int termEndIndex = termCursor.getColumnIndex(TERM_END);

            // convert the start and end date strings to calendar objects
            String startDateStr = termCursor.getString(termStartIndex);
            long startDateMillis = Long.parseLong(startDateStr);
            Calendar startDate = Calendar.getInstance();
            startDate.setTimeInMillis(startDateMillis);

            String endDateStr = termCursor.getString(termEndIndex);
            long endDateMillis = Long.parseLong(endDateStr);
            Calendar endDate = Calendar.getInstance();
            endDate.setTimeInMillis(endDateMillis);

            // create a new Term object with the retrieved data
            term = new Term(
                    termCursor.getInt(termCursor.getColumnIndex(TERM_ID)),
                    termCursor.getString(termCursor.getColumnIndex(TERM_NAME)),
                    startDate,
                    endDate
            );

            termCursor.close();

            // fetch the courses associated with this term
            String courseSelectQuery = "SELECT * FROM " + COURSES_TABLE + " WHERE " + TERM_ID + " = " + termId;
            Cursor courseCursor = db.rawQuery(courseSelectQuery, null);

            if (courseCursor != null && courseCursor.moveToFirst()) {
                do {
                    int courseId = courseCursor.getInt(courseCursor.getColumnIndex(COURSE_ID));
                    String courseTitle = courseCursor.getString(courseCursor.getColumnIndex(COURSE_NAME));
                    String courseStartDateStr = courseCursor.getString(courseCursor.getColumnIndex(COURSE_START));
                    long courseStartDateMillis = Long.parseLong(courseStartDateStr);
                    Calendar courseStartDate = Calendar.getInstance();
                    courseStartDate.setTimeInMillis(courseStartDateMillis);
                    String courseEndDateStr = courseCursor.getString(courseCursor.getColumnIndex(COURSE_END));
                    long courseEndDateMillis = Long.parseLong(courseEndDateStr);
                    Calendar courseEndDate = Calendar.getInstance();
                    courseEndDate.setTimeInMillis(courseEndDateMillis);

                    // create a new Course object and add it to the term's list of courses
                    Course course = new Course();
                    term.addCourse(course);
                } while (courseCursor.moveToNext());

                courseCursor.close();
            }
        }

        db.close();

        return term;
    }



    public List<Term> getAllTerms() {
        List<Term> terms = new ArrayList<>();

        // Define a query to select all rows from the terms table
        String selectQuery = "SELECT * FROM " + TERM_TABLE;

        // Open a connection to the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows in the cursor and create a Term object for each row
        if (cursor.moveToFirst()) {
            do {
                Term term = new Term();
                term.setId(cursor.getInt(0));
                term.setName(cursor.getString(1));
                term.setStartDate(cursor.getString(2));
                term.setEndDate(cursor.getString(3));

                terms.add(term);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database connection
        cursor.close();
        db.close();

        // Return the list of terms
        return terms;
    }
}
