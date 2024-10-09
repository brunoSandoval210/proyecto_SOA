package com.proyecto.soa.services;

import com.proyecto.soa.entities.Role;
import com.proyecto.soa.entities.User;
import com.proyecto.soa.repositories.RolRepository;
import com.proyecto.soa.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

}
