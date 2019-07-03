/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.mavenlombokdbdemo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author kmhasan
 */
@Data // all getters, all setters, toString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Student {
    private long id;
    private String name;
    private LocalDate dob;

}
