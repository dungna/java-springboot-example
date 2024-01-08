package info.nguyenanhdung.apitest.repository;

import info.nguyenanhdung.apitest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserRepository extends JpaRepository<User, Long> {
}
