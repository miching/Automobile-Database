import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "automobile")
public class Automobile
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int autoID;

    //Unidirectional Many-to-One with Trims
    //@Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "trimID")
    private Trim trim;

    @Column(nullable = false, unique = true, length = 25)
    private String vin;

    //Bidirectional Many-to-Many with availablePackages
    @ManyToMany
    @JoinTable
    (

        name = "chosenPackages",
        joinColumns = @JoinColumn(name = "autoID"),
        inverseJoinColumns = @JoinColumn(name = "availableID")

    )
    private Set<AvailablePackage> chosenPackages;

    public Automobile() 
    {


        
    }

    
    
}
