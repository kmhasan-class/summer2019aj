/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

import java.time.LocalDate;
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
    private String name;
    private LocalDate dob;
}
