package infra.piles

import org.apache.commons.lang.RandomStringUtils
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class SortedPilesManagerSpec extends Specification {

    @Shared SortedPilesManager pilesManager = new TestSortedPilesManager()
    @Shared Pile pile = newPile
    @Shared List<PiledItem> items = []

    def setupSpec() {
        10.times {items.add(newItem)}
    }

    void "adding and removing a single element"(){
        when:
        pilesManager.put(items[0], pile)

        then:
        pilesManager.getPiles(items[0])*.id == [pile.id]
        pilesManager.draw(pile, 0, 0) == [items[0]]
        pilesManager.inPile(items[0], pile)

        when:
        pilesManager.remove(items[0], pile)

        then:
        !pilesManager.getPiles(items[0]).size()
        pilesManager.draw(pile, 0, 0) == []
        !pilesManager.inPile(items[0], pile)
    }

    void "sorting a pair"() {
        when:
        pilesManager.put(items[0], pile)
        pilesManager.put(items[1], pile)

        then:
        pilesManager.draw(pile, 0, 0) == items[0..1]
        pilesManager.sizeOf(pile) == 2

        when:
        pilesManager.put(items[0], pile, 1)

        then:
        pilesManager.draw(pile, 0, 0) == items[0..1].reverse()

        when:
        pilesManager.put(items[0], pile, 0)

        then:
        pilesManager.draw(pile, 0, 0) == items[0..1]

        when:
        pilesManager.remove(items[1], pile)

        then:
        pilesManager.draw(pile, 0, 0) == items[0..0]

        when:
        pilesManager.put(items[1], pile)

        then:
        pilesManager.draw(pile, 0, 0) == items[0..1]
        pilesManager.draw(pile, 1, 0) == items[0..0]
        pilesManager.sizeOf(pile) == 2

        when:
        pilesManager.remove(items[0], pile, true)

        then:
        !pilesManager.inPile(items[0], pile)
        !pilesManager.inPile(items[1], pile)
        !pilesManager.sizeOf(pile)
    }

    private PiledItem getNewItem() {
        new TestItem(id: RandomStringUtils.randomAlphanumeric(5))
    }

    private Pile getNewPile() {
        new TestPile(id: RandomStringUtils.randomAlphanumeric(5))
    }
}
