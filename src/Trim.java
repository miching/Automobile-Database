import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;

@Entity (name = "trim")
public class Trim
{

    @Id
    @Column(length = 100, nullable = false)
    int trimID;

    //@UniqueConstraint(modelID, trimName)
    @Column(unique = true)
    int modelID;

    @Column(unique = true)
    String trimName;


    int cost;
    
}
