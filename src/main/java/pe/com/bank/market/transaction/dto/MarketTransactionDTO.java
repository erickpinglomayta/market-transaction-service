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
	private String sourcePaymentType;
	private Long sourcePaymentNumber;	
	private String destinationPaymentType;
	private Long destinationPaymentNumber;	

}
