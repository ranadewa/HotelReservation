package model;

public class Room implements IRoom{
    private String roomNumber;
    protected Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number: " + getRoomNumber() + ",  Price: " + getRoomPrice() + ", type: " + getRoomType().name();
    }

    @Override
    public int hashCode() {
        return this.getRoomPrice().hashCode() +
                this.getRoomNumber().hashCode() +
                this.getRoomType().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (!(obj instanceof Room))
            return false;

        Room other = (Room) obj;

        return other.getRoomType() == this.getRoomType() &&
                other.getRoomNumber() == this.getRoomNumber() &&
                other.getRoomPrice() == this.getRoomPrice();
    }
}
