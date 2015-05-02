package cfairtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.GenericType;
import org.junit.Before;
import org.junit.Test;

import static cfairtest.constants.Constants.DATE_FORMAT;
import static org.junit.Assert.*;
import cfairtest.model.TradeData;

import java.util.List;

public class TradeResourceIT {

	Client client;
	private final SimpleDateFormat parserSDF = new SimpleDateFormat(DATE_FORMAT);

	@Before
	public void init() {
		client = ClientBuilder.newClient();
	}

	@Test
	public void testTradeCreation() {
		WebTarget target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		for (int i = 0; i < 10; i++) {
			String testTrade = "{\"userId\": \"134257\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \""
					+ parserSDF.format(Calendar.getInstance().getTime())
					+ "\", \"originatingCountry\" : \"FR\"}";
			Response response = target.request().post(
					Entity.entity(testTrade, MediaType.APPLICATION_JSON));
			System.out.println(response.getStatus());
			assertEquals("error post new trade", 204, response.getStatus());
		}
	}

	@Test
	public void testTradeCreationUseridNull() {
		WebTarget target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		String testTrade = "{\"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
		Response response = target.request().post(
				Entity.entity(testTrade, MediaType.APPLICATION_JSON));
		System.out.println(response.getStatus());
		System.out.println(response.getEntity());
		assertEquals("error validate new trade", 400, response.getStatus());
	}

	@Test
	public void testTradeCreationUseridEmpty() {
		WebTarget target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		String testTrade = "{\"userId\": \"   \", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \"24-JAN-15 10:27:44\", \"originatingCountry\" : \"FR\"}";
		Response response = target.request().post(
				Entity.entity(testTrade, MediaType.APPLICATION_JSON));
		System.out.println(response.getStatus());
		assertEquals("error validate new trade", 400, response.getStatus());
	}

	@Test
	public void testTradeNotFound() {
		WebTarget target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade/8888");
		Response response = target.request().get();
		System.out.println(response.getStatus());
		assertEquals("error trade should not be found", 404,
				response.getStatus());
	}
	
	@Test
	public void testStats(){
		WebTarget target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade");
		for (int i = 0; i < 150; i++) {
			String testTrade = "{\"userId\": \"134257\", \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"amountSell\": 1000, \"amountBuy\": 747.10, \"rate\": 0.7471, \"timePlaced\" : \""
					+ parserSDF.format(Calendar.getInstance().getTime())
					+ "\", \"originatingCountry\" : \"FR\"}";
			Response response = target.request().post(
					Entity.entity(testTrade, MediaType.APPLICATION_JSON));
			System.out.println(response.getStatus());
			assertEquals("error post new trade", 204, response.getStatus());
		}
		target = client
				.target("http://cfairtest-mrcpvn.rhcloud.com/api/trade/stats");
		Response response = target.request().get();
		assertEquals("error getting stats", 200, response.getStatus());
	}
	
}
