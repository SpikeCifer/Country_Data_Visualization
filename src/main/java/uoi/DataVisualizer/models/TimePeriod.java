package uoi.DataVisualizer.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TimePeriod {
    @Id
    private final int year;
    private final String quinquennial; // The word for 5 years (believe it or not)
    private final String decade;
    private final String vicennial; // The word for 20 years (again believe it or not)

}
