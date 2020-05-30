package com.example.graphql.controller;

import com.example.graphql.graphql_utilities.GraphQlUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GraphQlController {
    private GraphQL graphQL;
    
    @Autowired
    public GraphQlController(GraphQlUtility graphQlUtility) throws IOException {
        graphQL = graphQlUtility.createGraphQlObject();
    }

    @PostMapping(value = "/query")
    public ResponseEntity<Object> query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: " + result.getErrors());
        return ResponseEntity.ok(result.getData());
    }
}
