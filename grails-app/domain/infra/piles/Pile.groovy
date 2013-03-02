package infra.piles

class Pile {

    String id

    List<Item> items

    static hasMany = [items:Item]

    static belongsTo = [tag:Tag]

    static mapWith = "mongo"

    static constraints = {
    }
}
