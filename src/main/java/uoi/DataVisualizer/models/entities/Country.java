package uoi.DataVisualizer.models.entities;

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
public class Country {
    @Id
    private final String code;
    private final String name;
}
