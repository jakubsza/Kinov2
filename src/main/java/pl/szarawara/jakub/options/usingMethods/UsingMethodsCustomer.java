package pl.szarawara.jakub.options.usingMethods;

import pl.szarawara.jakub.SqlTables.Discounts;
import pl.szarawara.jakub.SqlTables.Displays;
import pl.szarawara.jakub.SqlTables.Tickets;
import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;
import pl.szarawara.jakub.noSqlObjects.BoughtTickets;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public abstract class UsingMethodsCustomer extends UsingMethodsCustomerAndEmployee {

    private final String WRONG_NUMBER_EXPRESSION = "Wybrano zły numer, podaj jeszcze raz";
    protected final String MOVIE_QUESTION = "Na jaki film chcesz się wybrać?";
    protected final String MOVIE_QUERY = "select f from Movie f";
    protected final String DAY_QUESTION = "Wybierz dzień, w którym chcesz obejrzeć film";
    protected final String DAY_QUERY = "select distinct day from Displays w where Film_ID =(select f from Movie f where title = ?1)";
    protected final String HOUR_QUESTION = "Wybierz godzinę";
    protected final String HOUR_QUERY = "select hour from Displays w where Film_ID = ?1 and day =?2";
    protected final String TICKET_KIND_QUESTION = "Wybierz rodzaj biletu";
    protected final String FREE_SEATS_QUERY = "select freeSeats from Displays w where Film_ID = ?1 and day =?2";
    private final String NO_FREE_SEATS_AVAILABLE = "Przepraszamy, ale sala jest już pełna - nie można dodać więcej biletów";
    protected final String TICKET_QUERY = "select b from Tickets b";
    protected final String DISCOUNTS_QUESTION = "Czy posiadasz jakies kody promocyjne?\n0 - tak\n1 - nie";
    protected final String DISCOUNTS_QUERY = "select d from Discounts d";
    protected final String WRONG_DISCOUNT_CODE = "Podany kod jest niepoprawny. Podaj inny kod lub nacisnij 'X', gdy juz nie chcesz podawać innych kodów.";
    protected final String SCREEN_ROOM_PLACES_QUERY = "select w from Displays w where Film_ID = ?1 and day=?2 and hour=?3";
    protected final String FINISH_BOOKING_MESSAGE = "Gratulację! Udało się zarezerwować bilety.";
    protected final String TOTAL_PRICE_MESSAGE = "Kwota do zapłaty: ";

    protected List<SqlTableImpl> getListOfResultsFromQuery(Class classObject, String queryString) {
        TypedQuery<SqlTableImpl> query = entityManager.createQuery(queryString, classObject);
        return query.getResultList();
    }

    protected <T> List getListOfResultsFromQuery(String queryString, T parametr1) {
        Query query = entityManager.createQuery(queryString);
        query.setParameter(1, parametr1);
        return query.getResultList();
    }

    protected <T> List getListOfResultsFromQuery(String queryString, T parametr1, T parametr2) {
        Query query = entityManager.createQuery(queryString);
        query.setParameter(1, parametr1);
        query.setParameter(2, parametr2);
        return query.getResultList();
    }

    protected <T> List getListOfResultsFromQuery(String queryString, T parametr1, T parametr2, T parametr3) {
        Query query = entityManager.createQuery(queryString);
        query.setParameter(1, parametr1);
        query.setParameter(2, parametr2);
        query.setParameter(3, parametr3);
        return query.getResultList();
    }

    protected int chooseOptionFromQueryWithSpecification(List<SqlTableImpl> resultsQueryList) {
        int questionResult = 0;

        for (SqlTableImpl sqlTableResult : resultsQueryList)
            System.out.println(++questionResult + " - " + sqlTableResult.getSpecification());

        return queryQuestionary("Wybrano: ", resultsQueryList);
    }

    protected int chooseOptionFromQueryWithoutSpecification(List<SqlTableImpl> resultsQueryList) {
        int questionResult = 0;

        for (; questionResult < resultsQueryList.size(); )
            System.out.println(++questionResult + " - " + resultsQueryList.get(questionResult - 1));

        return queryQuestionary("Wybrano: ", resultsQueryList);
    }

    protected int chooseOptionFromQueryWithoutSpecificationWithTwoParameters(List<SqlTableImpl> queryMovieOptions, int chosenMovieOption, List<SqlTableImpl> queryDayOptions, int chosenDayOption, List<String> chosenHourOptions) {
        int chosenHourOption = 0;

        for (; chosenHourOption < chosenHourOptions.size(); )
            System.out.println(++chosenHourOption + " - " + chosenHourOptions.get(chosenHourOption - 1));

        return queryQuestionaryWithTwoOptions(chosenHourOption, queryMovieOptions, chosenMovieOption, queryDayOptions, chosenDayOption, chosenHourOptions);
    }

    private int queryQuestionary(String textPrefix, List<SqlTableImpl> resultsQueryList) {
        int questionResult = 0;
        boolean chosenNumberCorrectness = false;

        while (!chosenNumberCorrectness) {
            try {
                questionResult = Integer.parseInt(data.next());
                try {
                    System.out.println(textPrefix + resultsQueryList.get(questionResult - 1).getSpecification());
                } catch (ClassCastException e) {
                    System.out.println(textPrefix + resultsQueryList.get(questionResult - 1));
                }
                chosenNumberCorrectness = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(WRONG_NUMBER_EXPRESSION);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_NUMBER_EXPRESSION);
            }
        }
        return questionResult;
    }

    private int queryQuestionaryWithTwoOptions(int chosenHourOption, List<SqlTableImpl> queryMovieOptions, int chosenMovieOption, List<SqlTableImpl> queryDayOptions, int chosenDayOption, List<String> chosenHourOptions) {
        boolean chosenNumberCorrectness = false;

        while (!chosenNumberCorrectness) {
            try {
                chosenHourOption = Integer.parseInt(data.next());
                System.out.println("Wybrano film " + queryMovieOptions.get(chosenMovieOption - 1).getSpecification() + ", który będzie grany dnia "
                        + queryDayOptions.get(chosenDayOption - 1) + " o godzinie " + chosenHourOptions.get(chosenHourOption - 1) + ".");
                chosenNumberCorrectness = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(WRONG_NUMBER_EXPRESSION);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_NUMBER_EXPRESSION);
            }
        }
        return chosenHourOption;
    }

    private boolean isPossibleToAddNewTicket(List<String> orderedTickets, List<Integer> freeSeats, int chosenHourOption) {
        boolean isClientWantsToAddMoreTickets = false;
        if (orderedTickets.size() == freeSeats.get(chosenHourOption - 1)) {
            System.out.println(NO_FREE_SEATS_AVAILABLE);
        } else {

            System.out.println("0 - dodaj kolejny bilet");
            System.out.println("1 - przejdz dalej");

            isClientWantsToAddMoreTickets = getResultOfQuestion();
        }
        return isClientWantsToAddMoreTickets;
    }

    protected boolean getResultOfQuestion() {
        boolean isChosenNumberCorrect = false;
        boolean isClientWantsToAddMoreTickets = false;

        while (!isChosenNumberCorrect) {
            try {
                int chosenTicketNumber = Integer.parseInt(data.next());
                if (chosenTicketNumber == 1) {
                    isClientWantsToAddMoreTickets = false;
                    isChosenNumberCorrect = true;
                } else if (chosenTicketNumber == 0) {
                    isClientWantsToAddMoreTickets = true;
                    isChosenNumberCorrect = true;
                }

            } catch (NumberFormatException e) {
                System.out.println(WRONG_NUMBER_EXPRESSION);
            }
        }
        return isClientWantsToAddMoreTickets;
    }

    protected BoughtTickets getTicketsTotalPrice(List<Integer> freeSeats, int chosenHourOption, List<SqlTableImpl> queryTicketsOptions) {
        BoughtTickets boughtTicketsInformation = new BoughtTickets();
        List<String> orderedTickets = new ArrayList<String>();
        double ticketsTotalPrice = 0.0;
        int chosenOption;
        boolean addingTicketPossibility = true;

        while (addingTicketPossibility && orderedTickets.size() < freeSeats.get(chosenHourOption - 1)) {

            for (int i = 0; i < queryTicketsOptions.size(); ) {
                System.out.println(++i + " - " + queryTicketsOptions.get(i - 1).getSpecification());
            }
            chosenOption = queryQuestionary("Dodano bilet ", queryTicketsOptions);

            orderedTickets.add(queryTicketsOptions.get(chosenOption - 1).getSpecification());
            ticketsTotalPrice += ((Tickets) queryTicketsOptions.get(chosenOption - 1)).getTicketPrice();

            addingTicketPossibility = isPossibleToAddNewTicket(orderedTickets, freeSeats, chosenHourOption);
        }
        boughtTicketsInformation.setTotalBoughtTicketsNumber(orderedTickets.size());
        boughtTicketsInformation.setTotalPrice(ticketsTotalPrice);
        return boughtTicketsInformation;
    }

    protected double getTicketTotalPriceWithDiscounts(double ticketsTotalPrice, List<SqlTableImpl> discounts) {
        boolean stopWittingDiscounts = false;
        while (!stopWittingDiscounts) {

            String discountCode = data.next();

            if (discountCode.toUpperCase().equals("X")) {
                stopWittingDiscounts = true;
            } else {

                for (SqlTableImpl sqlTable : discounts)
                    if (sqlTable.getSpecification().equals(discountCode)) {
                        ticketsTotalPrice = ticketsTotalPrice * (1 - ((Discounts) sqlTable).getDiscount());
                        stopWittingDiscounts = true;
                    }
                if (!stopWittingDiscounts) {
                    System.out.println(WRONG_DISCOUNT_CODE);
                }
            }
        }
        return ticketsTotalPrice;
    }

    protected void showScreenRoomSeats(int columns, int rows, Displays seats) {

        for (int i = 0; i < columns; i++)
            System.out.print("--");
        System.out.print("\n");

        for (int i = 0; i < rows * columns; i++) {
            if (seats.getSeats().get(i).isOccupied()) {
                System.out.print("X ");
            } else {
                System.out.print("O ");
            }

            if ((i + 1) % columns == 0)
                System.out.print(" " + (i / columns + 1) + "\n");
        }

        for (int j = 0; j < columns; j++)
            System.out.print((char) (j + 65) + " ");
        System.out.println();
    }

    protected void setSeats(int totalBoughtTicketsNumber, List<SqlTableImpl> screenRoom){
        int column = 0;
        int row = 0;

        boolean isSeatCanBeBook = false;
        boolean isRowCorrect = false;
        boolean seatsAreBooked = false;

        for (int i = 0; i < totalBoughtTicketsNumber; i++) {

            System.out.println("Podaj rząd dla biletu nr " + (i + 1) + " : ");

            while (!isSeatCanBeBook) {
                while (!isRowCorrect) {
                    try {
                        row = Integer.parseInt(data.next()) - 1;
                        isRowCorrect = true;
                    } catch (NumberFormatException e) {
                        System.out.println(WRONG_NUMBER_EXPRESSION);
                    }
                }

                System.out.println("Podaj kolumnę dla biletu nr " + (i + 1) + " : ");
                column = ((int) data.next().toUpperCase().charAt(0)) - 65;        //A-0 etc


                try {
                    seatsAreBooked = ((Displays)screenRoom.get(0)).getSeats().get(row + column).isOccupied();
                    isSeatCanBeBook = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(WRONG_NUMBER_EXPRESSION);
                    isRowCorrect = false;
                }
            }


            while (seatsAreBooked) {
                System.out.println("Wybrane miejsce jest już zajęte");
                System.out.println("Podaj inny rząd dla biletu nr " + (i + 1) + " : ");
                row = data.nextInt() - 1;
                System.out.println("Podaj inny kolumnę dla biletu nr " + (i + 1) + " : ");
                column = ((int) data.next().charAt(0)) - 65;
            }
            ((Displays)screenRoom.get(0)).getSeats().get(row + column).setOccupied(true);

            entityManager.refresh(screenRoom.get(0));//.getMiejsca().get(row+column));
        }
    }
}
