package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

public class JwtResponse {

    private  String token;

    public JwtResponse(String token) {

        this.token = token;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

}
