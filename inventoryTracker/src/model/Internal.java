package model;

/**
 * @author Eric Trevorrow
 */

/** This class creates the part of type InHouse.*/
public class Internal extends Part {

    private int machineId;

    /**This method creates an InHouse part object.
     * Inherits the part class to create an InHouse object.*/
    public Internal(int id, String name, Double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);

        this.machineId = machineId;
    }

    /**This method sets the machine ID of a part.
     * Used to set the machine ID of a specific part.
     * @param machineId The machine ID.*/
    public void setMachineId(int machineId) {

        this.machineId = machineId;
    }

    /**This method gets the machine ID of a part.
     * Used to get the machine ID of a specific part.
     * @return Returns the machine ID.*/
    public int getMachineId() {

        return machineId;
    }

}
