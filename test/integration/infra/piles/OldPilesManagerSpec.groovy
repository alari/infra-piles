package infra.piles

import grails.plugin.spock.IntegrationSpec
import org.apache.commons.lang.RandomStringUtils
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
@Ignore
class OldPilesManagerSpec extends IntegrationSpec {
    PilesManager pilesManager

    private PiledItem getNewItem() {
        [id: RandomStringUtils.randomAlphanumeric(5)] as PiledItem
    }
    
    private getNewPile() {
        //[id: RandomStringUtils.randomAlphanumeric(5)] as SortablePile
    }
    
    void "cannot put null"() {
        when:
        pilesManager.put(null, null, true)
        
        then:
        thrown(IllegalArgumentException)
    }
    
    void "filling the pile"() {
        def pile = newPile
        List<PiledItem> items = []
        10.times {items.add(newItem)}
        pilesManager.delete(pile)
        
        expect:
        pilesManager.sizeOf(pile) == 0
        pilesManager.draw(pile, 1, 0).size() == 0
        
        when: "putting the first item on top"
        pilesManager.put(items[0], pile, true)
        
        then:
        pilesManager.sizeOf(pile) == 1 
        pilesManager.draw(pile, 1, 0) == [items[0]]

        when: "putting the second one on top"
        pilesManager.put(items[1], pile, true)
        
        then: "it's beyond the first one"
        pilesManager.sizeOf(pile) == 2
        pilesManager.draw(pile, 3, 0) == [items[1], items[0]]

        when: "putting on the bottom"
        pilesManager.put(items[2], pile, false)
        
        then: "it's on the bottom"
        pilesManager.sizeOf(pile) == 3
        pilesManager.draw(pile, 3, 0) == [items[1], items[0], items[2]]
        
        when:
        pilesManager.put(items[3], pile, true)
        
        then:
        pilesManager.draw(pile, 30, 0) == [items[3], items[1], items[0], items[2]]

        when:
        pilesManager.put(items[4], pile, false)

        then:
        pilesManager.draw(pile, 30, 0) == [items[3], items[1], items[0], items[2], items[4]]
    }
    
    void "sorting the pile"() {
        def pile = newPile
        List<PiledItem> items = []
        10.times {items.add(newItem)}
        pilesManager.delete(pile)

        expect:
        pilesManager.sizeOf(pile) == 0
        pilesManager.draw(pile, 1, 0).size() == 0
    
        when: "sort top two ones"
        pilesManager.put(items[0], pile, true)
        
        then:
        pilesManager.sizeOf(pile) == 1

        when:
        pilesManager.fix(items[0], pile, 0)
        
        then:
        pilesManager.sizeOf(pile) == 1

        when:
        pilesManager.put(items[1], pile, true)
        
        then:
        pilesManager.draw(pile, 2, 0) == [items[1], items[0]]

        when:
        pilesManager.fix(items[0], pile, 0)
        
        then:
        pilesManager.draw(pile, 2, 0) == [items[0], items[1]]

        when: "sort one additional"
        pilesManager.put(items[2], pile, false)
        
        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[2]]

        when: " put first"
        pilesManager.fix(items[2], pile, 0)
        
        then:
        pilesManager.draw(pile, 3, 0) == [items[2], items[0], items[1]]

        when:
        pilesManager.release(items[2], pile, false)
        
        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[2]]

        when: " put last"
        pilesManager.fix(items[2], pile, 2)
        
        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[2]]

        when:
        pilesManager.release(items[2], pile, false)
        
        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[2]]

        when: " put second"
        pilesManager.fix(items[2], pile, 1)
        
        then:
        pilesManager.draw(pile, 3, 0) == [items[0], items[2], items[1]]

        when:
        pilesManager.release(items[2], pile, false)
        
        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[2]]

        when: " add one more"
        pilesManager.put(items[3], pile, false)
        
        then:
        pilesManager.draw(pile, 4, 0) == [items[0], items[1], items[3], items[2]]

        when:
        pilesManager.put(items[4], pile, false)

        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[4], items[3], items[2]]

        when: " put second"
        pilesManager.fix(items[3], pile, 1)

        then:
        pilesManager.draw(pile, 5, 0) == [items[0], items[3], items[1], items[4], items[2]]

        when:
        pilesManager.release(items[3], pile, false)

        then:
        pilesManager.draw(pile, 20, 0) == [items[0], items[1], items[4], items[3], items[2]]

        when: " test with tail"
        pilesManager.release(items[0], pile, true)

        then:
        pilesManager.draw(pile, 5, 0) == [items[4], items[3], items[2], items[1], items[0]]
    }
}
