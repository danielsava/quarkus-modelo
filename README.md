# Problemas

    1. Rest 
        a. Listar: redundância cíclica quando converte no Json
        b. Salvar: Coleção de PaginaFuncionalidade sem a referência da Página, pois foi retirado no DTO no Frontend
        c. Alterar: Cascade.All não funciona pois perde a referência para a lista.


# Docker

    - Postgres:
        docker run --name questionmarks-psql \
            -p 5432:5432 \
            -e POSTGRES_DB=questionmarks \
            -e POSTGRES_PASSWORD=mysecretpassword \
            -d postgres 



# Implementações Futuras

 Gerar os Tokens Jwt (estudar a possibilidade substituir a rotina, de gerar tokens, que foi feita por essas)
   - https://www.baeldung.com/java-json-web-tokens-jjwt ***
   - https://github.com/auth0/java-jwt ***
   - https://bitbucket.org/b_c/jose4j/wiki/JWT%20Examples

 Autenticação e Autorização em NodeJS e MongoDB. Desta forma, retira da aplicação cliente' a responsabilidade de lidar com esses requisitos. 
   Basico
    - https://jasonwatmore.com/post/2018/06/14/nodejs-mongodb-simple-api-for-authentication-registration-and-user-management
   
   Jwt Settings
    - https://www.luiztools.com.br/post/autenticacao-json-web-token-jwt-em-nodejs/
    
   Rotation e Revogação de Chaves Públicas
    - https://blog.angular-university.io/angular-jwt-authentication/ 
    
 
 Mongo
  Backup e Restore
   - https://jasonwatmore.com/post/2019/09/03/mongodb-how-to-backup-and-restore-a-database

    

# Documentação Referência

    Criteria Query
        - https://www.boraji.com/hibernate-5-criteria-query-example *** 
        - https://www.initgrep.com/posts/java/jpa/create-programmatic-queries-using-criteria-api ***
    
    Criteria Tuple MultiSelect
        - https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-api-tuple.html
    
    Json em Java
        - https://www.baeldung.com/java-json-binding-api
        - https://javaee.github.io/tutorial/jsonb002.html
        
    Datas em Json Angular
        - http://www.adam-bien.com/roller/abien/entry/serializing_and_deserializing_a_pojo
    
    Json serialization infinite recursion (loop recursivo infinito)
        - https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
        - http://keenformatics.blogspot.com/2013/08/how-to-solve-json-infinite-recursion.html
        - https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue ***
        - @JsonView, @JsonManagedReference, @JsonIgnore
        
    DTO Projections
        - https://thoughts-on-java.org/dto-projections/
    
    QueryParams
        - https://mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/
    
    HttpParams Decode e Encoded 
        - https://www.baeldung.com/java-url-encoding-decoding *** (muito bom)
    
    Quarkus : Certificado Digital
         - https://quarkus.io/guides/http-reference
         
    Quarkus : Injeção de Dependência com Módulos Externos
        - Indexar Beans externos: 
              https://stackoverflow.com/questions/55513502/how-to-create-a-jandex-index-in-quarkus-for-classes-in-a-external-module   
    


# Configurações Intellij
    - maven  (clean install): clean install
    - maven  (quarkus [dev]): compile quarkus:dev
    - remote (quarkus debug):   

# Quarkus Debug
   - https://stackoverflow.com/questions/55190015/how-can-i-debug-my-quarkus-application-that-is-running-in-dev-mode
   - Intellij: Basta criar uma tarefa 'Remote'. Start o maven 'quarkus[dev]' e depois inicializa a tarefa 'Remote'.
   - obs: "This will also listen for a debugger on port 5005. If you want to wait for the debugger to attach before 
            running you can pass -Dsuspend on the command line. If you don’t want the debugger at all you can use -Ddebug=false."



   
