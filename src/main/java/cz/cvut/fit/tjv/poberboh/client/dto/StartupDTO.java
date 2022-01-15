package cz.cvut.fit.tjv.poberboh.client.dto;

import java.util.List;

public class StartupDTO {

    private String name;
    private Integer investment;
    private List<InvestorDTO> investorList;

    public StartupDTO(String name, Integer investment) {
        this.name = name;
        this.investment = investment;
    }

    public StartupDTO(String name, Integer investment, List<InvestorDTO> investorList) {
        this.name = name;
        this.investment = investment;
        this.investorList = investorList;
    }

    public StartupDTO() {
    }

    public List<InvestorDTO> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<InvestorDTO> investorList) {
        this.investorList = investorList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInvestment() {
        return investment;
    }

    public void setInvestment(Integer investment) {
        this.investment = investment;
    }
}
