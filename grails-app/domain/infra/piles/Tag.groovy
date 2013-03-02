package infra.piles

class Tag {

    String id
    String title

    static hasOne = [pile:Pile]

    static constraints = {
    }
}
