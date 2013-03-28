package infra.piles

class Entry implements Item<Label> {

    String id
    Set<Label> piles

    static hasOne = [content:EntryContents]

    static constraints = {
    }
}
