package co.jtoehen.model;

/**
 * Created by jtoehen on 19/2/2017.
 */
public class Atm{

    private Address address;

    private int distance;

    private String type;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
