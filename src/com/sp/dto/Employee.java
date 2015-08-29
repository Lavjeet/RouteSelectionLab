package com.sp.dto;

/**
 * Represents an Employee. Has details like name, gender and @Node where he
 * resides.
 * 
 * @author lavjeetk
 *
 */
public class Employee {

    /**
     * Name
     */
    private String name;

    /**
     * Gender
     */
    private Gender gender;

    /**
     * Node
     */
    private Node node;

    /**
     * Constructor
     * @param name
     * @param gender
     * @param node
     */
    public Employee( final String name, final Gender gender, final Node node ) {
        this.name = name;
        this.gender = gender;
        this.node = node;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName( final String name ) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender( final Gender gender ) {
        this.gender = gender;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node
     *            the node to set
     */
    public void setNode( final Node node ) {
        this.node = node;
    }

}
