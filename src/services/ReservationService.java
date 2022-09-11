package services;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    public static ReservationService instance = new ReservationService();
    private Map<String, IRoom> roomMap = new HashMap<>();
    private Map<String, List<Reservation>> reservationListMap = new HashMap<>();

    private Map<Date, List<String>> bookedRoomsForDateMap = new HashMap<>();

    public void addRoom(IRoom room) {
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return  roomMap.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        String email = customer.getEmail();
        addToReservationList(reservation, email);

        addReservationToDateMap(room, checkInDate, checkOutDate);

        return reservation;
    }

    private void addReservationToDateMap(IRoom room, Date checkInDate, Date checkOutDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        while (cal.getTime().before(checkOutDate)) {
            Date current = cal.getTime();
            String roomNUmber = room.getRoomNumber();
            if (bookedRoomsForDateMap.containsKey(current)){
                bookedRoomsForDateMap.get(current).add(roomNUmber);
            } else {
                ArrayList<String> reservations = new ArrayList<>();
                reservations.add(roomNUmber);
                bookedRoomsForDateMap.put(current, reservations);
            }

            cal.add(Calendar.DATE, 1);
        }
    }

    private void addToReservationList(Reservation reservation, String email) {
        if (reservationListMap.containsKey(email)) {
            reservationListMap.get(email).add(reservation);
        } else {
            ArrayList<Reservation> reservations = new ArrayList<>();
            reservations.add(reservation);
            reservationListMap.put(email, reservations);
        }
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<String> bookedRooms = new HashSet<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        while (cal.getTime().before(checkOutDate)) {
            Date current = cal.getTime();

            if (bookedRoomsForDateMap.containsKey(current)){
                bookedRooms.addAll( bookedRoomsForDateMap.get(current));
            }

            cal.add(Calendar.DATE, 1);
        }

        Set<String> availableRooms = new HashSet<>(roomMap.keySet()); //  all available rooms
        availableRooms.removeAll(bookedRooms);

        return  availableRooms.stream().map((String roomID) -> roomMap.get(roomID)).collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return  reservationListMap.get(customer.getEmail());
    }

    public void printAllReservation() {

        if (reservationListMap.isEmpty()) {
            System.out.println("No reservations in the system");
            return;
        }

        reservationListMap.forEach( (String email, List<Reservation> reservations) -> {
            System.out.println("User: " + email);
            reservations.forEach((Reservation reservation) -> System.out.println(reservation));
        });
    }

    public Collection<IRoom> getAllRooms() {
        return roomMap.values();
    }
}
