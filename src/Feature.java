import java.util.Collections;
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

    private Set<Package> packageFeatures;

    private Set<Trim> trimFeatures;

    private Set<Model> modelFeatures;

    public Feature()
    {



    }

    public Feature(String name)
    {

        this.name = name;
        packageFeatures = Collections.emptySet();
        trimFeatures = Collections.emptySet();
        modelFeatures = Collections.emptySet();

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
        return packageFeatures;
    }

    public void setPackageFeature(Set<Package> packageFeature) {
        this.packageFeatures = packageFeature;
    }

    public Set<Trim> getTrimFeature() {
        return trimFeatures;
    }

    public void setTrimFeature(Set<Trim> trimFeature) {
        this.trimFeatures = trimFeature;
    }

    public Set<Model> getModelFeature() {
        return modelFeatures;
    }

    public void setModelFeature(Set<Model> modelFeature) {
        this.modelFeatures = modelFeature;
    }

    
    
}
