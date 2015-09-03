package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sp.dto.Graph;
import com.sp.dto.Path;

/**
 * Computes Shortest Paths for a given set of source nodes.
 * @author lavjeetk
 *
 * @param <T>
 */
public class ShortestPathGenerator< T > {

    /**
     * Graph
     */
    private final Graph< T,Double > graph;

    /**
     * INFINITY distance for Nodes not connected.
     */
    private static final double INFINITY = Double.MAX_VALUE;

    /**
     * Constructor
     * @param graph
     */
    public ShortestPathGenerator( final Graph< T,Double > graph ) {
        this.graph = graph;
    }

    /**
     * Returns list of shortest paths from a set of nodes.
     * @param nodes Set of nodes from whom shortest paths are to be computed.
     * @return List<Path> list of shortest paths
     */
    public List< Path< T,Double >> getShortestPathsForSourceNodes( final Set< T > nodes ) {
        final List< Path< T,Double >> paths = new ArrayList< Path< T,Double > >();

        for ( T node : nodes ) {
            paths.addAll( getShortestPathsFromSingleNode( node ) );
        }
        return paths;
    }

    /**
     * Returns list of shortest paths from a Single Node.
     * @param source Source node
     * @return List<Path> shortest paths from source node.
     */
    public List< Path< T,Double >> getShortestPathsFromSingleNode( final T source ) {
        final Map< T, Map< T, Double >> nodeMap = graph.getNodeMap();
        final int totalVertices = nodeMap.size();
        final Set< T > visited = new HashSet<>();

        final Map< T, Path< T,Double >> pathsFromSource = getInitialPathsFromSource( source, nodeMap.get( source ),
                nodeMap.keySet() );
        visited.add( source );

        while ( visited.size() < totalVertices ) {
            final Path< T,Double > shortestPath = findShortestPath( pathsFromSource, visited );

            if (shortestPath.getDistance() == INFINITY) {
                System.out.println( "Cannot reach nodes" );
                break;
            } else {
                final T currentNode = shortestPath.getDest();
                visited.add( currentNode );

                for ( Map.Entry< T, Double > e : nodeMap.get( currentNode ).entrySet() ) {
                    final T newNodeToBeScanned = e.getKey();
                    //see if the new node is not same as source node - cyclic case
                    //for same source and dest node put distance as 0.0
                    if( !newNodeToBeScanned.equals( source ) ) {
                        final double totalDistanceFromSourceToNewNode = shortestPath.getDistance() + e.getValue();
                        final double currentDistanceFromSourceToNewNode = pathsFromSource.get( newNodeToBeScanned )
                                .getDistance();
    
                        if (totalDistanceFromSourceToNewNode < currentDistanceFromSourceToNewNode) {
                            pathsFromSource.get( newNodeToBeScanned ).setDistance( totalDistanceFromSourceToNewNode );
                        }
                    } else {
                        pathsFromSource.get( newNodeToBeScanned ).setDistance( 0.0 );
                    }
                }
            }
        }
        return new ArrayList<>( pathsFromSource.values() );
    }

    /**
     * Returns shortest path from the given map of Paths
     * @param initialPathsFromSource
     * @param visited
     * @return Path - Shortest Path
     */
    private Path< T,Double > findShortestPath( final Map< T, Path< T,Double >> initialPathsFromSource, final Set< T > visited ) {
        Path< T,Double > shortestPath = new Path< T,Double >( null, null, INFINITY );
        for ( final Path< T,Double > path : initialPathsFromSource.values() ) {
            if (!visited.contains( path.getDest() ) && path.getDistance() < shortestPath.getDistance()) {
                shortestPath = path;
            }
        }
        return shortestPath;
    }

    /**
     * Creates a map of initial paths from a given source node. If there are no
     * edges defined then add a new @Path with INFINITY weight.
     * 
     * @param source Source node 
     * @param edgesList List of edges from source node
     * @param allNodes All nodes in the given graph
     * @return Map of Initial Paths from source node.
     */
    private Map< T, Path< T,Double >> getInitialPathsFromSource( final T source, final Map< T, Double > edgesList,
            final Set< T > allNodes ) {

        final Map< T, Path< T,Double >> initialPathsFromSource = new HashMap< T, Path< T,Double > >();

        for ( final Map.Entry< T, Double > e : edgesList.entrySet() ) {
            final Path< T,Double > p = new Path< T,Double >( source, e.getKey(), e.getValue() );
            initialPathsFromSource.put( e.getKey(), p );
        }

        // add other nodes for whom there are no edges from source
        for ( final T node : allNodes ) {
            if (!initialPathsFromSource.containsKey( node )) {
                final Path< T,Double > p = new Path< T,Double >( source, node, INFINITY );
                initialPathsFromSource.put( node, p );
            }
        }

        return initialPathsFromSource;
    }


    /**
     * Returns a list of shortest paths between specific nodes.
     * The inputs is set of nodes which act as source nodes and well as end nodes.
     * @param nodes The nodes from which shortest paths are to be computed.
     * @return List<Path> list of shortest paths
     */
    public List< Path< T,Double >> getAllShortestPathsBetweenSpecificNodes( final Set< T > nodes ) {
        final List< Path< T,Double >> shortestPathsBetweenSpecificNodes = new ArrayList<>();
        
        final List< Path< T,Double >> shortestPathsFromSpecificSourceNodes = getShortestPathsForSourceNodes( nodes );

        for ( final Path< T,Double > path : shortestPathsFromSpecificSourceNodes ) {
            if (nodes.contains( path.getDest() )) {
                shortestPathsBetweenSpecificNodes.add( path );
            }
        }

        return shortestPathsBetweenSpecificNodes;
    }
}
