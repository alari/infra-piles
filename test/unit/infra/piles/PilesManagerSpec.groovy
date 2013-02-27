package infra.piles

import org.apache.commons.lang.RandomStringUtils
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class PilesManagerSpec extends Specification {
    @Shared PilesManager pilesManager = new TestPilesManager()

    void "cannot put null"() {
        when:
        pilesManager.put(null, null)

        then:
        thrown(NullPointerException)
    }

    void "filling the pile"() {
        Pile pile = newPile
        Pile pile2 = newPile
        List<PiledItem> items = []
        2.times {items.add newItem}

        expect:
        !pilesManager.getPiles(items[0])?.size()
        !pilesManager.sizeOf(pile)

        when:
        pilesManager.put(items[0], pile)

        then:
        pilesManager.getPiles(items[0])*.id == [pile.id]
        pilesManager.sizeOf(pile) == 1
        pilesManager.draw(pile, 1, 0) == [items[0]]

        when:
        pilesManager.remove(items[0], pile)

        then:
        !pilesManager.getPiles(items[0])?.size()
        !pilesManager.sizeOf(pile)

        when:
        pilesManager.put(items[0], pile)
        pilesManager.put(items[1], pile)

        then:
        pilesManager.getPiles(items[0])*.id == [pile.id]
        pilesManager.getPiles(items[1])*.id == [pile.id]
        pilesManager.draw(pile, 10, 0) == [items[0], items[1]]

        when:
        pilesManager.put(items[0], pile2)

        then:
        pilesManager.getPiles(items[0])*.id.containsAll(pile.id, pile2.id)
        pilesManager.getPiles(items[0]).size() == 2
        pilesManager.getPiles(items[1])*.id == [pile.id]
        pilesManager.sizeOf(pile) == 2
        pilesManager.inPile(items[0], pile)
        pilesManager.inPile(items[0], pile2)
        pilesManager.inPile(items[1], pile)
        !pilesManager.inPile(items[1], pile2)
        pilesManager.draw(pile2, 10, 0) == [items[0]]
    }

    private PiledItem getNewItem() {
        new TestItem(id: RandomStringUtils.randomAlphanumeric(5))
    }

    private Pile getNewPile() {
        new TestPile(id: RandomStringUtils.randomAlphanumeric(5))
    }
}
