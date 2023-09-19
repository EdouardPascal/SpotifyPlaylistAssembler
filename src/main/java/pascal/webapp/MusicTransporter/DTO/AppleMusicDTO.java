package pascal.webapp.MusicTransporter.DTO;

import jakarta.annotation.PostConstruct;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.FileReader;
import java.io.IOException;
import java.security.PrivateKey;

@Component
@SessionScope
public class AppleMusicDTO {

    @Value("${private_key_name}")
    private String apple_key_name;

    private PrivateKey apple_key;

    @PostConstruct //initialize the Apple music DTO after its construction
    public void init() throws IOException {

        final PEMParser pemParser = new PEMParser(new FileReader(apple_key_name));
        final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        final PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
        final PrivateKey pKey = converter.getPrivateKey(object);
        pemParser.close();
        this.setApple_key(pKey);

    }

    public PrivateKey getApple_key() {
        return apple_key;
    }

    public void setApple_key(PrivateKey apple_key) {
        this.apple_key = apple_key;
    }
}
