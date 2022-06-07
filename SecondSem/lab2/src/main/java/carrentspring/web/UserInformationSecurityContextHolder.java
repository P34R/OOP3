package carrentspring.web;

import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Set;

public interface UserInformationSecurityContextHolder {

  default Set<String> getRoles() {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();
    return (Set<String>) authentication.getDetails();
  }
}
