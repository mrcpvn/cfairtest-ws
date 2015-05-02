package cfairtest.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Stateful;

import cfairtest.model.TradeData;
import cfairtest.model.TradeModel;
import static cfairtest.constants.Constants.*;

@Stateful
public class MessageProcessor {

	private Map<Date, TradeData> cache = new ConcurrentHashMap<Date, TradeData>();
	private List<Date> timeList = new ArrayList<Date>();

	private final SimpleDateFormat parserSDF = new SimpleDateFormat(DATE_FORMAT);
	private final int MAX_CACHE_SIZE = 100;

	public void processTradeModel(TradeModel trade) throws ParseException {
		// create trade data
		if (trade.getTimePlaced() != null) {
			// get seconds
			Date tradeDate = parserSDF.parse(trade.getTimePlaced());
			TradeData cacheElem = cache.get(tradeDate);
			if (cacheElem != null) {
				// update elem
				cacheElem.setCounter(cacheElem.getCounter() + 1);
			} else {
				// new element: create new element, remove oldest
				TradeData newElement = new TradeData();
				newElement.setCounter(1);
				newElement.setDate(tradeDate);
				cache.put(tradeDate, newElement);
				timeList.add(tradeDate);
				if (timeList.size() > MAX_CACHE_SIZE) {
					// remove oldest elem
					cache.remove(timeList.get(0));
					timeList.remove(0);
				}
			}
		}
	}

	public List<TradeData> getTradeStats() {
		List<TradeData> result = new ArrayList<TradeData>();
		for (Date date : timeList) {
			result.add(cache.get(date));
		}
		return result;
	}
}
