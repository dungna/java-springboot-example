package info.nguyenanhdung.apitest.repositories;

import info.nguyenanhdung.apitest.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
