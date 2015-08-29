package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.dto.Path;
import com.sp.service.ShortestPathFinderService;

/**
 * Service to generate shortest paths
 * 
 * @author lavjeetk
 *
 */
public class ShortestPathFinderServiceImpl implements ShortestPathFinderService {

    /**
     * Shortest Path Generator
     */
    private ShortestPathGenerator< Node > spg;

    /**
     * Constructor
     * 
     * @param spg
     */
    public ShortestPathFinderServiceImpl( final Graph< Node, Double > graph ) {
        this.spg = new ShortestPathGenerator<>( graph );
    }

    /**
     * Returns all shortest paths originating from given set of nodes.
     * 
     * @param nodes
     *            Set of nodes
     * @return List of Shortest Paths originating from given set of nodes.
     */
    public List< Path< Node, Double >> getAllShortestPathsFromGivenSourceNodes( final Set< Node > nodes ) {
        final List< Path< Node, Double >> shortestPathsForGivenNodes = spg.getShortestPathsForSourceNodes( nodes );
        System.out.println( "Total Shortest Paths Between Employee Nodes : " + shortestPathsForGivenNodes.size() );
        return shortestPathsForGivenNodes;
    }

    /**
     * Returns shortest paths which end with target nodes..
     * 
     * @param targetNodes
     *            Set of target nodes
     * @param shortestPaths
     *            List of all shortest paths.
     * @return Map have keys as Node and value as Shortest path to
     *         other Nodes.
     */
    public Map< Node, List< Path< Node, Double >>> getAllShortestPathsToGivenTargetNodes(
            final List< Path< Node, Double >> shortestPaths, final Set< Node > targetNodes ) {

        final Map< Node, List< Path< Node, Double >>> pathBetweenNodes = new HashMap<>();
        for ( Path< Node, Double > p : shortestPaths ) {
            if (targetNodes.contains( p.getDest() )) {
                List< Path< Node, Double >> paths = pathBetweenNodes.get( p.getSrc() );
                if (paths == null) {
                    paths = new ArrayList<>();
                    pathBetweenNodes.put( p.getSrc(), paths );
                }
                paths.add( p );
            }
        }
        return pathBetweenNodes;
    }

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
            final List< Path< Node, Double >> allShortestPaths, final Node endNode ) {
        final Map< Node, Path< Node, Double >> pathsFromEmpNodesToEndNode = new HashMap< Node, Path< Node, Double > >();
        for ( Path< Node, Double > p : allShortestPaths ) {
            if (p.getDest().equals( endNode )) {
                pathsFromEmpNodesToEndNode.put( p.getSrc(), p );
            }
        }
        return pathsFromEmpNodesToEndNode;
    }

}
