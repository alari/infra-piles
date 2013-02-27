package infra.piles

import org.apache.commons.lang.RandomStringUtils
import spock.lang.Shared
import spock.lang.Specification

class HidePilesManagerSpec extends Specification {
    @Shared HidePilesManager pilesManager = new TestHidePilesManager()

    void "can hide and reveal"() {
        Pile pile = newPile
        List<PiledItem> items = []
        10.times {items.add(newItem)}

        when:
        pilesManager.put(items[0], pile)

        then:
        pilesManager.inPile(items[0], pile)
        pilesManager.draw(pile, 0, 0) == [items[0]]
        pilesManager.getPiles(items[0])*.id == [pile.id]
        !pilesManager.isHidden(items[0], pile)

        when:
        pilesManager.hide(items[0], pile)

        then:
        pilesManager.inPile(items[0], pile)
        pilesManager.draw(pile, 0, 0) == []
        pilesManager.getPiles(items[0])*.id == [pile.id]
        pilesManager.isHidden(items[0], pile)

        when:
        pilesManager.reveal(items[0], pile)

        then:
        pilesManager.inPile(items[0], pile)
        pilesManager.draw(pile, 0, 0) == [items[0]]
        pilesManager.getPiles(items[0])*.id == [pile.id]
        !pilesManager.isHidden(items[0], pile)
    }

    private PiledItem getNewItem() {
        new TestItem(id: RandomStringUtils.randomAlphanumeric(5))
    }

    private Pile getNewPile() {
        new TestPile(id: RandomStringUtils.randomAlphanumeric(5))
    }
}
