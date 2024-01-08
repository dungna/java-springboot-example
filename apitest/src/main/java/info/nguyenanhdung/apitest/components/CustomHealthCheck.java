package info.nguyenanhdung.apitest.components;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class CustomHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        try {
            // Implement your custom health check logic here
            String computerName = InetAddress.getLocalHost().getHostName();
            return Health.up().withDetails("ComputerName ", computerName).build();
        } catch (Exception e) {
            return Health.down()
                    .withDetails("Error: ", e.getMessage()).build();
        }
    }
}
