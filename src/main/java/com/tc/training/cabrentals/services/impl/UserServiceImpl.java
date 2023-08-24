package com.tc.training.cabrentals.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.tc.training.cabrentals.entities.QUser;
import com.tc.training.cabrentals.entities.User;
import com.tc.training.cabrentals.enums.Role;
import com.tc.training.cabrentals.repositories.UserRepository;
import com.tc.training.cabrentals.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User createOrUpdate( User user ) {
    return userRepository.save( user );
  }

  @Override
  public Page<User> getAllByFilters( final Integer pageSize, final Integer pageNumber, final Role role ) {
    BooleanBuilder where = new BooleanBuilder();
    if( role != null ) {
      where.and( QUser.user.role.eq( role ) );
    }

    PageRequest pageRequest = PageRequest.of( pageNumber, pageSize, Sort.by( "name" ) );
    return userRepository.findAll( where, pageRequest );
  }

  @Override
  public Optional<User> getById( UUID id ) {
    return userRepository.findById( id );
  }

  @Override
  public void deleteById( UUID id ) {
    userRepository.deleteById( id );
  }

  @Override
  public User add( User user ) {
    return userRepository.save( user );
  }

  @Override
  public Boolean userExistsByRole( Role role ) {
    return userRepository.existsByRole( role );
  }
}
