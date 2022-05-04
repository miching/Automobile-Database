import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "availablePackage")
public class AvailablePackage
{

    @Id
    @Column(nullable = false)
    int availableID;

    @Column(nullable = false)
    int trimID;

    @Column(nullable = false)
    int packageID;
    
    int cost;
    
    
}
