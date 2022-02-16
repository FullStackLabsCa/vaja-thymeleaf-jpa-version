package thymeleafsandbox.stsm.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name= "ROWSS")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROW_ID")
    private Integer rowId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VARIETY_ID")
    private Variety variety = null;

    @Column(name="SEEDS_PER_CELL")
    private Integer seedsPerCell ;


}
