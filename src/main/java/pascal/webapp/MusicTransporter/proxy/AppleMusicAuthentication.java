package pascal.webapp.MusicTransporter.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "authenticationapple")
public interface AppleMusicAuthentication {
}
