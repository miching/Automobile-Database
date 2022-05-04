import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name = "package")
public class Package 
{
    @Id
    @Column(nullable = false)
    int packageID;

    @Column(length = 100, nullable = false)
    String packageName;
    
}
