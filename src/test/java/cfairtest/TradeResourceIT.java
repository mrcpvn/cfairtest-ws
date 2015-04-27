package cfairtest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TradeResourceIT {

	Client client;
	
	@Before
	public void init(){
		client = ClientBuilder.newClient();		
	}
	
	@Test
	public void testTradeCreation(){
		WebTarget target = client.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		String testTrade = "{\"userId\": \"134256\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
        Response response = target.request().post(Entity.entity(testTrade, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        assertEquals("error post new trade",204, response.getStatus());
        
	}
	
	@Test
	public void testTradeCreationUseridNull(){
		WebTarget target = client.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		String testTrade = "{\"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
        Response response = target.request().post(Entity.entity(testTrade, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        System.out.println(response.getEntity());
        assertEquals("error validate new trade",400, response.getStatus());
	}
	
	@Test
	public void testTradeCreationUseridEmpty(){
		WebTarget target = client.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		String testTrade = "{\"userId\": \"   \", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
        Response response = target.request().post(Entity.entity(testTrade, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        assertEquals("error validate new trade",400, response.getStatus());
        
	}
	
	@Test
	public void testTradeNotFound(){
		WebTarget target = client.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade/8888");
		//String testTrade = "{\"userId\": \"   \", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
        Response response = target.request().get();
        System.out.println(response.getStatus());
        assertEquals("error trade should not be found",404, response.getStatus());
        
	}
}
