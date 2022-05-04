import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity (name = "model")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"modelName", "year"} ) } )
public class Model 
{

    @Id
    @Column(nullable = false)
    int modelID;

    @Column(length = 100, nullable = false)
    String modelName;

    int year;
    
}
