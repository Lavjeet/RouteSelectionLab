package com.sp.dto;

import java.util.List;

/**
 * Represents a Path.
 * Has details about the Source, Destination, Weight(distance) and List of Nodes
 * in the Path
 * 
 * @author lavjeetk
 *
 */
public class Path< T,W extends Comparable > implements Comparable< Path< T,W >> {

    /**
     * source
     */
    private T src;

    /**
     * destination
     */
    private T dest;

    /**
     * list of nodes
     */
    private List< T > nodes;

    /**
     * weight - Distance or Hops
     */
    private W weight;

    /**
     * Constructor
     * @param src
     * @param dest
     * @param weight
     */
    public Path( final T src, final T dest, final W weight ) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Constructor
     * @param src
     * @param dest
     * @param distance
     * @param nodes
     */
    public Path( T src, T dest, W distance, List< T > nodes ) {
        this( src, dest, distance );
        this.nodes = nodes;
    }

    /**
     * @return the src
     */
    public T getSrc() {
        return src;
    }

    /**
     * @param src
     *            the src to set
     */
    public void setSrc( final T src ) {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public T getDest() {
        return dest;
    }

    /**
     * @param dest
     *            the dest to set
     */
    public void setDest( final T dest ) {
        this.dest = dest;
    }

    /**
     * @return the nodes
     */
    public List< T > getNodes() {
        return nodes;
    }

    /**
     * @param nodes
     *            the nodes to set
     */
    public void setNodes( final List< T > nodes ) {
        this.nodes = nodes;
    }

    /**
     * @return the distance
     */
    public W getDistance() {
        return weight;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance( final W distance ) {
        this.weight = distance;
    }

    /**
     * Returns String representation.
     */
    public String toString() {
        return "Source node:"+src + "->" + " Destination node:"+dest + " , Will pass through: " + nodes + " , Total distance : " + weight;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dest == null ) ? 0 : dest.hashCode() );
        result = prime * result + ( ( nodes == null ) ? 0 : nodes.hashCode() );
        result = prime * result + ( ( src == null ) ? 0 : src.hashCode() );
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Path<T,W> other = (Path<T,W> ) obj;
        if (dest == null) {
            if (other.dest != null)
                return false;
        } else if (!dest.equals( other.dest ))
            return false;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals( other.nodes ))
            return false;
        if (src == null) {
            if (other.src != null)
                return false;
        } else if (!src.equals( other.src ))
            return false;
        return true;
    }

    @Override
    /**
     * For Ascending order of Paths.
     */
    public int compareTo( final Path< T,W > o ) {
        return this.getDistance().compareTo( o.getDistance() );
    }
}
