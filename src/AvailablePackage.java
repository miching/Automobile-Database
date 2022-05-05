import jakarta.persistence.*;

@Entity(name = "availablePackage")
public class AvailablePackage
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int availableID;

    @Column(nullable = false)
    private Trim trim;

    @Column(nullable = false)
    private Package package1;
    
    private int cost;
    
    
}
