package infra.piles

class Item {

    String id

    Set<Tag> tags
    Set<Tag> unorderedTags

    static constraints = {
    }

    static mapping = {
        unorderedTags index: true
    }
}
