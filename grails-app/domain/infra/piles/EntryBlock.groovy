package infra.piles

import infra.ca.Atom

class EntryBlock implements Atom {

    String id

    String title

    String type

    Map<String, String> texts

    public String getText() {
        texts?.containsKey("main")?texts.get("main"):null
    }

    public void setText(String text) {
        if (!texts) texts = [:]
        texts.put("main", text)
    }

    String externalId
    String externalUrl

    Map<String, String> sounds

    Map<String, String> images

    Date dateCreated
    Date lastUpdated

    static constraints = {
        externalId nullable: true
        externalUrl nullable: true
    }
}
