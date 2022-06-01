package pe.com.bank.market.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketTransactionDTO {
	
	private String marketRequestId;
	private Double amount;
	private String buyerBootcoinWalletId;
	private String sellerBootcoinWalletId;
	private String paymentType;
	private Long paymentNumber;		

}
