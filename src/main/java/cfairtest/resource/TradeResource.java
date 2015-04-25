package cfairtest.resource;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cfairtest.dao.TradeMessageDao;
import cfairtest.entity.TradeMessage;

@Path("/trade")
@Stateless
public class TradeResource {

	private final Logger LOG = LoggerFactory.getLogger(TradeResource.class);
	
	@EJB
	private TradeMessageDao dao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTrade(TradeMessage trade){
		LOG.debug("persist new trade");
		dao.persist(trade);
		LOG.debug("trade created");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public TradeMessage readTrade(@PathParam("id") String id){
		LOG.debug("read trade "+id);
		return dao.findById(id);
	}
}
