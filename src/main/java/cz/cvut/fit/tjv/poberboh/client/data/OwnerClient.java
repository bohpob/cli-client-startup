package cz.cvut.fit.tjv.poberboh.client.data;

import cz.cvut.fit.tjv.poberboh.client.dto.OwnerDTO;
import cz.cvut.fit.tjv.poberboh.client.ui.OwnerView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;

@Component
public class OwnerClient {

    private final WebClient ownerWebClient;
    private final OwnerView ownerView;
    private Integer id;

    public OwnerClient(@Value("${backend_url}") String backedUrl, OwnerView ownerView) {
        ownerWebClient = WebClient.create(backedUrl + "/owners");
        this.ownerView = ownerView;
    }

    public void setCurrentId(Integer id) {
        this.id = id;
        if (getCurrentId() != null) {
            try {
                read();
            } catch (WebClientException e) {
                this.id = null;
                throw e;
            }
        }
    }

    public Integer getCurrentId() {
        return id;
    }

    public OwnerDTO create(OwnerDTO owner) {
        return ownerWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(owner)
                .retrieve()
                .bodyToMono(OwnerDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public OwnerDTO read() {
        return ownerWebClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OwnerDTO.class)
                .block();
    }

    public void update(OwnerDTO owner) {
        if(getCurrentId() == null)
            throw new IllegalStateException("current id not set");
        ownerWebClient.put()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(owner)
                .retrieve()
                .toBodilessEntity()
                .subscribe(x -> {}, e -> {
                    ownerView.printErrorUpdate(e);});
    }

    public void delete() {
        if(getCurrentId() == null)
            throw new IllegalStateException("current id not set");
        ownerWebClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        x -> {setCurrentId(null);},
                        e -> {
                            ownerView.printErrorOwner(e);}
                );
    }
}
