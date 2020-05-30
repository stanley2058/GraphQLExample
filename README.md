# SpringBoot GraphQL Example
*This repo is base on this [Article](https://medium.com/oril/spring-boot-graphql-mongodb-8733002b728a)*


### Test it!
> Use Postman to POST **localhost:8080/query** with following body.

1. Example 1
- Body:
    ```graphql
    {
        users {
            name
            age
        }
    }
    ```

- Result:
    ```json
    {
        "users": [
            {
                "name": "Igor",
                "age": 24
            },
            {
                "name": "Ellen",
                "age": 24
            },
            {
                "name": "John",
                "age": 53
            },
            {
                "name": "Nazar",
                "age": 14
            }
        ]
    }
    ```

2. Example 2
- Body:
    ```graphql
    {
        users {
           age
           name
           friends {
              name
           }
           articles {
              title
              minutesRead
           }
        }
    }
    ```
- Result:
    ```json
    {
        "users": [
            {
                "age": 24,
                "name": "Igor",
                "friends": [
                    {
                        "name": "Ellen"
                    },
                    {
                        "name": "John"
                    },
                    {
                        "name": "Nazar"
                    }
                ],
                "articles": [
                    {
                        "title": "GraphQL Crash Course",
                        "minutesRead": 20
                    }
                ]
            },
            {
                "age": 24,
                "name": "Ellen",
                "friends": [],
                "articles": [
                    {
                        "title": "Spring Boot + Websocket",
                        "minutesRead": 17
                    }
                ]
            },
            {
                "age": 53,
                "name": "John",
                "friends": [],
                "articles": []
            },
            {
                "age": 14,
                "name": "Nazar",
                "friends": [],
                "articles": [
                    {
                        "title": "Road from Java 8 to Java 11",
                        "minutesRead": 8
                    }
                ]
            }
        ]
    }
    ```
