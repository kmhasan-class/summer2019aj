/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;

/**
 *
 * @author kmhasan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Student {
    @Id
    private long id;
    private Name name;
    private LocalDate dob;
    private String email;
    @ElementCollection
    private List<String> phoneList;
    // TODO create a list of Addresses, you'd have to create your own Address class
    // TODO add proper test cases to ensure that everything works
    // TODO add a Gender enumeration to record the gender of the student (follow the user guide)
}
