package cfairtest.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import cfairtest.exception.NotFoundException;
import cfairtest.model.TradeModel;
import cfairtest.processor.MessageProcessor;
import cfairtest.validator.MessageValidator;
import static cfairtest.constants.Constants.*;
import cfairtest.model.TradeData;

@Path("/trade")
@Stateless
public class TradeResource {

	private final Logger LOG = LoggerFactory.getLogger(TradeResource.class);
	private final SimpleDateFormat parserSDF = new SimpleDateFormat(DATE_FORMAT);

	@EJB
	private TradeMessageDao dao;
	@EJB
	private MessageProcessor processor;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTrade(TradeModel trade) throws ParseException {
		LOG.debug("validate new trade");
		MessageValidator.validate(trade);
		LOG.debug("process trades");
		processor.processTradeModel(trade); 
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
	public TradeModel readTrade(@PathParam("id") String id) {
		LOG.debug("read trade " + id);
		TradeMessage entity = null;
		if("last".equals(id)){
			entity = dao.findLastMessage();
		}else{
		 entity = dao.findById(Integer.parseInt(id));
		}
		if (entity != null) {
			TradeModel response = new TradeModel();
			response.setUserId(entity.getUserId());
			response.setCurrencyFrom(entity.getCurrencyFrom());
			response.setCurrencyTo(entity.getCurrencyTo());
			response.setAmountSell(entity.getAmountSell());
			response.setAmountBuy(entity.getAmountBuy());
			response.setRate(entity.getRate());
			response.setTimePlaced(parserSDF.format(entity.getTimePlaced()));
			response.setOriginatingCountry(entity.getOriginatingCountry());
			return response;
		}else{
			throw new NotFoundException();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/stats")
	public List<TradeData> getStats(){
		//get list of processed messages
		return processor.getTradeStats();
	}
	
}
