package proektwp.proektwp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proektwp.proektwp.models.Transactions;

public interface JpaTransactionRepository extends JpaRepository<Transactions, Long> {
}
