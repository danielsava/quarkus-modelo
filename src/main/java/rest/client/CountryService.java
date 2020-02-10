package rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/v2")
@RegisterRestClient
public interface CountryService {

    /** A URL do endpoint está configurada no 'application.app.properties'. Para fins de teste: 'name' = greece (alpha2Code 'GR', capital 'Athenus', currencies.size() '1' [Euro]) */
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Country> getByName(@PathParam("name") String name);


    /** Chamada, para o mesmo endereço, agora de forma 'Assíncrona' (Async) */
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Country>> getByNameAsync(@PathParam("name") String name);

}
