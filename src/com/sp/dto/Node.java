package com.sp.dto;

/**
 * Represents a Node.
 * Has details like Node number and Node name.
 * 
 * @author lavjeetk
 *
 */
public class Node implements Comparable< Node >{

    /**
     * number
     */
    private int number;
    
    /**
     * Name
     */
    private String name;

    /**
     * Constructor
     * @param number
     * @param name
     */
    public Node( final int number, final String name ) {
        this.number = number;
        this.name = name;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber( final int number ) {
        this.number = number;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( final String name ) {
        this.name = name;
    }
    
    @Override
    /**
     * Returns String representation.
     */
    public String toString() {
        return name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + number;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( final Object obj ) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Node other = ( Node ) obj;
        if (number != other.number)
            return false;
        return true;
    }
    
    /**
     * For Ascending order of Nodes.
     */
    @Override
    public int compareTo( final Node o ) {
        return this.number - o.number;
    }
    
}
