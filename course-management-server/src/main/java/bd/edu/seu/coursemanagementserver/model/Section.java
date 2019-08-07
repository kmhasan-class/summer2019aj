package bd.edu.seu.coursemanagementserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @ToString.Exclude
    private Course course;
    private String faculty;
    private int number;
    private int capacity;
    private int semesterId;

    @ToString.Include
    private String getLabel() {
        return course.getCode() + "." + number + "." + semesterId;
    }
}
