package pe.com.bank.market.transaction.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import pe.com.bank.market.transaction.document.MarketTransactionDocument;
import pe.com.bank.market.transaction.redis.BuySellRate;
import pe.com.bank.market.transaction.service.MarketTransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@RequestMapping("/v1")
@RestController
public class MarketTransactionController {
	
	MarketTransactionService marketTransactionService;
	
	@GetMapping("/getAllMarketTransaction")
	public Flux<MarketTransactionDocument> getAllMarketTransaction(){
		return marketTransactionService.getAllMarketTransaction();
	}
	
	@GetMapping("/getMarketTransactionById/{marketTransactionId}")
	public Mono<MarketTransactionDocument> getMarketTransactionById(@PathVariable String marketTransactionId){
		return marketTransactionService.getMarketTransactionById(marketTransactionId);
	}
	
	@PostMapping("/saveMarketTransaction")
	public Mono<MarketTransactionDocument> saveMarketTransaction(@RequestBody MarketTransactionDocument marketTransactionDocument){
		return marketTransactionService.saveMarketTransaction(marketTransactionDocument);
	}
	
	@PutMapping("/updateMarketTransactionById/{marketTransactionId}")
	public Mono<MarketTransactionDocument> updateMarketTransactionById(@RequestBody MarketTransactionDocument marketTransactionDocument,@PathVariable String marketTransactionId){
		return marketTransactionService.updateMarketTransactionById(marketTransactionDocument,marketTransactionId);
	}
	
	@DeleteMapping("/deleteMarketTransactionById/{marketTransactionId}")
	public Mono<Void> deleteMarketTransactionById(@PathVariable String marketTransactionId){
		return marketTransactionService.deleteMarketTransactionById(marketTransactionId);
	}
	
	@GetMapping("/getBuySellRate/{key}")
	public Mono<BuySellRate> getBuySellRate(@PathVariable String key){
		return marketTransactionService.getBuySellRate(key);
	}
	
	
	@PostMapping("/saveBuySellRate/{key}")
	public Mono<Boolean> saveBuySellRate(@PathVariable String key,@RequestBody BuySellRate buySellRate){
		return marketTransactionService.saveBuySellRate(key, buySellRate);
	}

}
