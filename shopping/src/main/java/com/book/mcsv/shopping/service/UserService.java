package com.book.mcsv.shopping.service;

import com.book.shoping.client.dto.UserDTO;
import com.book.shoping.client.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080/user/}")
    private String userApiURL;

    public UserDTO getUserByCpf(String cpf, String key) {

        try{
            RestTemplate restTemplate = new RestTemplate();
            //String userApiURL = "http://localhost:8080";
            //String url = "http://localhost:8080/user/cpf/" + cpf;

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(userApiURL + "/user/cpf/" + cpf);
            builder.queryParam("key", key);

            //ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);

            ResponseEntity<UserDTO> response = restTemplate
                    .getForEntity(builder.toUriString(), UserDTO.class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e){
            throw new UserNotFoundException();
        }

    }
}
