package com.sp.service.impl;

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

import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.dto.Path;
import com.sp.test.config.AbstractBaseMockitoTest;

/**
 * Test class for ShortestPathGenerator.
 * @author lavjeetk
 * @param <T>
 *
 */
public class ShortestPathGeneratorTest extends AbstractBaseMockitoTest {

    @InjectMocks
    ShortestPathGenerator< Node > shortestPathGenerator;

    @Mock
    private Graph< Node,Double > graph;


    /**
     * Tests getShortestPathsFromSingleNode method.
     */
    @Test( dataProvider = "nodeMapProvider" )
    public void testGetShortestPathsFromSingleNode( final Map< Node, Map< Node, Double >> nodeMap ) {
        Mockito.when( graph.getNodeMap() ).thenReturn( nodeMap );

        final Node node1 = new Node( 1, "A" );
        final List< Path< Node, Double >> paths = shortestPathGenerator.getShortestPathsFromSingleNode( node1 );

        Assert.assertTrue( paths.size() == 5 );
    }

    /**
     * Tests getShortestPathsForSourceNodes method.
     */
    @Test( dataProvider = "nodeMapProvider" )
    public void testGetShortestPathsForSourceNodes( final Map< Node, Map< Node, Double >> nodeMap ) {
        Mockito.when( graph.getNodeMap() ).thenReturn( nodeMap );

        final Node node1 = new Node( 1, "A" );
        final Node node2 = new Node( 2, "B" );

        final Set< Node > sourceNodes = new HashSet<>();
        sourceNodes.add( node1 );
        sourceNodes.add( node2 );

        final List< Path< Node, Double >> paths = shortestPathGenerator.getShortestPathsForSourceNodes( sourceNodes );
        Assert.assertTrue( paths.size() == 10 );
    }
    
    /**
     * Data Provider method.
     *          B--D 
     *         /    \
     *        A      |    
     *         \    /
     *          C--E     
     */
    @DataProvider( name = "nodeMapProvider" )
    private Object[][] nodeMapProvider() {
        final Map< Node, Map< Node, Double >> nodeMap = new HashMap< Node, Map<Node,Double> >();
        
        final Node node1 = new Node( 1, "A" );
        final Node node2 = new Node( 2, "B" );
        final Node node3 = new Node( 3, "C" );
        final Node node4 = new Node( 4, "D" );
        final Node node5 = new Node( 5, "E" );
        
        final Map<Node, Double> m1 = new HashMap<>();
        m1.put( node2, 2.0 );
        m1.put( node3, 3.0 );
        nodeMap.put(node1, m1);
        
        final Map<Node, Double> m2 = new HashMap<>();
        m2.put( node1, 2.0 );
        m2.put( node4, 3.0 );
        nodeMap.put(node2, m2);
        
        final Map<Node, Double> m3 = new HashMap<>();
        m3.put( node1, 2.0 );
        m3.put( node5, 3.0 );
        nodeMap.put(node3, m3);
        
        final Map<Node, Double> m4 = new HashMap<>();
        m4.put( node2, 2.0 );
        m4.put( node5, 3.0 );
        nodeMap.put(node4, m4);
        
        final Map<Node, Double> m5 = new HashMap<>();
        m5.put( node3, 2.0 );
        m5.put( node3, 3.0 );
        nodeMap.put(node5, m5);
        
        return new Object[][] { { nodeMap } };
    }
}
