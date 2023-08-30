package com.tc.training.cabrentals.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.net.HttpHeaders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.services.UserService;
import com.tc.training.cabrentals.utils.CurrentUser;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfiguration extends OncePerRequestFilter {
  private final UserService userService;

  @Override
  protected void doFilterInternal( final HttpServletRequest request, final HttpServletResponse response,
      final FilterChain filterChain ) throws ServletException, IOException {
    HttpMethod method = HttpMethod.valueOf( request.getMethod() );
    String uri = request.getRequestURI();
    String token = request.getHeader( HttpHeaders.AUTHORIZATION );
    if( method.equals( HttpMethod.OPTIONS ) ) {
      filterChain.doFilter( request, response );
    } else {
      if( StringUtils.hasText( token ) && ( token.startsWith( "Bearer" ) ) ) {
        String actualToken = token.split( " " )[1].trim();
        if( StringUtils.hasLength( actualToken ) ) {
          try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken( actualToken );
            String firebaseId = firebaseToken.getUid();
            User user = userService.getByFirebaseId( firebaseId );
            CurrentUser.set( user );
          } catch( FirebaseAuthException e ) {
            throw new RuntimeException( e );
          }
        }
      }

    }
  }
}

