package info.nguyenanhdung.apitest.repository;

import info.nguyenanhdung.apitest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
