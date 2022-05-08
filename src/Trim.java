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

    public Trim(Model model, String trimName, int cost)
    {

      this.model = model;
      this.trimName = trimName;
      this.cost = cost;

    }

    public int getTrimID() {
      return trimID;
    }

    public Model getModel() {
      return model;
    }

    public void setModel(Model model) {
      this.model = model;
    }

    public String getTrimName() {
      return trimName;
    }

    public void setTrimName(String trimName) {
      this.trimName = trimName;
    }

    public int getCost() {
      return cost;
    }

    public void setCost(int cost) {
      this.cost = cost;
    }

    public List<Automobile> getAutomobiles() {
      return automobiles;
    }

    public void setAutomobiles(List<Automobile> automobiles) {
      this.automobiles = automobiles;
    }

    public Set<AvailablePackage> getAvailablePackages() {
      return availablePackages;
    }

    public void setAvailablePackages(Set<AvailablePackage> availablePackages) {
      this.availablePackages = availablePackages;
    }

    public Set<Feature> getTrimFeatures() {
      return trimFeatures;
    }

    public void setTrimFeatures(Set<Feature> trimFeatures) {
      this.trimFeatures = trimFeatures;
    }

  
    
}
