import java.util.Set;

import jakarta.persistence.*;

@Entity(name = "availablePackage")
public class AvailablePackage
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int availableID;

    @ManyToOne
    @JoinColumn(name = "trimID")
    private Trim trim;

    @ManyToOne
    @JoinColumn(name = "packageID")
    private Package package1;
    
    private int cost;

    //Bidirectional Many-to-Many with automobile
    @ManyToMany(mappedBy = "chosenPackages")
    private Set<Automobile> chosenPackages;
    
    
}
