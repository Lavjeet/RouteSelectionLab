package com.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sp.db.DBService;
import com.sp.dto.Employee;
import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.dto.Path;
import com.sp.service.EmployeeService;
import com.sp.service.ShortestPathFinderService;
import com.sp.service.impl.EmployeeServiceImpl;
import com.sp.service.impl.ShortestPathFinderServiceImpl;

/**
 * The Driver class.
 * 
 * @author lavjeetk
 *
 */
public class Driver {

    /**
     * Main method
     * 
     * @param args
     */
    public static void main( String[] args ) {

        final Node endNode = new Node( 1, "A1" );
        final int maxEmployeesInEachVehicle = 4;

        final DBService dbService = new DBService();
        final Graph< Node, Double > graph = dbService.initializeGraph();

        final EmployeeService empService = new EmployeeServiceImpl( dbService );
        final ShortestPathFinderService spService = new ShortestPathFinderServiceImpl( graph );

        final Map< Node, List< Employee >> nodesWithEmployees = empService.getNodesWithEmployees( graph );

        final List< Path< Node, Double >> allShortestPathsFromEmpNodes = spService
                .getAllShortestPathsFromGivenSourceNodes( nodesWithEmployees.keySet() );
        final Map< Node, List< Path< Node, Double >>> shortestPathsBetweenEmpNodes = spService
                .getAllShortestPathsToGivenTargetNodes( allShortestPathsFromEmpNodes, nodesWithEmployees.keySet() );
        final Map< Node, Path< Node, Double >> pathsFromEmpNodesToEndNode = spService
                .getShortestPathsToGivenEndNode( allShortestPathsFromEmpNodes, endNode );

        final PathPermutationsGenerator ppg = new PathPermutationsGenerator( nodesWithEmployees,
                shortestPathsBetweenEmpNodes, pathsFromEmpNodesToEndNode, maxEmployeesInEachVehicle );
        final List< Path< Node, Double >> allPermutations = ppg.findAllPermutations( true, false );

        final List< Path< Node, Double >> shortestPaths = getShortestPathsFromAllPermutations( allPermutations );
        //printShortestPaths( shortestPaths );

    }

    /**
     * Given a list of permutations get the set of Shortest Possible Paths
     * covering all the Employees.
     * 
     * @param allPermutations
     *            List of all the permutations.
     * @return List of Shortest Paths covering all the employees.
     */
    private static List< Path< Node, Double >> getShortestPathsFromAllPermutations(
            final List< Path< Node, Double >> allPermutations ) {
        Set< Node > employeesNodes = new HashSet<>();
        List< Path< Node, Double >> shortestRoutes = new ArrayList<>();
        final Map< String, List< Path<Node, Double> >> shortestRoutesCombination = new HashMap< String, List<Path<Node,Double>> >();
        
        for ( int i = 0; i <= allPermutations.size(); i++ ) {
            employeesNodes = new HashSet<>();
            shortestRoutes = new ArrayList<>();
            
            for ( final Path< Node, Double > route : allPermutations ) {
                if (Collections.disjoint( employeesNodes, route.getNodes() )) {
                    employeesNodes.addAll( route.getNodes() );
                    shortestRoutes.add( route );
                }
            }
            //shortestRoutesCombination.put(""+i+1, shortestRoutes);
            System.out.print(i+1+ " : "+allPermutations.size());
            System.out.println(shortestRoutes);
            if( i == allPermutations.size() ) {
                System.out.println("reached");
            }
            Collections.rotate( allPermutations, 1 );
        }
        return shortestRoutes;
    }

    /**
     * Prints the shortest paths
     * 
     * @param shortestPaths
     */
    private static void printShortestPaths( List< Path< Node, Double >> shortestPaths ) {
        System.out.println("************ Shortest Paths *****************");
        for ( Path< Node, Double > route : shortestPaths ) {
            System.out.println( route );
        }
    }

}
