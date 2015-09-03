package com.sp.service;

import java.util.List;
import java.util.Map;

import com.sp.dto.Employee;
import com.sp.dto.Graph;
import com.sp.dto.Node;

/**
 * EmployeeService interface for Employee related calls.
 * @author lavjeetk
 *
 */
public interface EmployeeService {

    /**
     * Returns Map containing Node as key and List of Employees as value..
     * 
     * @param dataInitializer
     * @param graph
     * @return Map containing Node as key and List of Employees as value.
     */
    public Map< Node, List< Employee >> getNodesWithEmployees( final Graph< Node, Double > graph );

}
