package piktoproject.pikto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import piktoproject.pikto.controllers.UserController;
import piktoproject.pikto.models.User;
import piktoproject.pikto.repositorys.UserCrud;

import java.util.Map;

@Service
public class GoogleInfoService extends OidcUserService {

    @Autowired
    private UserCrud userCrud;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        User user = new User();
        System.out.println(attributes.toString());
        user.setEmail((String) attributes.get("email"));
        user.setPictureUrl((String) attributes.get("picture"));
        user.setFirstName((String )attributes.get("given_name"));
        user.setLastName((String )attributes.get("family_name"));
        updateUser(user);
        return oidcUser;
    }

    private void updateUser(User googleuser) {
        User user = userCrud.getUserByEmail(googleuser.getEmail());
        if(user.getEmail() == null) {
            googleuser.setPassword("");
            googleuser.setMobileNr("");
            userCrud.addUser(googleuser);
        }
        user.setEmail(googleuser.getEmail());
        user.setPictureUrl(googleuser.getPictureUrl());
        user.setFirstName(googleuser.getFirstName());
        user.setLastName(googleuser.getLastName());
        userCrud.updateUser(user);
    }
}
