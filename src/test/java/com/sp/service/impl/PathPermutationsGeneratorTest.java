package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sp.PathPermutationsGenerator;
import com.sp.dto.Employee;
import com.sp.dto.Gender;
import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.dto.Path;
import com.sp.test.config.AbstractBaseMockitoTest;

/**
 * Test class for PathPermutationsGenerator.
 * 
 * @author lavjeetk
 *
 */
public class PathPermutationsGeneratorTest extends AbstractBaseMockitoTest {

    private PathPermutationsGenerator pathPermutationsGenerator;

    private Map< Node, List< Employee >> empNodes;

    private Map< Node, List< Path< Node, Double >>> pathsBetweenEmpNodes;

    private Map< Node, Path< Node, Double >> pathsFromEmpNodesToEndNode;

    private int maxEmployeesInEachVehicle = 4;

    /**
     * Tests findAllPermutations method.
     */
    @Test
    public void testFindAllPermutations() {
        initData();
        pathPermutationsGenerator = new PathPermutationsGenerator( empNodes, pathsBetweenEmpNodes,
                pathsFromEmpNodesToEndNode, maxEmployeesInEachVehicle );
        final List<Path<Node, Double>> allPermutations = pathPermutationsGenerator.findAllPermutations( true, true );
        
        // 4  nodes will have 24 permutations - 4!
        Assert.assertTrue( allPermutations.size() == 24 );
    }

    private void initData() {
        final Node node1 = new Node( 1, "A" );
        final Node node2 = new Node( 2, "B" );
        final Node node3 = new Node( 3, "C" );
        final Node node4 = new Node( 4, "D" );
        final List< Employee > empList1 = new ArrayList<>();
        final List< Employee > empList2 = new ArrayList<>();
        final List< Employee > empList3 = new ArrayList<>();
        final List< Employee > empList4 = new ArrayList<>();
        empList1.add( new Employee( "E-A", Gender.MALE, node1 ) );
        empList2.add( new Employee( "E-B", Gender.MALE, node2 ) );
        empList3.add( new Employee( "E-C", Gender.MALE, node3 ) );
        empList4.add( new Employee( "E-D", Gender.MALE, node4 ) );

        empNodes = new HashMap<>();
        empNodes.put( node1, empList1 );
        empNodes.put( node2, empList2 );
        empNodes.put( node3, empList3 );
        empNodes.put( node4, empList4 );

        pathsBetweenEmpNodes = new HashMap<>();
        final Path< Node, Double > p1 = new Path< Node, Double >( node1, node2, 10.0 );
        final Path< Node, Double > p2 = new Path< Node, Double >( node1, node3, 10.0 );
        final Path< Node, Double > p3 = new Path< Node, Double >( node1, node4, 10.0 );
        final Path< Node, Double > p4 = new Path< Node, Double >( node2, node1, 10.0 );
        final Path< Node, Double > p5 = new Path< Node, Double >( node2, node3, 10.0 );
        final Path< Node, Double > p6 = new Path< Node, Double >( node2, node4, 10.0 );
        final Path< Node, Double > p7 = new Path< Node, Double >( node3, node1, 10.0 );
        final Path< Node, Double > p8 = new Path< Node, Double >( node3, node2, 10.0 );
        final Path< Node, Double > p9 = new Path< Node, Double >( node3, node4, 10.0 );
        final Path< Node, Double > p10 = new Path< Node, Double >( node4, node1, 10.0 );
        final Path< Node, Double > p11 = new Path< Node, Double >( node4, node2, 10.0 );
        final Path< Node, Double > p12 = new Path< Node, Double >( node4, node3, 10.0 );

        final List< Path< Node, Double >> list1 = new ArrayList<>();
        final List< Path< Node, Double >> list2 = new ArrayList<>();
        final List< Path< Node, Double >> list3 = new ArrayList<>();
        final List< Path< Node, Double >> list4 = new ArrayList<>();

        list1.add( p1 );
        list1.add( p2 );
        list1.add( p3 );
        list2.add( p4 );
        list2.add( p5 );
        list2.add( p6 );
        list3.add( p7 );
        list3.add( p8 );
        list3.add( p9 );
        list4.add( p10 );
        list4.add( p11 );
        list4.add( p12 );

        pathsBetweenEmpNodes.put( node1, list1 );
        pathsBetweenEmpNodes.put( node2, list2 );
        pathsBetweenEmpNodes.put( node3, list3 );
        pathsBetweenEmpNodes.put( node4, list4 );

        pathsFromEmpNodesToEndNode = new HashMap<>();
        final Node nodeEnd = new Node( 5, "End" );
        final Path< Node, Double > pe1 = new Path< Node, Double >( node1, nodeEnd, 10.0 );
        final Path< Node, Double > pe2 = new Path< Node, Double >( node2, nodeEnd, 10.0 );
        final Path< Node, Double > pe3 = new Path< Node, Double >( node3, nodeEnd, 10.0 );
        final Path< Node, Double > pe4 = new Path< Node, Double >( node4, nodeEnd, 10.0 );
        pathsFromEmpNodesToEndNode.put( node1, pe1 );
        pathsFromEmpNodesToEndNode.put( node2, pe2 );
        pathsFromEmpNodesToEndNode.put( node3, pe3 );
        pathsFromEmpNodesToEndNode.put( node4, pe4 );

    }
}
