
import jakarta.persistence.*;
import models.*;

import javax.swing.text.html.parser.Entity;


public class App {

    private static void equalityDemos(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project3Db");
        EntityManager em = factory.createEntityManager();
        return;
    }

    public static void main(String[] args) throws Exception {
//        basicDemos();
        //associationDemos();
        equalityDemos();
    }




}
