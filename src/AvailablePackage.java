import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "availablePackage")
public class AvailablePackage
{

    @Id
    @Column(nullable = false)
    int availableID;

    
    int trimID;
    int packageID;
    int cost;
    
    
}
