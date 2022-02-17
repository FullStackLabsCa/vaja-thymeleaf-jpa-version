package thymeleafsandbox.stsm.business.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Row {

    private Integer rowId;


    private Variety variety = null;

    private Integer seedsPerCell ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Objects.equals(rowId, row.rowId) && Objects.equals(variety, row.variety) && Objects.equals(seedsPerCell, row.seedsPerCell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowId, variety, seedsPerCell);
    }
}
