package com.sp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sp.db.DBService;
import com.sp.dto.Employee;
import com.sp.dto.Graph;
import com.sp.dto.Node;
import com.sp.service.EmployeeService;

/**
 * Service class for Employee related calls.
 * @author lavjeetk
 *
 */
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * DataInitializer
     */
    private DBService dbService;

    /**
     * Constructor
     * @param dbService
     */
    public EmployeeServiceImpl( final DBService dbService ) {
        this.dbService = dbService;
    }

    /**
     * Returns Map containing Node as key and List of Employees as value..
     * 
     * @param dataInitializer
     * @param graph
     * @return Map containing Node as key and List of Employees as value.
     */
    public Map< Node, List< Employee >> getNodesWithEmployees( final Graph< Node, Double > graph ) {
        final List< Employee > list = dbService.initEmployeesList( graph.getNodeMap().keySet() );
        final Map< Node, List< Employee >> empNodes = new HashMap<>();

        // Get list of nodes having employees
        for ( Employee e : list ) {
            List< Employee > employeesInGivenNode = empNodes.get( e.getNode() );
            if (employeesInGivenNode == null) {
                employeesInGivenNode = new ArrayList<>();
                empNodes.put( e.getNode(), employeesInGivenNode );
            }
            employeesInGivenNode.add( e );
        }
        return empNodes;
    }
    
}
