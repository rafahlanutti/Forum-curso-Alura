package br.com.alura.forum.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {

    private String email;
    private String senha;

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
