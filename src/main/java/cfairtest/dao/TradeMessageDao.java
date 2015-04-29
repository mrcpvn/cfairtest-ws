package cfairtest.dao;

import javax.ejb.Stateless;

import cfairtest.entity.TradeMessage;

@Stateless
public class TradeMessageDao extends GenericDao<TradeMessage>{

	public TradeMessageDao(){
		super(TradeMessage.class);
	}
	
	public TradeMessage findLastMessage(){
		TradeMessage result = null;
		Integer maxId = (Integer) em.createQuery("select max(e.transactionId) from TradeMessage e").getSingleResult();
		result = this.findById(maxId);
		return result;
	}
}
