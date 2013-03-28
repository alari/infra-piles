package infra.piles

class Label implements Pile<Entry> {

    String id
    String title

    Entry item

    static hasOne = [item:Entry]

    static constraints = {
    }
}
