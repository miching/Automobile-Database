import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "model")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"modelName", "year"} ) } )
public class Model 
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelID;

    @Column(length = 100, nullable = false)
    private String modelName;

    private int year;

    //Unidirectional Many-to-Many with Features
    @ManyToMany 
    @JoinTable
    (

        name = "modelFeatures",
        joinColumns = @JoinColumn(name = "modelID"),
        inverseJoinColumns = @JoinColumn(name = "featureID")

    )
    private Set<Feature> modelFeatures;

    //Bidirectional One-to-Many with Trim
    @OneToMany(mappedBy = "model")
    private List<Trim> trims;

    public Model() 
    {



    }

    public Model(String modelName, int year)
    {

      this.modelName = modelName;
      this.year = year;

    }

    public int getModelID() {
      return modelID;
    }

    public String getModelName() {
      return modelName;
    }

    public void setModelName(String modelName) {
      this.modelName = modelName;
    }

    public int getYear() {
      return year;
    }

    public void setYear(int year) {
      this.year = year;
    }

    public Set<Feature> getModelFeatures() {
      return modelFeatures;
    }

    public void setModelFeatures(Set<Feature> modelFeatures) {
      this.modelFeatures = modelFeatures;
    }

    public List<Trim> getTrims() {
      return trims;
    }

    public void setTrims(List<Trim> trims) {
      this.trims = trims;
    }

    
    
}
