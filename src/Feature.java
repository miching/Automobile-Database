import java.util.Set;

import jakarta.persistence.*;

@Entity (name = "feature")
public class Feature 
{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int featureID;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    private Set<Package> packageFeature;

    private Set<Trim> trimFeature;

    private Set<Model> modelFeature;

    public Feature()
    {



    }

    public Feature(String name)
    {

        this.name = name;

    }

    public int getFeatureID() {
        return featureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Package> getPackageFeature() {
        return packageFeature;
    }

    public void setPackageFeature(Set<Package> packageFeature) {
        this.packageFeature = packageFeature;
    }

    public Set<Trim> getTrimFeature() {
        return trimFeature;
    }

    public void setTrimFeature(Set<Trim> trimFeature) {
        this.trimFeature = trimFeature;
    }

    public Set<Model> getModelFeature() {
        return modelFeature;
    }

    public void setModelFeature(Set<Model> modelFeature) {
        this.modelFeature = modelFeature;
    }

    
    
}
