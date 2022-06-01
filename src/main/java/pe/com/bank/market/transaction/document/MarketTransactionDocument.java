package pe.com.bank.market.transaction.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "market_transaction")
public class MarketTransactionDocument {
	
	@Id
	private String marketTransactionId;
	private String marketId;
	private Double amount;
	private String paymentType;
	private Long paymentNumber;
	private Date dateMarketTransaction;
	private String buyerBootcoinWalletId;
	private String sellerBootcoinWalletId;
	

}
