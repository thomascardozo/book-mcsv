package com.book.mcsv.user.dto;

import com.book.mcsv.user.model.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    private String key;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setDataCadastro(user.getDataCadastro());
        userDTO.setKey(user.getKey());

        return userDTO;
    }
}
