package pascal.webapp.MusicTransporter.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "internalcalls", url = "http://localhost:8888")
public interface InternalCallsProxy {

    @GetMapping("/playlist")
    public String showPlaylist();

}
