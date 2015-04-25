package cfairtest.resource;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cfairtest.model.DateModel;

@Path("/sysdate")
public class DateResource {

	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
	public DateModel getDate(){
		DateModel model = new DateModel();
		model.setSysdate(Calendar.getInstance().getTime().toString());
		model.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
		return model;
	}
}
