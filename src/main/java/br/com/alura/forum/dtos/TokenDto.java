package br.com.alura.forum.dtos;

public class TokenDto {

    private String token;
    private String type;

    public TokenDto(String token, String bearer) {

        this.token = token;
        this.type = bearer;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
