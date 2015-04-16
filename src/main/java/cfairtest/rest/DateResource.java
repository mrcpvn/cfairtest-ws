package cfairtest.rest;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sysdate")
public class DateResource {

	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
	public String getDate(){
		return Calendar.getInstance().getTime().toString();
	}
}
