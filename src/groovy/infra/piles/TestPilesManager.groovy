package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:05 AM
 */
class TestPilesManager implements PilesManager<TestItem,TestPile> {
    @Override
    void put(TestItem item, TestPile pile) {
        remove(item, pile)
        pile.plainItems.push(item)
        item.piles.add(pile)
    }

    @Override
    void remove(TestItem item, TestPile pile) {
        if (pile.plainItems.contains(item)) {
            pile.plainItems.remove(item)
        }
        if (item.piles.contains(pile)) {
            item.piles.remove(pile)
        }
    }

    @Override
    List<TestItem> draw(TestPile pile, long limit, long offset) {
        if (limit) {
            pile.plainItems.subList((int)offset, Math.min((int)offset+limit, pile.plainItems.size()))
        } else {
            pile.plainItems
        }
    }

    @Override
    Collection<TestPile> getPiles(TestItem item) {
        item.piles
    }

    @Override
    boolean inPile(TestItem item, TestPile pile) {
        item.piles.contains(pile)
    }

    @Override
    long sizeOf(TestPile pile) {
        pile.plainItems.size()
    }
}
