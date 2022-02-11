package thymeleafsandbox.stsm.business.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "ROWSS")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROW_ID")
    private Integer rowId;


    @Column(name = "VARIETY")
    private Variety variety = null;

    @Column(name="SEEDS_PER_CELL")
    private Integer seedsPerCell ;


}
