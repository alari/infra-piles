package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:33 AM
 */
class TestSortedPilesManager implements SortedPilesManager<TestItem,TestPile> {
    @Override
    void put(TestItem item, TestPile pile, int position) {
        pile.sortedItems.remove(item)
        position <= pile.sortedItems.size() ? pile.sortedItems.add(position, item) : pile.sortedItems.add(item)
        item.piles.add(pile)
    }

    @Override
    void remove(TestItem item, TestPile pile, boolean withTail) {
        if (withTail) {
            int index = pile.sortedItems.indexOf(item)
            if (index >= 0) {
                Collection<TestItem> itemsToRemove = []
                for (int i=index; i<pile.sortedItems.size(); i++){
                    itemsToRemove.add pile.sortedItems[i]
                }
                itemsToRemove.each {
                    it.piles.remove(pile)
                    pile.sortedItems.remove(it)
                }
            }
        } else {
            pile.sortedItems.remove(pile)
        }
        item.piles.remove(pile)
    }

    @Override
    void put(TestItem item, TestPile pile) {
        assert item
        assert pile
        if (!pile.sortedItems.contains(item)) pile.sortedItems.add(item)
        assert item.piles != null
        item.piles.add(pile)
    }

    @Override
    void remove(TestItem item, TestPile pile) {
        pile.sortedItems.remove(item)
        item.piles.remove(pile)
    }

    @Override
    List<TestItem> draw(TestPile pile, long limit, long offset) {
        if (limit) {
            pile.sortedItems.subList((int)offset, Math.min((int)offset+limit, pile.sortedItems.size()))
        } else {
            pile.sortedItems
        }
    }

    @Override
    Collection<TestPile> getPiles(TestItem item) {
        item.piles
    }

    @Override
    boolean inPile(TestItem item, TestPile pile) {
        pile.sortedItems.contains(item)
    }

    @Override
    long sizeOf(TestPile pile) {
        pile.sortedItems.size()
    }
}
