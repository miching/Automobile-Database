import jakarta.persistence.*;
import java.util.*;

public class App {

    private static void addingFeatures(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        String [] features = {"leather seats","plug-in hybrid engine","power sliding doors",
                "hands-free sliding doors","Amazon FireTV","rear-seat entertainment screens",
                "all-wheel drive","adaptive cruise control"};

        for(String f : features){

            em.getTransaction().begin();

            Feature newFeature =  new Feature(f);

            em.persist(newFeature);

            em.getTransaction().commit();

        }


    }

    private static void printFeatures(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        String jpaQuery = "SELECT f FROM feature f";

        List<Feature> featureList = em.createQuery(jpaQuery,Feature.class).getResultList();

        for(Feature f : featureList){

            System.out.println(f.getName());

        }

    }
    private static void addPackages(int pk, String packageN,int featureid){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        if (em.find(Package.class,pk) == null){

            Package createdP = new Package(packageN);

            Set<Feature> newSet = new HashSet<>();

            Feature existingFeature = em.find(Feature.class,featureid);

            newSet.add(existingFeature);

            em.getTransaction().begin();

            em.persist(createdP);

            createdP.setPackageFeatures(newSet);

            em.getTransaction().commit();

        }
        else {

            Package existingPackage = em.find(Package.class,pk);

            Feature existingFeature = em.find(Feature.class,featureid);

            em.getTransaction().begin();

            existingPackage.getPackageFeatures().add(existingFeature);

            em.getTransaction().commit();

        }

    }

    private static void  testPackageFeatures(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        System.out.println("*Packages");

        String jpaQuery = "SELECT p FROM package p";

        List<Package> packageSet = em.createQuery(jpaQuery,Package.class).getResultList();

        for(Package p : packageSet){

            System.out.println("*"+p.getPackageName());

            for(Feature f : p.getPackageFeatures()){
                System.out.print("   " + f.getName() + ";");
            }
            System.out.println();
        }
        System.out.println(" ");


    }

    private static void addModels(int pk, String name,int year){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Model newModel = new Model(name, year);

        em.persist(newModel);

        em.getTransaction().commit();


    }

    private static void addModelfeatures(int [] featureIDs,int modelID){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Set<Feature> modelSet = new HashSet<Feature>();

        for (int i : featureIDs){

            Feature eF = em.find(Feature.class,i);

            modelSet.add(eF);

        }

        Model eModel = em.find(Model.class,modelID);

        em.getTransaction().begin();

        eModel.setModelFeatures(modelSet);

        em.getTransaction().commit();

    }

    private static void testModelFeatures(int pN){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Model eM = em.find(Model.class,pN);

        System.out.println(eM.getModelID() + " " + eM.getModelName() + " " + eM.getYear());

        for(Feature f : eM.getModelFeatures()){

            System.out.println(f.getName()+" "+f.getFeatureID());

        }


    }

    private static void addTrims(int pK , Model eModel, String name, int cost){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Trim newTrim = new Trim(eModel,name,cost);

        Model mTrim = em.find(Model.class,eModel.getModelID());

        List<Trim> trimList = mTrim.getTrims();

        trimList.add(newTrim);

        mTrim.setTrims(trimList);

        em.persist(newTrim);

        em.getTransaction().commit();

    }

    private static void checkTrims(int pk){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Model eM = em.find(Model.class,pk);
        System.out.println(eM.getModelName() + " " + eM.getYear());
        for(Trim t : eM.getTrims()){
            System.out.println(t.getTrimName() +"(" + t.getCost()+ ")");
        }
        System.out.println();


    }

    private static  void addTrimfeatures(int [] trimFeatures , int trimId){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Set<Feature> trimSet = new HashSet<Feature>();

        for(int i : trimFeatures){

            Feature tF = em.find(Feature.class,i);

            trimSet.add(tF);

        }

        Trim eTrim = em.find(Trim.class,trimId);

        em.getTransaction().begin();

        eTrim.setTrimFeatures(trimSet);

        em.getTransaction().commit();

    }

    private static void checktF(int pk){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Trim eT = em.find(Trim.class,pk);

        System.out.println(eT.getTrimName());

        for(Feature t : eT.getTrimFeatures()){

            System.out.println(t.getName() + "" + t.getFeatureID());
        }

    }

    private static void setAvailablepackages(int pk, Trim trimid, Package packageid, int cost){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        AvailablePackage newAp = new AvailablePackage(trimid,packageid,cost);

        Trim tTrim = em.find(Trim.class,trimid.getTrimID());

        Set<AvailablePackage> eAp = tTrim.getAvailablePackages();

        eAp.add(newAp);

        tTrim.setAvailablePackages(eAp);

        em.persist(newAp);

        em.getTransaction().commit();

    }



    private static void addAutomobiles(int pk, Trim trimid, String vin,int [] aP){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Automobile newAuto = new Automobile(trimid,vin);

        Set<AvailablePackage> newApset = new HashSet<>();

        if(aP.length != 0){

            for(int i : aP){

                AvailablePackage createdAp = em.find(AvailablePackage.class,i);

                newApset.add(createdAp);

            }
        }
        em.getTransaction().begin();
        em.persist(newAuto);
        newAuto.setChosenPackages(newApset);
        em.getTransaction().commit();

    }

    private static void checkAp(int pk){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Set<AvailablePackage> aPset = em.find(Automobile.class,pk).getChosenPackages();

        for(AvailablePackage aP : aPset){

            System.out.println(aP.getPackage1().getPackageName());

        }

    }

    //Option #2 - Look up car information with a VIN
    private static void carLookup(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

         //Get User-input VIN
        System.out.print("Enter VIN = ");
        String inputVIN = scanner.nextLine();


        //Parameterized Queries
        var carVIN = em.createQuery("SELECT a FROM automobile a where a.vin = ?1", Automobile.class);
        carVIN.setParameter(1, inputVIN);

        //Check if car VIN exists
        try{

            Automobile car = carVIN.getSingleResult();
            System.out.println("Your requested automobile: \n" + car);

        }
        catch(NoResultException e)
        {

            System.out.println("No automobile with the VIN: " + inputVIN + " could be found.");

        }
        

    }

    //Option 3 - Find all automobiles with a certain feature
    private static void automobileWithFeature()
    {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        //Get User-input Feature
        System.out.print("Enter Feature = ");
        String inputFeature = scanner.nextLine();

        //

        //Parameterized Queries
        var carWithFeature = em.createQuery("SELECT a FROM automobile a JOIN a.trim t JOIN t.trimFeatures tf WHERE tf.name = ?1", Automobile.class);
        carWithFeature.setParameter(1, inputFeature);

        //Parameterized Queries
        var carWithFeature2 = em.createQuery("SELECT a FROM automobile a JOIN a.trim t JOIN t.model m JOIN m.modelFeatures mF WHERE mF.name = ?1", Automobile.class);
        carWithFeature2.setParameter(1, inputFeature);

        //Parameterized Queries
        var carWithFeature3 = em.createQuery("SELECT a FROM automobile a JOIN a.chosenPackages cP JOIN cP.package1 p1 JOIN p1.packageFeatures pF WHERE pF.name = ?1", Automobile.class);
        carWithFeature3.setParameter(1, inputFeature);

        //Check if cars have this feature
        try{

            List<Automobile> carsWithFeature = carWithFeature.getResultList();

            List<Automobile> carsWithFeature2 = carWithFeature2.getResultList();

            List<Automobile> carsWithFeature3 = carWithFeature3.getResultList();

            Set <Automobile> allCarsWithFeature = new HashSet<Automobile>();

            allCarsWithFeature.addAll(carsWithFeature);
            allCarsWithFeature.addAll(carsWithFeature2);
            allCarsWithFeature.addAll(carsWithFeature3);

            System.out.println("Automobiles with feature: " + inputFeature + "\n");
            for (Automobile car: allCarsWithFeature)
            {

                System.out.println(car.getVin());

            }
            

        }
        catch(NoResultException e)
        {

            System.out.println("Feature: " + inputFeature + " could be found in a car.");

        }


    }

//    private static void automobilWithf(){
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
//        EntityManager em = factory.createEntityManager();
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter Feature Name = ");
//
//
//        String input = scanner.nextLine();
//
////        String jpaQueary = "SELECT f FROM feature f WHERE f.name = '" + input +"'";
//
//        String q = "SELECT t FROM trim t JOIN"
//
//        List<Automobile> listA = em.createQuery(query, Automobile.class).getResultList();
//
//        System.out.println(listA);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }

    //Option #1 - Instantiate Model
    public static void instantiateModel()
    {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        //Check if values already in DB
        Feature firstFeature = em.find(Feature.class, 1); // parameter 1: the primary key value.

        String [] features = {"leather seats","plug-in hybrid engine","power sliding doors",
                "hands-free sliding doors","Amazon FireTV","rear-seat entertainment screens",
                "all-wheel drive","adaptive cruise control"};

        //Only create model once, if it already exists don't instantiate again
        if(firstFeature == null)
        {

            for(String f : features){

                em.getTransaction().begin();
    
                Feature newFeature =  new Feature(f);
    
                em.persist(newFeature);
    
                em.getTransaction().commit();
    
            }
          
            String [] packages = {"Theater Package","Amazon Theater Package", "Safety Package"};
    
            addPackages(1,packages[0],6);
            addPackages(2,packages[1],6);
            addPackages(2,packages[1],5);
            addPackages(3,packages[2],8);
    
    //        System.out.println("__________________");
    
            addModels(1,"Pacifica", 2022);
            addModels(2,"Pacifica Hybrid", 2022);
            addModels(3,"Pacifica Hybrid", 2021);
    
            int [] p2022 = {3};
            int [] ph2022 = {3,2};
            int [] ph2021 = {3,2};
            addModelfeatures(p2022,1);
    //        testModelFeatures(1);
            addModelfeatures(ph2022,2);
    //        testModelFeatures(2);
            addModelfeatures(ph2021,3);
    //        testModelFeatures(3);
    
    
            Model pM2022 = em.find(Model.class,1);
            Model phM2022 = em.find(Model.class,2);
            Model phM2021 = em.find(Model.class,3);
    
            String [] tNames = {"Touring","Limited","Pinnacle"};
    
            addTrims(1,pM2022,tNames[0],30000);
            addTrims(2,pM2022,tNames[1],34000);
            addTrims(3,pM2022,tNames[2],42000);
    
            addTrims(4,phM2022,tNames[0],43000);
            addTrims(5,phM2022,tNames[1],48000);
            addTrims(6,phM2022,tNames[2],54000);
    
            addTrims(7,phM2021,tNames[0],41000);
            addTrims(8,phM2021,tNames[1],46000);
            addTrims(9,phM2021,tNames[2],52000);
    
    //        checkTrims(1);
    //        checkTrims(2);
    //        checkTrims(3);
    
    //        System.out.println("____________");
    
            int [] pTl2022 = {1,4};
            int [] pTp2022 = {1,4,6,5,7};
    
            addTrimfeatures(pTl2022,2);
            addTrimfeatures(pTp2022,3);
    
    //        checktF(2);
    //        checktF(3);
    
            int [] phTl2022 = {1,4};
            int [] phTp2022 = {1,4,6,5};
    
            addTrimfeatures(phTl2022,5);
            addTrimfeatures(phTp2022,6);
    
            int [] phTl2021 = {1,4};
            int [] phTp20221 = {1,4,6,8};
    
            addTrimfeatures(phTl2021,8);
            addTrimfeatures(phTp20221,9);
    
    
            setAvailablepackages(1,em.find(Trim.class,1),em.find(Package.class,3),3000);
            setAvailablepackages(2,em.find(Trim.class,2),em.find(Package.class,2),2500);
            setAvailablepackages(3,em.find(Trim.class,5),em.find(Package.class,2),2500);
            setAvailablepackages(4,em.find(Trim.class,7),em.find(Package.class,3),3000);
            setAvailablepackages(5,em.find(Trim.class,8),em.find(Package.class,1),2500);
            setAvailablepackages(6,em.find(Trim.class,8),em.find(Package.class,3),2000);
    
    
            //These are chossen packages should match trim and package
            int [] firstAuto = {2};
            int [] secondAuto = {};
            int [] thirdAuto = {};
            int [] fourthAuto = {4};
            int [] fifthAuto = {5,6};
    
            addAutomobiles(1,em.find(Trim.class,2),"12345abcde",firstAuto);
            addAutomobiles(2,em.find(Trim.class,6),"67890abcde",secondAuto);
            addAutomobiles(3,em.find(Trim.class,9),"99999aaaaa",thirdAuto);
            addAutomobiles(4,em.find(Trim.class,7),"aaaaa88888",fourthAuto);
            addAutomobiles(5,em.find(Trim.class,8),"bbbbb77777",fifthAuto);

        }
        

    }

    public static void printMenu()
    {

        System.out.println("1) Instantiate model.");
        System.out.println("2) Automobile lookup.");
        System.out.println("3) Feature search.");
        System.out.println("4) Exit.");
        System.out.println("Enter int: ");

    }


    public static void main(String[] args) throws Exception
    {

        printMenu();
        Scanner scan = new Scanner(System.in);
        
        try{

            int userInput = scan.nextInt();
            while(userInput != 4)
            {

                //Option: Create Model
                if(userInput == 1)
                {

                    instantiateModel();

                }

                //Option: Car Lookup
                else if(userInput == 2)
                {

                    carLookup();

                }

                //Option: Cars containing feature
                else if(userInput == 3)
                {

                    automobileWithFeature();

                }
                else
                {

                    System.out.println("Invalid user input");

                }

                printMenu();
                try{

                    userInput = scan.nextInt();

                }catch(InputMismatchException e)
                {
        
                    System.out.println("Input mismatch. Exit.");
                    userInput = 4;
        
                }

            }

        }catch(InputMismatchException e)
        {

            System.out.println("Input mismatch. Exit.");

        }

    }




}