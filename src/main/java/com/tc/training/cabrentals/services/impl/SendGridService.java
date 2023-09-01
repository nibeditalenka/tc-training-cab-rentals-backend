package com.tc.training.cabrentals.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.tc.training.cabrentals.services.EmailService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SendGridService implements EmailService {
  @Value( "${sendgrid.from-mail}" )
  private String SENDGRID_FROM_MAIL;
  @Value( "${sendgrid.from-name}" )
  private String SENDGRID_FROM_NAME;
  @Value( "${sendgrid.api-key}" )
  private String SENDGRID_MAIL_API_KEY;

  @Override
  public void sendEmail( final String toMail, final String subject, final String body ) {
    try {
      Email from = new Email( SENDGRID_FROM_MAIL );
      from.setName( SENDGRID_FROM_NAME );
      Email to = new Email( toMail );
      Content content = new Content( "text/html", body );
      Mail mail = new Mail( from, subject, to, content );

      SendGrid sendGrid = new SendGrid( SENDGRID_MAIL_API_KEY );
      Request request = new Request();
      request.setMethod( Method.POST );
      request.setEndpoint( "mail/send" );
      request.setBody( mail.build() );
      Response response = sendGrid.api( request );
      log.info( "Mail Sent to {} with {} status code", toMail, response.getStatusCode() );
    } catch( Exception e ) {
      log.error( "Could not send mail" );
      e.printStackTrace();
    }
  }
}
