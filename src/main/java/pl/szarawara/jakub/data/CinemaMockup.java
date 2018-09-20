package pl.szarawara.jakub.data;

import pl.szarawara.jakub.SqlTables.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CinemaMockup {

	public CinemaMockup(EntityManagerFactory entityManagerFactory, EntityManager entityManager){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
		data = new Scanner(System.in);
	}

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Scanner data;

public void setupSQL(){

		/*ScreenRoom screenRoomA = new ScreenRoom(10,10);
		ScreenRoom screenRoomB = new ScreenRoom(12,14);
		ScreenRoom screenRoomC = new ScreenRoom(8,6);*/

		Employees employee1 = new Employees("e568532","123haha");
		Employees employee2 = new Employees("e598452","edzia15");
		Employees employee3 = new Employees("e685952","t4d4k");
		Employees employee4 = new Employees("e566852","h3h3szki");

		Discounts code1 = new Discounts("a8ksp34",0.1);
		Discounts code2 = new Discounts("sko58a6",0.1);
		Discounts code3 = new Discounts("586as2w",0.1);
		Discounts code4 = new Discounts("86a4z85",0.15);
		Discounts code5 = new Discounts("jdkas86",0.20);
		Discounts code6 = new Discounts("qw18385",0.25);
		Discounts code7 = new Discounts("186588a",0.50);

		ScreenRoom screenRoomA = new ScreenRoom(2,1);
		ScreenRoom screenRoomB = new ScreenRoom(5,3);
		ScreenRoom screenRoomC = new ScreenRoom(1,1);

		Displays displays1 = new Displays(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,12,0), screenRoomA);
		Displays displays2 = new Displays(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,18,0), screenRoomB);
		Displays displays3 = new Displays(new GregorianCalendar(2017,4,13),new GregorianCalendar(0,0,0,12,0), screenRoomA);
		Displays displays4 = new Displays(new GregorianCalendar(2017,4,14),new GregorianCalendar(0,0,0,12,0), screenRoomB);
		Displays displays5 = new Displays(new GregorianCalendar(2017,4,15),new GregorianCalendar(0,0,0,20,0), screenRoomB);
		Displays displays6 = new Displays(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,14,0), screenRoomC);
		Displays displays7 = new Displays(new GregorianCalendar(2017,4,13),new GregorianCalendar(0,0,0,16,0), screenRoomC);

		setupScreenRoom(displays1);
		setupScreenRoom(displays2);
		setupScreenRoom(displays3);
		setupScreenRoom(displays4);
		setupScreenRoom(displays5);
		setupScreenRoom(displays6);
		setupScreenRoom(displays7);

//		bookScreenRoomRandomly(displays1);
//		bookScreenRoomRandomly(displays2);
//		bookScreenRoomRandomly(displays3);
//		bookScreenRoomRandomly(displays4);
//		bookScreenRoomRandomly(displays5);
//		bookScreenRoomRandomly(displays6);
//		bookScreenRoomRandomly(displays7);

		List<Displays> displaysA = new ArrayList<Displays>();
		List<Displays> displaysB = new ArrayList<Displays>();
		List<Displays> displaysC = new ArrayList<Displays>();

		displaysA.add(displays1);
		displaysA.add(displays2);
		displaysA.add(displays3);
		displaysA.add(displays4);
		displaysA.add(displays5);
		displaysB.add(displays6);
		displaysC.add(displays7);

		Movie movie1 = new Movie("Movie A",123,15, displaysA);
		Movie movie2 = new Movie("Movie B",153,18, displaysB);
		Movie movie3 = new Movie("Movie C",92,0, displaysC);

		Tickets ticket1 = new Tickets("Normalny", 35.00);
		Tickets ticket2 = new Tickets("Ulgowy", 17.50);

		entityManager.getTransaction().begin();


		entityManager.persist(movie1);
		entityManager.persist(movie2);
		entityManager.persist(movie3);
		entityManager.persist(displays1);
		entityManager.persist(displays2);
		entityManager.persist(displays3);
		entityManager.persist(displays4);
		entityManager.persist(displays5);
		entityManager.persist(displays6);
		entityManager.persist(displays7);
		entityManager.persist(screenRoomA);
		entityManager.persist(screenRoomB);
		entityManager.persist(screenRoomC);
		entityManager.persist(ticket1);
		entityManager.persist(ticket2);
		entityManager.persist(code1);
		entityManager.persist(code2);
		entityManager.persist(code3);
		entityManager.persist(code4);
		entityManager.persist(code5);
		entityManager.persist(code6);
		entityManager.persist(code7);
		entityManager.persist(employee1);
		entityManager.persist(employee2);
		entityManager.persist(employee3);
		entityManager.persist(employee4);


		entityManager.getTransaction().commit();

	}

	private void bookScreenRoomRandomly(Displays displays){
		int result;
		for(int i = 0; i< displays.getScreenRoom().getScreenRoomRows()* displays.getScreenRoom().getScreenRoomColumns()*0.5; i++){
			result=(int)(Math.random()* displays.getScreenRoom().getScreenRoomRows()* displays.getScreenRoom().getScreenRoomColumns());
			if(!displays.getSeats().get(result).isOccupied()){
				displays.setFreeSeats(displays.getFreeSeats()-1);
			}
			displays.getSeats().get(result).setOccupied(true);
		}

	}

	private void setupScreenRoom(Displays displays){
		displays.setFreeSeats(displays.getScreenRoom().getScreenRoomColumns()* displays.getScreenRoom().getScreenRoomRows());
		List<Seats> seats = new ArrayList<Seats>();
		for(int i = 0; i< displays.getScreenRoom().getScreenRoomRows(); i++)
			for (int j = 0; j< displays.getScreenRoom().getScreenRoomColumns(); j++){
				Seats seat = new Seats(i,j);
				seats.add(seat);

				entityManager.getTransaction().begin();

				entityManager.persist(seat);

				entityManager.getTransaction().commit();
			}
		 				displays.setSeats(seats);
	}

}
