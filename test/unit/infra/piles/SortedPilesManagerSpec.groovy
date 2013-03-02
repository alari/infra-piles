package infra.piles

import org.apache.commons.lang.RandomStringUtils
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class SortedPilesManagerSpec extends Specification {

    @Shared SortedPilesManager pilesManager = new TestSortedPilesManager()
    @Shared SortablePile pile = newPile
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

    void "Sorting a triade"() {
        when:
        items.each {pilesManager.put(it, pile)}

        then:
        pilesManager.sizeOf(pile) == items.size()
        pilesManager.draw(pile,0,0) == items

        when:
        PiledItem item = newItem
        pilesManager.put(item, pile, 0)

        then:
        pilesManager.sizeOf(pile) == items.size() + 1
        pilesManager.draw(pile,1,0) == [item]

        when:
        pilesManager.put(item, pile, 1)

        then:
        pilesManager.draw(pile,2,0) == [items[0], item]
        pilesManager.draw(pile,1,1) == [item]

        when:
        pilesManager.remove(item, pile, true)

        then:
        pilesManager.sizeOf(pile) == 1
        pilesManager.draw(pile, 0, 0) == [items[0]]

        when:
        pilesManager.put(item, pile, 100000)

        then:
        pilesManager.draw(pile, 2, 0) == [items[0], item]

        when:
        pilesManager.remove(items[0], pile)

        then:
        pilesManager.sizeOf(pile) == 1
        pilesManager.getPiles(item)*.id == [pile.id]
    }

    private PiledItem getNewItem() {
        new TestItem(id: RandomStringUtils.randomAlphanumeric(5))
    }

    private SortablePile getNewPile() {
        new TestPile(id: RandomStringUtils.randomAlphanumeric(5))
    }
}
