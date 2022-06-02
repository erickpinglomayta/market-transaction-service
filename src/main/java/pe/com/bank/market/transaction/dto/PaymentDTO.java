package pe.com.bank.market.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private Double amount;
	private Long sourceNumberPayment;
	private Long destinationNumberPayment;
	private Double buyRate;
	private Double sellRate;

}
