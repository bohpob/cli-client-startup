package cz.cvut.fit.tjv.poberboh.client.ui;

import cz.cvut.fit.tjv.poberboh.client.dto.OwnerDTO;
import cz.cvut.fit.tjv.poberboh.client.dto.StartupDTO;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.ExitRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class OwnerView {

    public void printErrorGeneric(Throwable e) {
        if (e instanceof WebClientRequestException) {
            System.err.println("Cannot connect to API");
            throw new ExitRequest();
        } else if (e instanceof WebClientResponseException.InternalServerError) {
            System.err.println("Unknown technical server error");
        } else {
            System.err.println("Unknown error: " + e.toString());
        }
    }

    public void printOwner(OwnerDTO owner) {
        System.out.println("username: " + owner.getUsername()
                + "\nfirstname: " + owner.getFirstname()
                + "\nlastname: " + owner.getLastname());
        if (!owner.getStartupList().isEmpty()) {
            int i = 1;
            for (StartupDTO startupDTO : owner.getStartupList()) {
                System.out.println(i++ + ".startup:");
                System.out.println("name: " + startupDTO.getName()
                        + "\ninvestment: " + startupDTO.getInvestment());
            }
        }
    }

    public void printErrorCreate(WebClientException e) {
        if (e instanceof WebClientResponseException.Conflict) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Owner already exists",
                    AnsiColor.DEFAULT
            ));
        } else
            printErrorGeneric(e);
    }

    public void printErrorUpdate(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot update - owner does not exist",
                    AnsiColor.DEFAULT
            ));
        } else
            printErrorGeneric(e);
    }

    public void printErrorOwner(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Owner does not exist",
                    AnsiColor.DEFAULT
            ));
        } else
            printErrorGeneric(e);
    }
}
