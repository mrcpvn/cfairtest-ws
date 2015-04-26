package cfairtest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;


public class TradeResourceIT {

	Client client;
	
	@Before
	public void init(){
		client = ClientBuilder.newClient();		
	}
	
	@Test
	public void testTradeCreation(){
		WebTarget target = client.target("http://cfairtest-mcloud.rhcloud.com/api/trade");
		String testTrade = "{\"userId\": \"134256\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
        Response response = target.request().post(Entity.entity(testTrade, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
	}
}
