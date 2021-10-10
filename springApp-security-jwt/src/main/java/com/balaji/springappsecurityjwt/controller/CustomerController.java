package com.balaji.springappsecurityjwt.controller;

import com.balaji.springappsecurityjwt.model.AuthenticatioResponse;
import com.balaji.springappsecurityjwt.model.AuthenticationRequest;
import com.balaji.springappsecurityjwt.security.MyUserDetailsService;
import com.balaji.springappsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/hello")
    public @ResponseBody String sayHello( ){
            return "Hello";
    }

    @PostMapping("/authentication")
    public @ResponseBody ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );
                }
        catch (BadCredentialsException e) {
            throw new Exception("not valid User", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticatioResponse(jwt));//ok means 200 status
    }
}
