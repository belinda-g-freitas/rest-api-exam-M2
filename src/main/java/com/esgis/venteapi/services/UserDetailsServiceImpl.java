package com.esgis.venteapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.esgis.venteapi.models.UserInfo;
import com.esgis.venteapi.repositories.AgentVendeurRepository;
import com.esgis.venteapi.repositories.BoutiqueRepository;
import com.esgis.venteapi.repositories.SuperviserRepository;
import com.esgis.venteapi.repositories.SuperviseurRepository;
import com.esgis.venteapi.repositories.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoutiqueRepository storeRepository;
    @Autowired
    private AgentVendeurRepository sellerRepository;
    @Autowired
    private SuperviseurRepository supervisorSRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // logger.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username);
        UserInfo seller = sellerRepository.findByUsername(username);
        UserInfo store = storeRepository.findByUsername(username);
        UserInfo supervisor = supervisorSRepository.findByUsername(username);
        UserInfo userInfo = user != null ? user : seller != null ? seller : store != null ? store : supervisor;

        if (userInfo == null) {
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        // System.out.println(userInfo);

        // logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(userInfo);
    }

}
