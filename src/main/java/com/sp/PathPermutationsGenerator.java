package com.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.sp.dto.Employee;
import com.sp.dto.Gender;
import com.sp.dto.Node;
import com.sp.dto.Path;

/**
 * Given a list of Paths this class would generate all the possible
 * permutations.
 * 
 * @author lavjeetk
 *
 */
public class PathPermutationsGenerator {

    /**
     * Employee Nodes
     */
    private Map< Node, List< Employee >> empNodes;

    /**
     * Path between Employee nodes
     */
    private Map< Node, List< Path< Node, Double >>> pathsBetweenEmpNodes;

    /**
     * Path from employee nodes to end node
     */
    private Map< Node, Path< Node, Double >> pathsFromEmpNodesToEndNode;

    /**
     * Maximum employees in one vehicle
     */
    private int maxEmployeesInEachVehicle;
    
    /**
     * 
     */
    private int numberOfEmplyeesLeft;

    /**
     * Constructor
     * 
     * @param empNodes
     * @param pathsBetweenEmpNodes
     * @param pathsFromEmpNodesToEndNode
     */
    public PathPermutationsGenerator( final Map< Node, List< Employee >> empNodes,
            final Map< Node, List< Path< Node, Double >>> pathsBetweenEmpNodes,
            final Map< Node, Path< Node, Double >> pathsFromEmpNodesToEndNode, final int maxEmployeesInEachVehicle ) {
        this.empNodes = empNodes;
        this.pathsBetweenEmpNodes = pathsBetweenEmpNodes;
        this.pathsFromEmpNodesToEndNode = pathsFromEmpNodesToEndNode;
        this.maxEmployeesInEachVehicle = maxEmployeesInEachVehicle;
        this.numberOfEmplyeesLeft = empNodes.size();
    }

    /**
     * Returns all the permutations of possible paths.
     * 
     * @param sort
     *            Sort the List of Paths
     * @param canStartWithWomanEmp
     *            Can woman employee travel alone.
     * @return List of all the possible permutations.
     */
    public List< Path< Node, Double >> findAllPermutations( final boolean sort, final boolean canStartWithWomanEmp ) {
        final List< Employee > empList = new ArrayList<>();
        final Stack< Node > nodeStack = new Stack< Node >();
        final Stack< Path< Node, Double >> pathStack = new Stack< Path< Node, Double > >();
        final List< Path< Node, Double >> allPermutations = new ArrayList<>();

        for ( Node node : empNodes.keySet() ) {
            // check if number of employees == maxEmployeesInEachVehicle
            empList.addAll( empNodes.get( node ) );
            if (checkIfEmployeeCountMet( empList )) {
                allPermutations.add( getPermutation( nodeStack, pathStack, empList ));
            } else {
                if( canStartWithWomanEmp ) {
                    generatePermuations( empList, nodeStack, pathStack, allPermutations, node );
                } else {
                    // check if the node has only female employee, then skip this
                    // node
                    final List< Employee > employees = empNodes.get( node );
                    for ( final Employee emp : employees ) {
                        if (!( emp.getGender() == Gender.FEMALE )) {
                            generatePermuations( empList, nodeStack, pathStack, allPermutations, node );
                            break;
                        }
                    }
                }
            }
            empList.removeAll( empNodes.get( node ) );
        }

        if (sort) {
            sortPermutations( allPermutations );
        }

        System.out.println("Total number of permutations taking max "+maxEmployeesInEachVehicle+" employees in one vehicle :"+allPermutations.size());
        return allPermutations;
    }

    /**
     * 
     * @param empList
     * @param nodeStack
     * @param pathStack
     * @param allPermutations
     * @param node
     */
    private void generatePermuations( final List< Employee > empList, final Stack< Node > nodeStack,
            final Stack< Path< Node, Double >> pathStack, final List< Path< Node, Double >> allPermutations, Node node ) {
        nodeStack.push( node );
        generatePermuations( empList, nodeStack, pathStack, node, allPermutations );
        nodeStack.pop();
    }

    /**
     * Sort the permutations.
     */
    private void sortPermutations( final List<Path< Node, Double >> allPermutations ) {
        Collections.sort( allPermutations, new Comparator< Path< Node, Double > >() {
            @Override
            public int compare( Path< Node, Double > o1, Path< Node, Double > o2 ) {
                return new Float( o1.getDistance() ).compareTo( new Float( o2.getDistance() ) );
            }

        } );
    }

    /**
     * Recursively tries to generate all the permutations.
     * 
     * @param empList
     *            List of Employees
     * @param nodeStack
     *            Stack of Nodes
     * @param pathStack
     *            Stack of Path
     * @param node
     *            Node to start with
     */
    private void generatePermuations( final List< Employee > empList, final Stack< Node > nodeStack,
            final Stack< Path< Node, Double >> pathStack, final Node node, final List<Path< Node, Double >> allPermutations ) {
        final List< Path< Node, Double >> paths = pathsBetweenEmpNodes.get( node );
        for ( Path< Node, Double > path : paths ) {
            final Node newNode = path.getDest();
            if (!nodeStack.contains( newNode )) {
                if (( empNodes.get( newNode ).size() + empList.size() ) <= maxEmployeesInEachVehicle) {
                    nodeStack.push( newNode );
                    pathStack.push( path );
                    empList.addAll( empNodes.get( newNode ) );
                    if (checkIfEmployeeCountMet( empList )) {
                        allPermutations.add( getPermutation( nodeStack, pathStack, empList ));
                    } else {
                        generatePermuations( empList, nodeStack, pathStack, newNode, allPermutations );
                    }
                    empList.removeAll( empNodes.get( newNode ) );
                    nodeStack.pop();
                    pathStack.pop();
                }
            }
        }
    }
    
    private boolean checkIfEmployeeCountMet( final List< Employee > empList ) {
        if (empList.size() == maxEmployeesInEachVehicle || empList.size() == numberOfEmplyeesLeft) {
            numberOfEmplyeesLeft -= maxEmployeesInEachVehicle;
            return true;
        } else {
            return false;
        }
    }

    private Path< Node, Double > getPermutation( final Stack< Node > nodeStack, final Stack< Path< Node, Double >> pathStack,
            final List< Employee > empList ) {
        double totalDistance = 0;
        for ( int i = 0; i < pathStack.size(); i++ ) {
            totalDistance += pathStack.get( i ).getDistance();
        }
            final Path<Node, Double> path = pathsFromEmpNodesToEndNode.get( nodeStack.peek() );
            totalDistance += path.getDistance();
            return new Path< Node, Double >( nodeStack.get( 0 ), path.getDest(), totalDistance,
                    new ArrayList< Node >( nodeStack ) );

    }

}
