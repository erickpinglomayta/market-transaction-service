package pe.com.bank.market.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private Long numberPayment;
	private Double buyRate;
	private Double sellRate;

}
