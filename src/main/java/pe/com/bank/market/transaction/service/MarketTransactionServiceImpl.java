package pe.com.bank.market.transaction.service;


import java.util.Date;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

import pe.com.bank.market.transaction.document.MarketTransactionDocument;
import pe.com.bank.market.transaction.dto.MarketTransactionDTO;
import pe.com.bank.market.transaction.dto.PaymentDTO;
import pe.com.bank.market.transaction.dto.WalletBootcoinDTO;
import pe.com.bank.market.transaction.redis.BuySellRate;
import pe.com.bank.market.transaction.repository.MarketTransactonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MarketTransactionServiceImpl implements MarketTransactionService {
	
	@Autowired
	MarketTransactonRepository marketTransactonRepository;
	@Autowired
	StreamBridge streamBridge;
	
	private final ReactiveRedisOperations<String, BuySellRate> operations;
	
	
	public MarketTransactionServiceImpl(ReactiveRedisOperations<String, BuySellRate> operations) {
        this.operations = operations;
    }
	
	
	public Flux<MarketTransactionDocument> getAllMarketTransaction(){
		return marketTransactonRepository.findAll();
	}

	
	public Mono<MarketTransactionDocument> getMarketTransactionById(String marketTransactionId){
		return marketTransactonRepository.findById(marketTransactionId);
	}
	public Mono<MarketTransactionDocument> saveMarketTransaction(MarketTransactionDocument marketTransactionDocument){
		return marketTransactonRepository.save(marketTransactionDocument);
	}
	public Mono<MarketTransactionDocument> updateMarketTransactionById(MarketTransactionDocument marketTransactionDocument,String marketTransactionId){
		return null;
	}
	public Mono<Void> deleteMarketTransactionById(String marketTransactionId){
		return marketTransactonRepository.deleteById(marketTransactionId);
	}
	
	
	public Mono<BuySellRate> getBuySellRate(String key){ 
                
        return operations.opsForValue().get(key);
        
	}
	
	public Mono<Boolean> saveBuySellRate(String key,BuySellRate buySellRate){ 
       
        return operations.opsForValue().set(key,buySellRate);
        
	}
	
	 @Bean
	 Consumer<MarketTransactionDTO> saveMarketTrasanction() {
		 return marketTransactionDTO -> {
			 saveMarketTransaction(new MarketTransactionDocument(null,marketTransactionDTO.getMarketRequestId(),marketTransactionDTO.getAmount(),
					 marketTransactionDTO.getPaymentType(),marketTransactionDTO.getPaymentNumber(),new Date(),marketTransactionDTO.getBuyerBootcoinWalletId(),
					 marketTransactionDTO.getSellerBootcoinWalletId())).flatMap( m -> {
						 sendUpdateWalletBootcoin(new WalletBootcoinDTO(marketTransactionDTO.getSellerBootcoinWalletId(),marketTransactionDTO.getBuyerBootcoinWalletId(),
								 marketTransactionDTO.getAmount()));
						 Mono<BuySellRate> buySellRate = getBuySellRate("buySellRate");
						 return buySellRate.flatMap(buySell ->{						
							 	if(marketTransactionDTO.getPaymentType().equals("YANKI")) {
							 			sendPaymentYanki(new PaymentDTO(marketTransactionDTO.getPaymentNumber(),buySell.getBuyRate(),buySell.getSellRate()));
							 	}else {
							 			sendPaymentAccount(new PaymentDTO(marketTransactionDTO.getPaymentNumber(),buySell.getBuyRate(),buySell.getSellRate()));
							 		}						 
							 	return Mono.empty();
						 		});
					 	}).subscribe();        
		 };
		 
	 }
	 
	 private void sendPaymentAccount(PaymentDTO marketTransactionDTO) {
		 streamBridge.send("sendPaymentAccount-out-0",marketTransactionDTO);
	}
	 
	 private void sendPaymentYanki(PaymentDTO marketTransactionDTO) {
		 streamBridge.send("sendPaymentYanki-out-0",marketTransactionDTO);
	}	
	 
	 private void sendUpdateWalletBootcoin(WalletBootcoinDTO walletBootcoinDTO) {
		 streamBridge.send("sendUpdateWalletBootcoin-out-0",walletBootcoinDTO);
	}
	 
		

}
