package pe.com.bank.market.transaction.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuySellRate {
	
	private Double buyRate;
	private Double sellRate;

}
