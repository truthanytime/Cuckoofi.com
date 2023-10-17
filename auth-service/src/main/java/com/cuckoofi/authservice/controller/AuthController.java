package com.cuckoofi.authservice.controller;

import com.cuckoofi.authservice.model.AuthResponse;
import com.cuckoofi.authservice.model.SignInRequestBody;
import com.cuckoofi.authservice.model.SignupRequestBody;
import com.cuckoofi.authservice.service.impl.UserManageServiceImpl;
import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.cuckoofi.commonclientlibs.constant.ResponseConstant;
import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.jwt.JwtProvider;
import com.cuckoofi.commonclientlibs.service.impl.RoleServiceImpl;
import com.cuckoofi.commonclientlibs.service.impl.UserServiceImpl;
import com.cuckoofi.commonclientlibs.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserManageServiceImpl userManageService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping("/signin")
    public ResponseEntity<Response<AuthResponse>> login(@Valid @RequestBody SignInRequestBody loginRequest) {
        try {
            logger.info("login method called");
            User user = userService.findByEmail(loginRequest.getEmail().toLowerCase());
            if( user != null ) {
                // Generate password version
                String jwt = jwtProvider.generateJwtAuthToken(user.getEmail(), roleService.findByUser(user));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequest.getPassword());
                Authentication authentication = authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Save last login date and time
                user.setLastLoginDate(new Date());
                user = userService.saveUser( user );

                AuthResponse authResponse = new AuthResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getVerified(),
                        jwt,
                        user.getFirstName(),
                        user.getLastName(),
                        user.getTimeZone()
                );

                return ResponseEntity.ok(new Response<>(
                        HttpStatus.OK.value(),
                        true,
                        AuthConstant.SIGN_IN_SUCCESS,
                        authResponse
                ));
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new Response<>(
                                HttpStatus.UNAUTHORIZED.value(),
                                false,
                                AuthConstant.INCORRECT_CREDENTIAL
                        ));
            }
        } catch (Exception e) {
            logger.error("error happened in login method");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new Response<>(
                            HttpStatus.UNAUTHORIZED.value(),
                            false,
                            AuthConstant.INCORRECT_CREDENTIAL
                    ));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Response<AuthResponse>> signup(
            @Valid @RequestBody SignupRequestBody signupRequestBody) {

        logger.info("Signup method called");

        // Check if signed-up user.
        User user = userService.findByEmail(signupRequestBody.getEmail().toLowerCase());
        if ( user != null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Response<>(
                            HttpStatus.BAD_REQUEST.value(),
                            false,
                            AuthConstant.EMAIL_IN_USE
                    ));
        }

        try {
            // Create and save new user.
            User registeredUser = userManageService.registerNewUser( signupRequestBody );
            if( registeredUser == null )
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new Response<>(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                false,
                                AuthConstant.FAILED_IN_REGISTER
                        ));

            // Allow the created user to log in.
            String jwt;
            try {
                jwt = jwtProvider.generateJwtAuthToken(registeredUser.getEmail(), registeredUser.getRoles());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(signupRequestBody.getEmail(), signupRequestBody.getPassword());
                Authentication authentication = authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e){
                logger.error("error happened in set authentication", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new Response<>(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                false,
                                e.getMessage()
                        ));
            }

            AuthResponse authResponse = new AuthResponse(
                    registeredUser.getId(),
                    registeredUser.getEmail(),
                    registeredUser.getVerified(),
                    jwt,
                    registeredUser.getFirstName(),
                    registeredUser.getLastName(),
                    registeredUser.getTimeZone()
            );

            return ResponseEntity.ok(new Response<>(
                    HttpStatus.OK.value(),
                    true,
                    AuthConstant.SIGN_UP_SUCCESS,
                    authResponse
            ));
        }
        catch (Exception e) {
            logger.error("error happened in register method", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Response<String>> validateGet(HttpServletRequest request) {
        logger.info("validateGet method called");

        String token = request.getHeader("Authorization");
        try {
            String tokenState = jwtProvider.getJwtTokenState(token);
            if (tokenState.equals(ResponseConstant.TOKEN_VALID)) {
                return ResponseEntity.ok(
                        new Response<>(
                                HttpStatus.OK.value(),
                                true,
                                tokenState));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new Response<>(
                                HttpStatus.UNAUTHORIZED.value(),
                                false,
                                tokenState));
            }
        }
        catch ( Exception e ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()));
        }
    }

    @GetMapping("/checkUserAlreadyExists")
    public ResponseEntity<Response<String>> checkUserAlreadyExists(HttpServletRequest request) {
        logger.info("checkUserAlreadyExists method called");

        try {
            String email = request.getParameter("email");
            if( email == null ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new Response<>(
                                HttpStatus.BAD_REQUEST.value(),
                                false,
                                AuthConstant.REQUIRED_EMAIL));
            }

            User user = userService.findByEmail( email );
            String status = "This email user exist now.";
            if( user == null )
                status = "This email user does not exist.";
            return ResponseEntity.ok(
                    new Response<>(
                            HttpStatus.OK.value(),
                            user != null,
                            status));
        }
        catch ( Exception e ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()));
        }
    }

    @PostMapping("/authTest")
    public ResponseEntity<Response<User>> authTest(HttpServletRequest request) {

        logger.info("authTest method called");
        try {
            String token = request.getHeader("Authorization");
            String userNameFromJwtToken = jwtProvider.getUserNameFromJwtToken(token);
            User user = userService.findByEmail(userNameFromJwtToken);
            return ResponseEntity.ok(
                    new Response<>(
                            HttpStatus.OK.value(),
                            true,
                            AuthConstant.AUTH_TEST_SUCCESS,
                            user
                    ));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }
}
