package pl.szarawara.jakub.options;

import pl.szarawara.jakub.SqlTables.Discounts;
import pl.szarawara.jakub.SqlTables.Displays;
import pl.szarawara.jakub.SqlTables.Movie;
import pl.szarawara.jakub.SqlTables.Tickets;
import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;
import pl.szarawara.jakub.noSqlObjects.BoughtTickets;
import pl.szarawara.jakub.options.usingMethods.UsingMethodsCustomer;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Customer extends UsingMethodsCustomer {

    public Customer(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
        data = new Scanner(System.in);
    }

    public void orderTickets() {

        System.out.println(MOVIE_QUESTION);
        List<SqlTableImpl> queryMovieOptions = getListOfResultsFromQuery(Movie.class, MOVIE_QUERY);
        int chosenMovieOption = chooseOptionFromQueryWithSpecification(queryMovieOptions);

        System.out.println(DAY_QUESTION);
        List<SqlTableImpl> queryDayOptions = getListOfResultsFromQuery(DAY_QUERY, queryMovieOptions.get(chosenMovieOption - 1).getSpecification());
        int chosenDayOption = chooseOptionFromQueryWithoutSpecification(queryDayOptions);
        long movieId = ((Movie) queryMovieOptions.get(chosenMovieOption - 1)).getId();

        System.out.println(HOUR_QUESTION);
        List<String> chosenHourOptions = getListOfResultsFromQuery(HOUR_QUERY, movieId, queryDayOptions.get(chosenDayOption - 1));
        List<Integer> freeSeats = getListOfResultsFromQuery(FREE_SEATS_QUERY, movieId, queryDayOptions.get(chosenDayOption - 1));

        int chosenHourOption = chooseOptionFromQueryWithoutSpecificationWithTwoParameters(queryMovieOptions, chosenMovieOption, queryDayOptions, chosenDayOption, chosenHourOptions);
        System.out.println(TICKET_KIND_QUESTION);
        List<SqlTableImpl> queryTicketsOptions = getListOfResultsFromQuery(Tickets.class, TICKET_QUERY);
        BoughtTickets boughtTicketsInformation = getTicketsTotalPrice(freeSeats, chosenHourOption, queryTicketsOptions);
        double ticketsTotalPrice = boughtTicketsInformation.getTotalPrice();
        int totalBoughtTicketsNumber = boughtTicketsInformation.getTotalBoughtTicketsNumber();

        System.out.println(DISCOUNTS_QUESTION);
        boolean discountCodes = getResultOfQuestion();
        if (discountCodes) {
            List<SqlTableImpl> discounts = getListOfResultsFromQuery(Discounts.class, DISCOUNTS_QUERY);
            System.out.println("Podaj kod");
            ticketsTotalPrice = getTicketTotalPriceWithDiscounts(ticketsTotalPrice, discounts);
        }

        System.out.println("Wybierz miejsce:\nX - zarezerwowane\nO - wolne");
        List<SqlTableImpl> screenRoom = getListOfResultsFromQuery(SCREEN_ROOM_PLACES_QUERY, movieId, queryDayOptions.get(chosenDayOption - 1), chosenHourOptions.get(chosenHourOption - 1));
        showScreenRoomSeats(((Displays) screenRoom.get(0)).getScreenRoom().getScreenRoomColumns(), ((Displays) screenRoom.get(0)).getScreenRoom().getScreenRoomRows(), (Displays) screenRoom.get(0));
        
        setSeats(totalBoughtTicketsNumber, screenRoom);

        System.out.println(FINISH_BOOKING_MESSAGE);
        System.out.println(TOTAL_PRICE_MESSAGE + ticketsTotalPrice);

    }
}
