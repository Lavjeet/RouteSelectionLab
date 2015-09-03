package com.sp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sp.db.DBService;
import com.sp.dto.Employee;
import com.sp.dto.Gender;
import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.test.config.AbstractBaseMockitoTest;

/**
 * Test class for EmployeeServiceImpl
 * 
 * @author lavjeetk
 *
 */
// @RunWith( MockitoJUnitRunner.class )
public class EmployeeServiceImplTest extends AbstractBaseMockitoTest {

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private DBService dbService;

    private Graph< Node, Double > graph = new Graph<>();

    /**
     * Tests with empty employee list. Should return Empty Map.
     */
    @Test
    public void testNodesWithEmployeesWithEmptyEmployeeList() {
        final List< Employee > list = new ArrayList< Employee >();
        Mockito.when( dbService.initEmployeesList( Mockito.any( Set.class ) ) ).thenReturn( list );
        final Map< Node, List< Employee >> returnedMap = employeeServiceImpl.getNodesWithEmployees( graph );
        Assert.assertTrue( returnedMap.size() == 0 );
    }

    /**
     * Tests with one employee in one node.
     */
    @Test( dataProvider = "nodesWithMaxOneEmployeeEach" )
    public void testNodesWithEmployeesMaxOneEmpInANode( final List< Employee > list ) {

        Mockito.when( dbService.initEmployeesList( Mockito.any( Set.class ) ) ).thenReturn( list );
        final Map< Node, List< Employee >> returnedMap = employeeServiceImpl.getNodesWithEmployees( graph );
        Assert.assertTrue( returnedMap.size() == list.size() );
    }

    /**
     * Tests with multiple employees in one node.
     */
    @Test( dataProvider = "nodesWithMultipleEmployees" )
    public void testNodesWithMultipleEmployeesInANode( final List< Employee > list ) {

        Mockito.when( dbService.initEmployeesList( Mockito.any( Set.class ) ) ).thenReturn( list );
        final Map< Node, List< Employee >> returnedMap = employeeServiceImpl.getNodesWithEmployees( graph );
        Assert.assertTrue( returnedMap.size() == 1 );
        // there should be 2 employees for the given node.
        Assert.assertTrue( returnedMap.get( list.get( 0 ).getNode() ).size() == 2 );
    }

    /**
     * DataProvider for nodesWithMaxOneEmployeeEach
     * 
     * @return
     */
    @DataProvider( name = "nodesWithMaxOneEmployeeEach" )
    private static Object[][] nodesWithMaxOneEmployeeEach() {

        final Node node1 = new Node( 1, "A1" );
        final Employee emp1 = new Employee( "Hari", Gender.MALE, node1 );
        final Node node2 = new Node( 2, "A2" );
        final Employee emp2 = new Employee( "Hari", Gender.MALE, node2 );

        final List< Employee > list1 = new ArrayList< Employee >();
        list1.add( emp1 );

        final List< Employee > list2 = new ArrayList< Employee >();
        list2.add( emp1 );
        list2.add( emp2 );

        return new Object[][] { { list1 }, { list2 } };
    }

    /**
     * DataProvider for nodesWithMultipleEmployees
     * 
     * @return
     */
    @DataProvider( name = "nodesWithMultipleEmployees" )
    private static Object[][] nodesWithMultipleEmployees() {

        final Node node1 = new Node( 1, "A1" );
        final Employee emp1 = new Employee( "Hari1", Gender.MALE, node1 );
        final Employee emp2 = new Employee( "Hari2", Gender.MALE, node1 );

        final List< Employee > list2 = new ArrayList< Employee >();
        list2.add( emp1 );
        list2.add( emp2 );

        return new Object[][] { { list2 } };
    }
}
