package cfairtest.dao;

import javax.ejb.Stateless;

import cfairtest.entity.TradeMessage;

@Stateless
public class TradeMessageDao extends GenericDao<TradeMessage>{

	public TradeMessageDao(){
		super(TradeMessage.class);
	}
}
