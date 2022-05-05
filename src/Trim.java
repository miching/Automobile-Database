import jakarta.persistence.*;

@Entity (name = "trim")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"modeID", "trimName"} ) } )
public class Trim
{

    @Id
    @Column(length = 100, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trimID;

    @Column(nullable = false)
    private Model model;

    @Column(nullable = false)
    private String trimName;


    private int cost;
    
}
