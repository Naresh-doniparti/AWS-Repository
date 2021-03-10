package com.microservices.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.microservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class EmployeeDynamoDBRepository {

    private static final Logger logger = Logger.getLogger(EmployeeDynamoDBRepository.class.getName());

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public  void save(Employee employee){
        dynamoDBMapper.save(employee);
    }

    public Employee get(String employeeId, String name){
        return dynamoDBMapper.load(Employee.class, employeeId, name);
    }

    public void delete(Employee employee){
        dynamoDBMapper.delete(employee);
    }

    public void update(Employee employee){
        try {
            dynamoDBMapper.save(employee, dynamoDBSaveExpressionForUpdatingEmployee(employee));
        }catch (ConditionalCheckFailedException cce){
            logger.log(Level.WARNING, cce.getMessage());
        }

    }

    //Used to check whether the key exists before making an update. If key is not there,
    // it is going to throw ConditionalCheckFailedException
    public DynamoDBSaveExpression dynamoDBSaveExpressionForUpdatingEmployee(Employee employee){
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("id",
                new ExpectedAttributeValue(
                        new AttributeValue(employee.getId()))).withComparisonOperator(ComparisonOperator.EQ);
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }

}
