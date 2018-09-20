package pl.szarawara.jakub.main;

import pl.szarawara.jakub.data.CinemaMockup;
import pl.szarawara.jakub.options.Customer;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main{

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static Scanner data;

    private final static String GREETING = "Witamy w Kinie Olkusz!\nJeżeli jesteś pracownikiem wybierz 1, jeżeli klientem wybierz 2";
    private final static String DATABASE = "myDatabase";
    private final static String WRONG_NUMBER = "Wybrano zły numer, podaj jeszcze raz";

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE);
        entityManager = entityManagerFactory.createEntityManager();

        Customer customer = new Customer(entityManagerFactory,entityManager);
        CinemaMockup cinemaMockup = new CinemaMockup(entityManagerFactory,entityManager);
//        Employee pracownik = new Employee(entityManagerFactory,entityManager);

        cinemaMockup.setupSQL();

        System.out.println(GREETING);

        data = new Scanner(System.in);

        boolean correctNumber=false;
        int result = 0;

        while(!correctNumber){
            try{
                result=Integer.parseInt(data.next());
                correctNumber=true;
            }
            catch(NumberFormatException e){
                System.out.println(WRONG_NUMBER);
            }

        }

        switch(result){
//            case 1: pracownik.pracownik(); break;
            case 2: customer.orderTickets();break;
        }

        entityManager.close();
        entityManagerFactory.close();

    }

}
