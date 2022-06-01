package pe.com.bank.market.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.market.transaction.document.MarketTransactionDocument;

@Repository
public interface MarketTransactonRepository extends ReactiveMongoRepository <MarketTransactionDocument,String>{

}
