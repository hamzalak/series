package com.hamza.series.userNonInscritLogic;


import com.hamza.series.authLogic.jwt.JwtUtils;
import com.hamza.series.authLogic.service.UserDetailsImpl;
import com.hamza.series.model.Episode;
import com.hamza.series.model.Serie;
import com.hamza.series.model.auth.ERole;
import com.hamza.series.model.auth.Role;
import com.hamza.series.model.auth.User;
import com.hamza.series.model.dto.JwtResponse;
import com.hamza.series.model.dto.LoginForm;
import com.hamza.series.model.dto.MessageResponse;
import com.hamza.series.model.dto.RegistrationForm;
import com.hamza.series.repository.EpisodeRepo;
import com.hamza.series.repository.RoleRepo;
import com.hamza.series.repository.SerieRepo;
import com.hamza.series.repository.UserRepo;
import com.hamza.series.utility.DateUtils;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 34000)

@RequestMapping("/consult")
@RestController
public class UserNonInscritController {

    @Autowired
    private SerieRepo serieRepo;

    @Autowired
    private EpisodeRepo episodeRepo ;

    @Autowired
    UserRepo userRepo ;

    @Autowired
    RoleRepo roleRepo ;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping("/series")
    public ResponseEntity<List<Serie>> getAllSeries() {
        System.out.println("Get all series...");
        List<Serie> series = new ArrayList<>();
        serieRepo.findAll().forEach(series::add);

        return new ResponseEntity<>(series, HttpStatus.OK);
    }


    @GetMapping("/series/{id}")
    public ResponseEntity<?> getSerieEpisodes(@PathVariable("id") int id) throws ParseException {
        System.out.println("Get all series...");
        Map<String, Set<Episode>> series = new HashMap<>();
        Serie serie = serieRepo.getOne(id) ;
        System.out.println(serie.toString());
        Set<Episode> episodes = episodeRepo.findBySerieId(id);
        Serie  serie1 = serieRepo.getOne(id) ;
        series.put(serie1.toString(),episodes) ;
        return new ResponseEntity(series, HttpStatus.OK);
    }
    @PutMapping("/episode/{id}")
    public ResponseEntity<?> updateEpisode(@PathVariable("id") Integer id, @RequestBody Episode _episode) {
        Optional<Episode> episodeData = episodeRepo.findById(id) ;
        if (episodeData.isPresent()) {
            Episode episode = episodeData.get();
            episode.setTitre(_episode.getTitre());
            episode.setNumero(_episode.getNumero());
            episode.setDescription(_episode.getDescription());
            episode.setVue(false);
            return new ResponseEntity<>(episodeRepo.save(_episode), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Le User Name ici est le mail c'est une contrainte de la classe d'Authentification de spring
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody RegistrationForm registrationForm) {

        if (userRepo.existsByEmail(registrationForm.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email déja utlisé !"));
        }

        // Create new user's account
        User user = new User(
                registrationForm.getEmail(),
                encoder.encode(registrationForm.getPassword()));
        user.setNom(registrationForm.getNom());
        user.setPrenom(registrationForm.getPrenom());
        user.setUsername(registrationForm.getEmail());
        user.setNumero(registrationForm.getNumero());
        try {
            user.setDateNaissance(DateUtils.parseDate(registrationForm.getDateNaissance()));
        }catch(ParseException ex){

            ex.getErrorOffset() ;
        }
        Set<Role> roles = new HashSet<>() ;
        roles.add((roleRepo.findByName(ERole.ROLE_USER_INSCRIT).get())) ;
        user.setRoles(roles);
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("Utlisateur Inscrit Avec sucsee!"));
    }

}


