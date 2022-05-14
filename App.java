
import jakarta.persistence.*;
import models.*;
import models.Package;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

//import java.lang.Package;
import java.util.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;


public class App {

    private static void equalityDemos(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();
        return;
    }
    private static void addingFeatures(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        String [] features = {"leather seats","plug-in hybrid engine","power sliding doors",
                "hands-free sliding doors","Amazon FireTV","rear-seat entertainment screens",
                "all-wheel drive","adaptive cruise control"};

        int pkFeature = 1;

        for(String f : features){

            em.getTransaction().begin();

            Feature newFeature =  new Feature(pkFeature,f);

            em.persist(newFeature);

            pkFeature++;

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
    private static void addPakcages(int pk, String packageN,int featureid){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        if (em.find(Package.class,pk) == null){

            Package createdP = new Package(pk,packageN);

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

        Model newModel = new Model(pk, name, year);

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

        Trim newTrim = new Trim(pK,eModel,name,cost);

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

        AvailablePackage newAp = new AvailablePackage(pk,trimid,packageid,cost);

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

        Automobile newAuto = new Automobile(pk,trimid,vin);



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

    private static void carLookup(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN = ");

        String input = scanner.nextLine();

        String jpaQuery = "SELECT a FROM automobile a WHERE a.vin = '" + input +"'";

        Automobile auto = em.createQuery(jpaQuery,Automobile.class).getSingleResult();


        Set<Feature> sPset = auto.getFeatures();

//        for(Feature f : sPset){
//
//            System.out.println(f.getName());
//
//        }

        Trim autoTrim = em.find(Trim.class,auto.getTrim().getTrimID());

        System.out.println("[" +autoTrim.getModel().getYear() + "] " +"[" +autoTrim.getModel().getModelName() + "] " +"[" +autoTrim.getTrimName() + "] ");

        System.out.println("[" +autoTrim.getCost() + "]");




        Object [] stringTFeatures = sPset.toArray();

        Object temp ;
        for(int i = 0 ; i < stringTFeatures.length ;i++){

            for(int j = i + 1 ; i < stringTFeatures.length;i++){

                if(stringTFeatures[i].toString().compareTo(stringTFeatures[j].toString()) > 0){

                    temp = stringTFeatures[i];

                    stringTFeatures[i] = stringTFeatures[j];

                    stringTFeatures[j] = temp;

                }
            }
        }

        Feature [] fTrim = Arrays.stream(stringTFeatures).toArray(Feature[]::new);


        for(Feature f : fTrim){

            System.out.println("[" + f.getName() + "]");
        }
//        for
//
//        Model autoModel = autoTrim.getModel();
//
//        System.out.println(autoModel.getYear());



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




    public static void main(String[] args) throws Exception {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();

//        basicDemos();
        //associationDemos();
//        equalityDemos();
        String [] packages = {"Theater Package","Amazon Theater Package", "Safety Package"};

        addingFeatures();
//        printFeatures();
//        addModels();
//        System.out.println();
        addPakcages(1,packages[0],7);
        addPakcages(2,packages[1],6);
        addPakcages(2,packages[1],5);
        addPakcages(3,packages[2],8);
//        testPackageFeatures();



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

        addTrimfeatures(phTl2022,4);
        addTrimfeatures(phTp2022,5);

        int [] phTl2021 = {1,4};
        int [] phTp20221 = {1,4,6,8};

        addTrimfeatures(phTl2021,6);
        addTrimfeatures(phTp20221,8);


        setAvailablepackages(1,em.find(Trim.class,1),em.find(Package.class,3),3000);
        setAvailablepackages(2,em.find(Trim.class,2),em.find(Package.class,2),2500);
        setAvailablepackages(3,em.find(Trim.class,5),em.find(Package.class,2),2500);
        setAvailablepackages(4,em.find(Trim.class,7),em.find(Package.class,3),3000);
        setAvailablepackages(6,em.find(Trim.class,8),em.find(Package.class,1),2500);
        setAvailablepackages(7,em.find(Trim.class,8),em.find(Package.class,3),2000);


        int [] firstAuto = {1};
        int [] secondAuto = {};
        int [] thirdAuto = {};
        int [] fourthAuto = {3};
        int [] fifthAuto = {5,6};

        addAutomobiles(1,em.find(Trim.class,2),"12345abcde",firstAuto);
        addAutomobiles(2,em.find(Trim.class,6),"67890abcde",secondAuto);
        addAutomobiles(3,em.find(Trim.class,9),"99999aaaaa",thirdAuto);
        addAutomobiles(4,em.find(Trim.class,7),"aaaaa88888",fourthAuto);
        addAutomobiles(5,em.find(Trim.class,8),"bbbbb77777",fifthAuto);

        carLookup();
//        automobilWithf();

    }




}
