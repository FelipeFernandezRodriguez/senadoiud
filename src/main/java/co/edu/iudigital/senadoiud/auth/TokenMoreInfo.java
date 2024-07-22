package co.edu.iudigital.senadoiud.auth;


import co.edu.iudigital.senadoiud.models.UserEntity;
import co.edu.iudigital.senadoiud.services.ifaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Adicionar info del usuario al token
 * o cualquier otra
 * luego se registra en AuthorizationServerConfig
 * @author JULIOCESARMARTINEZ
 *
 */
@Component
public class TokenMoreInfo implements TokenEnhancer {

    @Autowired
    private IUserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserEntity userEntity = userService.searchByEmail(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("id_usuario", ""+ userEntity.getId());
        info.put("nombre", userEntity.getName());
        info.put("image", userEntity.getImage());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }

}
