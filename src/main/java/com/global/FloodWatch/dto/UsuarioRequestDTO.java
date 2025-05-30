package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    // Adicione validações de complexidade de senha se necessário (ex: @Pattern)
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha; // Senha em texto plano, o hash será feito no service

    @NotNull(message = "O tipo de usuário não pode ser nulo")
    private Usuario.TipoUsuario tipoUsuario;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    private Double latitude;
    private Double longitude;
}
