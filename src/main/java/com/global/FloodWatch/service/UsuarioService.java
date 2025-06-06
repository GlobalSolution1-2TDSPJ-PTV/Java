package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.UsuarioRequestDTO;
import com.global.FloodWatch.dto.UsuarioResponseDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Usuario;
import com.global.FloodWatch.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenhaHash(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        usuario.setTipoUsuario(usuarioRequestDTO.getTipoUsuario());
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setLatitude(usuarioRequestDTO.getLatitude());
        usuario.setLongitude(usuarioRequestDTO.getLongitude());
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return mapToUsuarioResponseDTO(novoUsuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
        return mapToUsuarioResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarEntidadeUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarEntidadeUsuarioEmail(String email) {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com o email: " + email);
        }
        return usuario;
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        if (usuarioRequestDTO.getSenha() != null && !usuarioRequestDTO.getSenha().isEmpty()) {
            usuario.setSenhaHash(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        }
        usuario.setTipoUsuario(usuarioRequestDTO.getTipoUsuario());
        usuario.setTelefone(usuarioRequestDTO.getTelefone());
        usuario.setLatitude(usuarioRequestDTO.getLatitude());
        usuario.setLongitude(usuarioRequestDTO.getLongitude());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return mapToUsuarioResponseDTO(usuarioAtualizado);
    }
    @Transactional
    public void atualizarSenha(String email, String novaSenha) {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);
        String senhaCriptografada = new BCryptPasswordEncoder().encode(novaSenha);
        usuario.setSenhaHash(senhaCriptografada);
        usuarioRepository.save(usuario);
    }



    @Transactional
    public void deletarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        // Adicionar lógica para verificar dependências antes de excluir, se necessário
        // Ex: verificar se o usuário tem SOS ativos, etc.
        usuarioRepository.deleteById(id);
    }


    private UsuarioResponseDTO mapToUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getTelefone(),
                usuario.getLatitude(),
                usuario.getLongitude(),
                usuario.getCriadoEm()
        );
    }
}
