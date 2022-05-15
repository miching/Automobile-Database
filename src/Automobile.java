import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import jakarta.validation.OverridesAttribute.List;

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

    public Automobile(int autoID, Trim trim, String vin)
    {
        
        this.autoID = autoID;
        this.trim = trim;
        this.vin = vin;
        chosenPackages = Collections.emptySet();
        
    }

    public int getAutoID() {
        return autoID;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Set<AvailablePackage> getChosenPackages() {
        return chosenPackages;
    }

    public void setChosenPackages(Set<AvailablePackage> chosenPackages) {
        this.chosenPackages = chosenPackages;
    }

    
    public Set<Feature> getFeatures()
    {
        
        //Set containing all features
        Set<Feature> allFeatures = new HashSet<Feature>();

        //Get all features from chosen packages
        for (AvailablePackage availableFeature : chosenPackages) 
        {
        
            Package temp = availableFeature.getPackage1();

            for(Feature f1: temp.getPackageFeatures() )
            {

                allFeatures.add(f1);

            }
               

        }

        //Get all features from Trim
        for (Feature trimFeature : trim.getTrimFeatures() ) 
        {
        
            allFeatures.add(trimFeature);

        }

        //Get all features from Model
        for (Feature modelFeature : trim.getModel().getModelFeatures() ) 
        {
        
            allFeatures.add(modelFeature);

        }

    
        
        return allFeatures;

    }


    public double stickerPrice()
    {

        double price = 0;

        //Add price of trim
        price = trim.getCost();


        //Get price from chosen packages
        for (AvailablePackage carPackages : chosenPackages) 
        {
        
            price += carPackages.getCost(); 

        }
        

        return price;
    }

    //Print Car Info in desired format
    @Override
    public String toString() 
    {

        Object [] listFeatures = this.getFeatures().toArray();

        Object temp ;
        //Sort list of features
        for(int i = 0 ; i < listFeatures.length ;i++)
        {

            for(int j = i + 1 ; i < listFeatures.length;i++)
            {

                if(listFeatures[i].toString().compareTo(listFeatures[j].toString()) > 0)
                {

                    temp = listFeatures[i];

                    listFeatures[i] = listFeatures[j];

                    listFeatures[j] = temp;

                }
            }
        }

        //Format of Car Info
        String carInfo = ("[" + trim.getModel().getYear() + "] " + "[" + trim.getModel().getModelName() + "] " + "[" + trim.getTrimName() + "]"+ "\n[" + this.stickerPrice() + "]\n");

        //Add all chosen car features to car info 
        for(int i = 0; i < listFeatures.length; i++)
        {

            carInfo = carInfo + "["+listFeatures[i] + "]\n";

        }

        return (carInfo);

    }








}
