
package nus.iss.ca.leave_application.helper;

/**
 * @version 1.0
 * @author Jane
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Decision {
    private String decision;
    private String comment;
}
