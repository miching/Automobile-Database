import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "trim")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"modelID", "trimName"} ) } )
public class Trim
{

    @Id
    @Column(length = 100, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trimID;

    //Bidirectional Many-to-One with models
    @ManyToOne
    @JoinColumn(name = "modelID")
    //@Column(nullable = false)
    private Model model;

    @Column(nullable = false)
    private String trimName;

    private int cost;

    //Unidirectional One-To-Many with automobiles
    @OneToMany
    @JoinColumn(name = "trim")
    private List<Automobile> automobiles;

    //Bidirectional One-to-Many with availablePackges
    @OneToMany (mappedBy = "trim")
    private Set<AvailablePackage> availablePackages;

    //Unidirectional Many-to-Many with Features
    @ManyToMany 
    @JoinTable
    (

        name = "trimFeatures",
        joinColumns = @JoinColumn(name = "trimID"),
        inverseJoinColumns = @JoinColumn(name = "featureID")

    )
    private Set<Feature> trimFeatures;
    
}
