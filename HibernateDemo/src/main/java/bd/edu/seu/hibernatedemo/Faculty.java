/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.*;

/**
 *
 * @author kmhasan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Faculty {
    @Id
    private String initials;
    private String name;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    // TODO fix this part
    // TODO add proper unit tests to check that this indeed works
    // TODO fix the DAO implementations (retrieve operations)
    private List<Student> studentList;
}
