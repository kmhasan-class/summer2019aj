package bd.edu.seu.coursemanagementserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"code"})
public class Course {
    @Id
    private String code;
    private String title;
    private List<Section> sectionList;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public void addSection(Section section) {
        if (sectionList == null)
            sectionList = new ArrayList<>();
        sectionList.add(section);
    }
}
