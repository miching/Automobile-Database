import java.util.Collections;
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

    public AvailablePackage() 
    {



    }

    public AvailablePackage(Trim trim, Package package1, int cost)
    {
        
        this.trim = trim;
        this.package1 = package1;
        this.cost = cost;
        chosenPackages = Collections.emptySet();
    }

    public int getAvailableID() {
        return availableID;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public Package getPackage1() {
        return package1;
    }

    public void setPackage1(Package package1) {
        this.package1 = package1;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Automobile> getChosenPackages() {
        return chosenPackages;
    }

    public void setChosenPackages(Set<Automobile> chosenPackages) {
        this.chosenPackages = chosenPackages;
    }

    

}
