package exercise1;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */

    String name ;
    String registrationNumber ;
    Map<String, Integer>scoreCourse = new HashMap<>();


    public Student(String name, String registrationNumber) {
        this.name = requireNonNull(name, "name not null !") ;
        this.registrationNumber = requireNonNull(registrationNumber, "registration not null !");
    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {

        scoreCourse.put(course, score);

        requireNonNull(course, "course not null !");
        if (score<0 || score>20) {
            throw new IllegalArgumentException("Erreur : le score doit être entre 0 et 20 ! ");
        }

    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public int getScore(String course) {
        int score = scoreCourse.get(course);
        return score ;
    }

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {

        return scoreCourse.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        //.orElse permet de retourner un double d'office
        // Sinon on doit changer "public double averageScore" en "public OptionalDouble averageScore"
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {

        return scoreCourse.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).findFirst();
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {

        return scoreCourse.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).mapToInt(Map.Entry::getValue).findFirst().orElse(0);
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        return null;
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {

        return averageScore() >= 12 ;
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() {
        return scoreCourse.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String getName() {

        return name;
    }

    public String getRegistrationNumber() {

        return registrationNumber;
    }

    public Map<String, Integer> getScoreCourse() {
        return scoreCourse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
