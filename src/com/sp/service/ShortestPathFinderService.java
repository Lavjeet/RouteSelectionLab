package com.sp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sp.dto.Node;
import com.sp.dto.Path;

/**
 * Interface for ShortestPathFinderService
 * 
 * @author lavjeetk
 *
 */
public interface ShortestPathFinderService {

    /**
     * Returns all shortest paths originating from given set of nodes.
     * 
     * @param nodes
     *            Set of nodes
     * @return List of Shortest Paths originating from given set of nodes.
     */
    public List< Path< Node, Double >> getAllShortestPathsFromGivenSourceNodes( final Set< Node > nodes );

    /**
     * Returns shortest paths which end with target nodes..
     * 
     * @param targetNodes
     *            Set of target nodes
     * @param allShortestPathsForGivenNodes
     *            List of all shortest paths.
     * @return Map have keys as Node and value as Shortest path to
     *         other Nodes.
     */
    public Map< Node, List< Path< Node, Double >>> getAllShortestPathsToGivenTargetNodes(
            final List< Path< Node, Double >> allShortestPathsForGivenNodes, final Set< Node > targetNodes );

    /**
     * Returns Map of Nodes having Path between Employee Node and
     * 
     * @param allShortestPaths
     *            all shortest paths
     * @param endNode
     *            The end Node
     * @return Map have Node as key and Path to End Node as value.
     */
    public Map< Node, Path< Node, Double >> getShortestPathsToGivenEndNode(
            final List< Path< Node, Double >> allShortestPaths, final Node endNode );
}
