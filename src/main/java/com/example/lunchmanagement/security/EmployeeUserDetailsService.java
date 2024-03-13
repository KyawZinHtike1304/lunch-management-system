package com.example.lunchmanagement.security;

import com.example.lunchmanagement.dao.EmployeeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {
    private final EmployeeDao employeeDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeDao.findEmployeeByUserName(username)
                .map(EmployeeSecurity::new)
                .orElseThrow(()->new UsernameNotFoundException("Error!"));
    }
}
