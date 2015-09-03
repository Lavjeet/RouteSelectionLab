package com.sp.service.impl;

import java.util.ArrayList;
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

import com.sp.dto.Node;
import com.sp.dto.Path;
import com.sp.test.config.AbstractBaseMockitoTest;

/**
 * Test class for ShortestPathFinderServiceImpl
 * 
 * @author lavjeetk
 *
 */
// @RunWith( MockitoJUnitRunner.class )
public class ShortestPathFinderServiceImplTest extends AbstractBaseMockitoTest {

    @InjectMocks
    ShortestPathFinderServiceImpl shortestPathFinderServiceImpl;

    @Mock
    private ShortestPathGenerator< Node > spg;

    @Test( dataProvider = "sourceNodes" )
    public void testGetAllShortestPathsFromGivenSourceNodes( Set< Node > nodes ) {
        Mockito.when( spg.getShortestPathsForSourceNodes( Mockito.anySet() ) ).thenReturn( new ArrayList<>() );
        final List< Path< Node, Double >> paths = shortestPathFinderServiceImpl
                .getAllShortestPathsFromGivenSourceNodes( nodes );
        Assert.assertNotNull( paths );
    }

    @Test( dataProvider = "shortestPathsAndTargetNodes" )
    public void testGetAllShortestPathsToGivenTargetNodes( final List< Path< Node, Double >> shortestPaths,
            final Set< Node > targetNodes ) {

        final Map< Node, List< Path< Node, Double >>> mapOfShortestPathsToGivenTargetNodes = shortestPathFinderServiceImpl
                .getAllShortestPathsToGivenTargetNodes( shortestPaths, targetNodes );
        Assert.assertNotNull( mapOfShortestPathsToGivenTargetNodes );
        Assert.assertTrue( mapOfShortestPathsToGivenTargetNodes.size() == 2 );

        for ( List< Path< Node, Double >> paths : mapOfShortestPathsToGivenTargetNodes.values() ) {
            for ( Path< Node, Double > path : paths ) {
                Assert.assertTrue( targetNodes.contains( path.getDest() ) );
            }
        }
    }

    @Test( dataProvider = "shortestPathsAndSingleEndNode" )
    public void getShortestPathsToGivenEndNode( final List< Path< Node, Double >> allShortestPaths, final Node endNode ) {
        Map< Node, Path< Node, Double >> mapOfShortestPathsToGivenTargetNode = shortestPathFinderServiceImpl
                .getShortestPathsToGivenEndNode( allShortestPaths, endNode );

        Assert.assertNotNull( mapOfShortestPathsToGivenTargetNode );
        Assert.assertTrue( mapOfShortestPathsToGivenTargetNode.size() == 3 );

        for ( Path< Node, Double > path : mapOfShortestPathsToGivenTargetNode.values() ) {
            Assert.assertTrue( endNode.equals( path.getDest() ) );
        }

    }

    /**
     * DataProvider for source nodes
     * 
     * @return
     */
    @DataProvider( name = "sourceNodes" )
    private static Object[][] sourceNodes() {
        final Node node1 = new Node( 1, "A1" );
        final Node node2 = new Node( 2, "A2" );
        final Set< Node > nodes = new HashSet<>();
        nodes.add( node1 );
        nodes.add( node2 );
        return new Object[][] { { nodes } };
    }

    /**
     * DataProvider for Shortest Paths and Set of Target Nodes
     * 
     * @return
     */
    @DataProvider( name = "shortestPathsAndTargetNodes" )
    private static Object[][] shortestPathsAndTargetNodes() {

        final Node node1 = new Node( 1, "A1" );
        final Node node2 = new Node( 2, "A2" );
        final Node node3 = new Node( 3, "A3" );
        final Node node4 = new Node( 4, "A4" );
        final Node node5 = new Node( 5, "A5" );
        final Node node6 = new Node( 6, "A6" );

        Path< Node, Double > p1 = new Path< Node, Double >( node1, node2, 1.0 );
        Path< Node, Double > p2 = new Path< Node, Double >( node1, node3, 1.0 );
        Path< Node, Double > p3 = new Path< Node, Double >( node2, node5, 1.0 );
        Path< Node, Double > p4 = new Path< Node, Double >( node4, node6, 1.0 );
        final List< Path< Node, Double >> shortestPaths = new ArrayList<>();
        shortestPaths.add( p1 );
        shortestPaths.add( p2 );
        shortestPaths.add( p3 );
        shortestPaths.add( p4 );

        final Set< Node > targetNodes = new HashSet<>();
        targetNodes.add( node5 );
        targetNodes.add( node6 );

        return new Object[][] { { shortestPaths, targetNodes } };
    }

    /**
     * DataProvider for Shortest Paths and Single End Node
     * 
     * @return
     */
    @DataProvider( name = "shortestPathsAndSingleEndNode" )
    private static Object[][] shortestPathsAndSingleEndNode() {

        final Node node1 = new Node( 1, "A1" );
        final Node node2 = new Node( 2, "A2" );
        final Node node3 = new Node( 3, "A3" );
        final Node node4 = new Node( 4, "A4" );
        final Node node5 = new Node( 5, "A5" );
        final Node node6 = new Node( 6, "A6" );

        Path< Node, Double > p1 = new Path< Node, Double >( node1, node2, 1.0 );
        Path< Node, Double > p2 = new Path< Node, Double >( node1, node3, 1.0 );
        Path< Node, Double > p3 = new Path< Node, Double >( node2, node6, 1.0 );
        Path< Node, Double > p4 = new Path< Node, Double >( node4, node6, 1.0 );
        Path< Node, Double > p5 = new Path< Node, Double >( node5, node6, 1.0 );
        final List< Path< Node, Double >> shortestPaths = new ArrayList<>();
        shortestPaths.add( p1 );
        shortestPaths.add( p2 );
        shortestPaths.add( p3 );
        shortestPaths.add( p4 );
        shortestPaths.add( p5 );

        return new Object[][] { { shortestPaths, node6 } };
    }
}
