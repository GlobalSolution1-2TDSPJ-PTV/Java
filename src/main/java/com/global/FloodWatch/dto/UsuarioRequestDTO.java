package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO para requisição de criação ou atualização de Usuário")
public class UsuarioRequestDTO {
    @Schema(description = "Nome completo do usuário.", example = "Maria Souza", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @Schema(description = "Endereço de e-mail do usuário.", example = "maria.souza@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @Schema(description = "Senha do usuário (mínimo 6 caracteres). Será feito hash antes de salvar.", example = "senhaForte123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @Schema(description = "Tipo de usuário.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O tipo de usuário não pode ser nulo")
    private Usuario.TipoUsuario tipoUsuario;

    @Schema(description = "Número de telefone do usuário.", example = "21998877665")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Schema(description = "Latitude da localização do usuário.", example = "-22.906847")
    private Double latitude;

    @Schema(description = "Longitude da localização do usuário.", example = "-43.172897")
    private Double longitude;
}