import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name = "automobile")
public class Automobile
{

    @Id
    @Column(nullable = false)
    int autoID;

    @Column(nullable = false)
    int trimID;

    @Column(nullable = false, unique = true, length = 25)
    String vin;
    
}
