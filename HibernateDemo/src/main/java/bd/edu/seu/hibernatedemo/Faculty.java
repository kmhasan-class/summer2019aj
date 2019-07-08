/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

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
}
