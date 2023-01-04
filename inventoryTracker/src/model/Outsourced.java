package model;

/**
 * @author Eric Trevorrow
 */

/** This class creates the part of type Outsourced.*/
public class Outsourced extends Part {

    private String companyName;

    /**This method creates an Outsourced part object.
     * Inherits the part class to create an Outsourced object.*/
    public Outsourced(int id, String name, Double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        this.companyName = companyName;
    }

    /**This method sets the company name of a part.
     * Used to set the name of a company that makes a specific part.
     * @param companyName The name of the company.*/
    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    /**This method gets the company name of a part.
     * Used to get the name of a company that makes a specific part.
     * @return Returns the name of the company.*/
    public String getCompanyName() {

        return companyName;
    }
}