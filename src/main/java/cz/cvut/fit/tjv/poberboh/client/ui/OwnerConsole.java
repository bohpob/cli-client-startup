package cz.cvut.fit.tjv.poberboh.client.ui;

import cz.cvut.fit.tjv.poberboh.client.data.OwnerClient;
import cz.cvut.fit.tjv.poberboh.client.dto.OwnerDTO;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.web.reactive.function.client.WebClientException;

@ShellComponent
public class OwnerConsole {

    private final OwnerClient ownerClient;

    private final OwnerView ownerView;

    public OwnerConsole(OwnerClient userClient, OwnerView userView) {
        this.ownerClient = userClient;
        this.ownerView = userView;
    }

    @ShellMethod("Create new owner")
    public void createOwner(String username, String firstname, String lastname) {
        try {
            var newOwner = new OwnerDTO(username, firstname, lastname);
            var ret = ownerClient.create(newOwner);
            ownerView.printOwner(ret);
        } catch (WebClientException e) {
            ownerView.printErrorCreate(e);
        }
    }

    @ShellMethod("Set current owner")
    public void setOwner(Integer id) {
        try {
            ownerClient.setCurrentId(id);
        } catch (WebClientException e) {
            ownerView.printErrorOwner(e);
        }
    }

    @ShellMethod("Unset current owner, move to general scope")
    @ShellMethodAvailability("currentOwnerNeededAvailability")
    public void unsetOwner() {
        ownerClient.setCurrentId(null);
    }

    @ShellMethod("Print a owner")
    @ShellMethodAvailability("currentOwnerNeededAvailability")
    public void readOwner() {
        try {
            ownerView.printOwner(ownerClient.read());
        } catch (WebClientException e) {
            ownerView.printErrorOwner(e);
        }
    }

    @ShellMethod("Update a owner")
    @ShellMethodAvailability("currentOwnerNeededAvailability")
    public void updateOwner(String username, String firstname, String lastname) {
        try {
            ownerClient.update(new OwnerDTO(username, firstname, lastname));
        } catch (WebClientException e) {
            ownerView.printErrorUpdate(e);
        }
    }

    @ShellMethod("Delete a owner")
    @ShellMethodAvailability("currentOwnerNeededAvailability")
    public void deleteOwner() {
        try {
            ownerClient.delete();
        } catch (WebClientException e) {
            ownerView.printErrorOwner(e);
        }
    }

    public Availability currentOwnerNeededAvailability() {
        return ownerClient.getCurrentId() == null ?
                Availability.unavailable("current owner needs to be set in advance")
                :
                Availability.available();
    }
}
