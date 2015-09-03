package com.sp.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sp.dto.Employee;
import com.sp.dto.Gender;
import com.sp.dto.Graph;
import com.sp.dto.Node;

/**
 * Initializes the Data.
 * 
 * @author lavjeetk
 *
 */
public class DBService {

    /**
     * DATA SET
     * 
     * Nilesh M J11
     * Praskha F F5
     * Nilima F C9
     * Frenny F J1
     * Paul M B6
     * Sachin M E10
     * Harry M F1
     * Sandeep M G8
     * Ashwini F A4
     * Shilpa F C2
     * Shivani F D11
     * rahul M F4
     * swapnil M H8
     * mandar M C6
     * sameer M E3
     * prasad M D5
     * Kiran T B7
     * prafulla M E11
     * ashish M A11
     * rajiv M F4
     */
    public Graph< Node, Double > initializeGraph() {
        final int xBlocks = 10;
        final int yBlocks = 11;
        final Graph< Node, Double > graph = new Graph< Node, Double >();
        int c = 1;
        for ( int i = 1; i <= yBlocks; i++ ) {
            char n = 'A';
            for ( int j = 1; j <= xBlocks; j++, n++ ) {
                Node v = createNode( c, n + "" + i );
                graph.addNode( v );
                c++;
            }
        }

        final Map< Node, Map< Node, Double >> nodeMap = graph.getNodeMap();
        final List< Node > vertices = new ArrayList< Node >( nodeMap.keySet() );
        Collections.sort( vertices );
        for ( int i = 0; i < vertices.size(); i++ ) {
            final Node src = vertices.get( i );
            Node v;

            if (i > 10 && i % 10 != 0) {
                v = vertices.get( i - 11 );
                graph.addEdge( src, v, 1.5 );
            }
            if (i > 9) {
                v = vertices.get( i - 10 );
                graph.addEdge( src, v, 1.0 );
            }
            if (i > 9 && ( i + 1 ) % 10 != 0) {
                v = vertices.get( i - 9 );
                graph.addEdge( src, v, 1.5 );
            }
            if (i % 10 != 0) {
                v = vertices.get( i - 1 );
                graph.addEdge( src, v, 1.0 );
            }
            if (( i + 1 ) % 10 != 0) {
                v = vertices.get( i + 1 );
                graph.addEdge( src, v, 1.0 );
            }
            if (i < 100 && i % 10 != 0) {
                v = vertices.get( i + 9 );
                graph.addEdge( src, v, 1.5 );
            }
            if (i < 100) {
                v = vertices.get( i + 10 );
                graph.addEdge( src, v, 1.0 );
            }
            if (i < 99 && ( i + 1 ) % 10 != 0) {
                v = vertices.get( i + 11 );
                graph.addEdge( src, v, 1.5 );
            }
            // same node added as 0 distance
            graph.addEdge( src, src, 0.0 );
        }

        /*
         * for ( Node v : vertices ) {
         * System.out.print( v + " " );
         * for ( Entry< Node, Double > entry : g.getNodeMap().get( v
         * ).entrySet() ) {
         * System.out.print( entry.getKey() + "-" + entry.getValue() + " " );
         * }
         * System.out.println();
         * }
         */

        return graph;
    }

    public List< Employee > initEmployeesList( final Set< Node > nodes ) {

        final List< Employee > employees = new ArrayList<>();
        employees.add( new Employee( "Nilesh", Gender.MALE, getNodeByName( "J11", nodes ) ) );
        employees.add( new Employee( "Praskha", Gender.FEMALE, getNodeByName( "F5", nodes ) ) );
        employees.add( new Employee( "Nilima", Gender.FEMALE, getNodeByName( "C9", nodes ) ) );
        employees.add( new Employee( "Frenny", Gender.FEMALE, getNodeByName( "J1", nodes ) ) );
        employees.add( new Employee( "Paul", Gender.MALE, getNodeByName( "B6", nodes ) ) );
        employees.add( new Employee( "Sachin", Gender.MALE, getNodeByName( "E10", nodes ) ) );
        employees.add( new Employee( "Harry", Gender.MALE, getNodeByName( "F1", nodes ) ) );
        employees.add( new Employee( "Sandeep", Gender.MALE, getNodeByName( "G8", nodes ) ) );
        employees.add( new Employee( "Ashwini", Gender.FEMALE, getNodeByName( "A4", nodes ) ) );
        employees.add( new Employee( "Shilpa", Gender.FEMALE, getNodeByName( "C2", nodes ) ) );
        employees.add( new Employee( "Shivani", Gender.FEMALE, getNodeByName( "D11", nodes ) ) );
        employees.add( new Employee( "rahul", Gender.MALE, getNodeByName( "F4", nodes ) ) );
        employees.add( new Employee( "swapnil", Gender.MALE, getNodeByName( "H8", nodes ) ) );
        employees.add( new Employee( "mandar", Gender.MALE, getNodeByName( "C6", nodes ) ) );
        employees.add( new Employee( "sameer", Gender.MALE, getNodeByName( "E3", nodes ) ) );
        employees.add( new Employee( "prasad", Gender.MALE, getNodeByName( "D5", nodes ) ) );
        employees.add( new Employee( "Kiran", Gender.TRANSGENDER, getNodeByName( "B7", nodes ) ) );
        employees.add( new Employee( "prafulla", Gender.MALE, getNodeByName( "E11", nodes ) ) );
        employees.add( new Employee( "ashish", Gender.MALE, getNodeByName( "A11", nodes ) ) );
        //employees.add( new Employee( "rajiv", Gender.MALE, getNodeByName( "F4", nodes ) ) );

        return employees;
    }

    private Node createNode( final int number, final String name ) {
        return new Node( number, name );
    }

    public Node getNodeByName( final String name, final Set< Node > nodes ) {
        Node node = null;
        for ( final Node n : nodes ) {
            if (n.getName().equals( name )) {
                node = n;
                break;
            }
        }

        return node;
    }

}
