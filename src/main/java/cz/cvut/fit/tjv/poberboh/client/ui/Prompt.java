package cz.cvut.fit.tjv.poberboh.client.ui;

import cz.cvut.fit.tjv.poberboh.client.data.OwnerClient;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class Prompt implements PromptProvider {

    private final OwnerClient ownerClient;

    public Prompt(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }

    @Override
    public AttributedString getPrompt() {
        if (ownerClient.getCurrentId() == null) {
            return new AttributedString("owners:>");
        }
        return new AttributedString("owner" + ownerClient.getCurrentId() + ":>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
