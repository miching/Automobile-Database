import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private Package package;
    
    private int cost;
    
    
}
