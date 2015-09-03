package com.sp.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Graph.
 * @author lavjeetk
 *
 * @param <T>
 */
public class Graph< T,W extends Comparable > {

    /**
     * NodeMap
     */
    private Map< T, Map< T, W >> nodeMap;

    /**
     * Constructor
     */
    public Graph() {
        nodeMap = new HashMap< T, Map< T, W > >();
    }

    /**
     * Adds the node to the Graph
     * @param node Node to be added.
     */
    public void addNode( final T node ) {
        nodeMap.put( node, new HashMap< T, W >() );
    }

    /**
     * Adds the edge from source node to dest node
     * @param src Source Node
     * @param dest Destination Node
     * @param dist Distance 
     */
    public void addEdge( final T src, final T dest, final W dist ) {
        if (nodeMap.containsKey( src )) {
            final Map< T, W > map = nodeMap.get( src );
            map.put( dest, dist );
        }
    }

    /**
     * @return the nodeMap
     */
    public Map< T, Map< T, W >> getNodeMap() {
        return nodeMap;
    }

    /**
     * @param nodeMap
     *            the nodeMap to set
     */
    public void setNodeMap( final Map< T, Map< T, W >> nodeMap ) {
        this.nodeMap = nodeMap;
    }
}
