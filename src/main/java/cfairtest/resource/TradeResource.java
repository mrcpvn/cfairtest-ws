package cfairtest.resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import cfairtest.model.TradeModel;
import static cfairtest.constants.Constants.*;

@Path("/trade")
@Stateless
public class TradeResource {

	private final Logger LOG = LoggerFactory.getLogger(TradeResource.class);
	private final SimpleDateFormat parserSDF = new SimpleDateFormat(DATE_FORMAT);

	@EJB
	private TradeMessageDao dao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTrade(TradeModel trade) throws ParseException {
		LOG.debug("persist new trade");
		TradeMessage entity = new TradeMessage();
		entity.setUserId(trade.getUserId());
		entity.setCurrencyFrom(trade.getCurrencyFrom());
		entity.setCurrencyTo(trade.getCurrencyTo());
		entity.setAmountSell(trade.getAmountSell());
		entity.setAmountBuy(trade.getAmountBuy());
		entity.setRate(trade.getRate());
		entity.setTimePlaced(parserSDF.parse(trade.getTimePlaced()));
		entity.setOriginatingCountry(trade.getOriginatingCountry());
		dao.persist(entity);
		LOG.debug("trade created");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public TradeMessage readTrade(@PathParam("id") String id) {
		LOG.debug("read trade " + id);
		return dao.findById(id);
	}
}
