package com.example.cabproject.security;

import com.example.cabproject.dto.request.UserRequestDto;
import com.example.cabproject.entity.User;
import com.example.cabproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
//@SecurityRequBearerirement(name = " Authentication")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private final UserDetailsService jwtInMemoryUserDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepo;

	@PostMapping("/signing")
	public String  signIn(@RequestBody JwtRequest request)
			throws Exception {

		authenticate(request.getEmail(), request.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(request.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AfterSignInResponseDto signInResponseDto = AfterSignInResponseDto.builder()
				.token(token)
				.emailAddress(request.getEmail())
				.password(request.getPassword())
				.role(request.getRole())
				.build();

		return  signInResponseDto.toString();
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp (@RequestBody UserRequestDto dto){

		User entity = userRepo.findUserByEmailAddress(dto.getEmailAddress());
		if (entity == null) {
			User userEntity = User.builder()
					.emailAddress(dto.getEmailAddress())
					.password(passwordEncoder.encode(dto.getPassword()))
					.role(dto.getRole())
					.username(dto.getUsername())
					.name(dto.getName())
					.surName(dto.getSurName())
					.age(dto.getAge())
					.gender(dto.getGender())
					.phoneNumber(dto.getPhoneNumber())
					.homeAddress(dto.getHomeAddress())
					.cardDetails(dto.getCardDetails())
					.language(dto.getLanguage())
					.build();
			userRepo.save(userEntity);
			return ResponseEntity.ok("You signed!");
		}else
			return ResponseEntity.ok("This account already exist in our DB!");

	}



	private void authenticate(String username, String password) throws Exception {
		log.debug("Authenticating user: {}", username);

		if (username == null || password == null) {
			log.error("Authentication attempt with null username or password");
			throw new IllegalArgumentException("Username and password must not be null");
		}

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.error("User disabled: {}", username, e);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("Invalid credentials for user: {}", username, e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
