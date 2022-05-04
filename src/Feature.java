import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name = "feature")
public class Feature 
{

    @Id
    @Column(nullable = false)
    int featureID;

    @Column(length = 100, nullable = false, unique = true)
    String name;
    
}
