import jakarta.persistence.*;

@Entity (name = "automobile")
public class Automobile
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int autoID;

    @Column(nullable = false)
    private Trim trim;

    @Column(nullable = false, unique = true, length = 25)
    private String vin;
    
}
