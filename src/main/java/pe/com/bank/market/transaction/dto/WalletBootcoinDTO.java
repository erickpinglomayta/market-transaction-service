package pe.com.bank.market.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletBootcoinDTO {
	
	private String sourceWalletBootcoinId;
	private String destinatonWalletBootcoinId;
	private Double amount;

}
