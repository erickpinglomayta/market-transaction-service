package pe.com.bank.market.transaction.service;

import pe.com.bank.market.transaction.document.MarketTransactionDocument;
import pe.com.bank.market.transaction.redis.BuySellRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MarketTransactionService {
	
	public Flux<MarketTransactionDocument> getAllMarketTransaction();
	public Mono<MarketTransactionDocument> getMarketTransactionById(String marketTransactionId);
	public Mono<MarketTransactionDocument> saveMarketTransaction(MarketTransactionDocument marketTransactionDocument);
	public Mono<MarketTransactionDocument> updateMarketTransactionById(MarketTransactionDocument marketTransactionDocument,String marketTransactionId);
	public Mono<Void> deleteMarketTransactionById(String marketTransactionId);
	public Mono<BuySellRate> getBuySellRate(String key);
	public Mono<Boolean> saveBuySellRate(String key,BuySellRate buySellRate);

}
