package com.tc.training.cabrentals.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
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
    if( method.equals( HttpMethod.OPTIONS ) || !StringUtils.hasText( token ) ) {
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
            filterChain.doFilter( request, response );
          } catch( FirebaseAuthException e ) {
            throw new RuntimeException( e );
          }
        }
      }
    }
    response.setHeader( "Access-Control-Allow-Origin", "*" );
    response.setHeader( "Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE" );
    response.setHeader( "Access-Control-Max-Age", "3600" );
    response.setHeader( "Access-Control-Allow-Headers",
        "X-Requested-With,Origin,Content-Type, Accept, x-device-user-agent, Content-Type" );

  }

  @Override
  protected boolean shouldNotFilter( final HttpServletRequest request ) throws ServletException {
    List<Map<String, Object>> publicApis = List.of( Map.of( "url", "/users/login", "method", RequestMethod.POST ) );

    String requestURI = request.getRequestURI();
    RequestMethod requestMethod = RequestMethod.valueOf( request.getMethod() );

    return publicApis.stream()
        .anyMatch( api -> api.get( "url" ).equals( requestURI ) && api.get( "method" ).equals( requestMethod ) );
  }

}

