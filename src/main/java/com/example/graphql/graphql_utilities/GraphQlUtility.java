package com.example.graphql.graphql_utilities;

import com.example.graphql.dataFetcher.AllUsersDataFetcher;
import com.example.graphql.dataFetcher.ArticlesDataFetcher;
import com.example.graphql.dataFetcher.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class GraphQlUtility {
    @Value("classpath:models.graphqls")
    private Resource schemeResource;
    private AllUsersDataFetcher allUsersDataFetcher;
    private UserDataFetcher userDataFetcher;
    private ArticlesDataFetcher articlesDataFetcher;

    @Autowired
    public GraphQlUtility(AllUsersDataFetcher allUsersDataFetcher, UserDataFetcher userDataFetcher, ArticlesDataFetcher articlesDataFetcher) {
        this.allUsersDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.articlesDataFetcher = articlesDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemeResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWriting -> typeWriting
                    .dataFetcher("users", allUsersDataFetcher)
                    .dataFetcher("user", userDataFetcher))
                .type("User", typeWriting -> typeWriting
                    .dataFetcher("articles", articlesDataFetcher)
                    .dataFetcher("friends", allUsersDataFetcher))
                .build();
    }


}
