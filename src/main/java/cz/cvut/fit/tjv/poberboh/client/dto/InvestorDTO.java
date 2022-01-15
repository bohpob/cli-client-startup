package cz.cvut.fit.tjv.poberboh.client.dto;

import java.util.ArrayList;
import java.util.List;

public class InvestorDTO {

    private String username;
    private String firstname;
    private String lastname;
    private List<Integer> startupIds = new ArrayList<>();

    public InvestorDTO(String username, String firstname, String lastname, List<Integer> startupList) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.startupIds = startupList;
    }

    public InvestorDTO() {
    }

    public List<Integer> getStartupList() {
        return startupIds;
    }

    public InvestorDTO(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void addStartup(Integer id) {
        this.startupIds.add(id);
    }

    public void setStartupIds(List<Integer> startupIds) {
        this.startupIds = startupIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
