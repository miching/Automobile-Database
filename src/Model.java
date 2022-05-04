import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name = "model")
public class Model 
{

    @Id
    @Column(nullable = false)
    int modelID;

    @Column(length = 100, unique = true)
    int modelName;

    @Column(unique = true)
    int year;
    
}
